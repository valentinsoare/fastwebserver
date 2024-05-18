package io.valentinsoare.fastweb.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Set;

@Service
public class AsyncNetworkServer {
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SelectionKey serverSocketChannelKey;

    private ServerSocketChannel serverSocketChannelForClientConnection;
    private SocketChannel socketChannelForClient;

    private ConnectionPool connectionPool;

    public AsyncNetworkServer() {
        try {
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEPORT, true);

            serverSocketChannel.bind(new InetSocketAddress(8080));
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();

            serverSocketChannelKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

//            connectionPool = new ConnectionPool(512, "localhost", 8080);
            System.out.println(" Server started on port 8080...");

        } catch (IOException e) {
            System.out.printf("%n\033[31m ERROR: Issues in the init phase of the web server: %s\033[0m", e.getMessage());
            System.exit(1);
        }
    }

    private void workOnRequest(Iterator<SelectionKey> keyIterator) throws IOException {
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            keyIterator.remove();

            if (key.isAcceptable()) {
                serverSocketChannelForClientConnection = (ServerSocketChannel) key.channel();

                socketChannelForClient = serverSocketChannelForClientConnection.accept();

                socketChannelForClient.setOption(StandardSocketOptions.TCP_NODELAY, true);

                socketChannelForClient.configureBlocking(false);
                socketChannelForClient.register(selector, SelectionKey.OP_READ);

                System.out.println("\n New client connected: " + socketChannelForClient.socket().getRemoteSocketAddress());

                socketChannelForClient.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
//                        socketChannelForClient.setOption(StandardSocketOptions.SO_LINGER, 0);        // avoid
            } else if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();

                ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);

                int bytesRead = clientChannel.read(buffer);

                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.println("\n Client disconnected");
                    key.cancel();
                    continue;
                }

                buffer.flip();
                String requestData = StandardCharsets.UTF_8.decode(buffer).toString().trim();
                System.out.println(" Received request: " + requestData);

                StringBuilder answer = new StringBuilder();

                answer.append("HTTP/1.1 200 OK\r\n")
                        .append("Content-Type: text/plain\r\n")
                        .append("Connection: keep-alive\r\n")
                        .append("\r\n")
                        .append(" Hello from the server!\r\n");

                clientChannel.write(ByteBuffer.wrap(answer.toString().getBytes(StandardCharsets.UTF_8)));

//                key.cancel();
                buffer.clear();
//                clientChannel.close();
            }
        }
    }

    public void runServer() {
        try  {
            while (true) {
                int selectedKeysCount = selector.select();

                if (selectedKeysCount == 0) {
                    continue;
                }

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                workOnRequest(keyIterator);
            }

        } catch (IOException e) {
            System.out.printf("%n \033[31mERROR: Issues with handling the web connections: %s\033[0m%n", e.getMessage());
        } finally {
            closeResources();
        }
    }

    private void closeResources() {
        try {
            if (selector != null) {
                selector.close();
            }

            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException e) {
            System.err.printf("%n \033[31mERROR: Failed to close resources: %s\033[0m", e.getMessage());
        }
    }
}

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
            System.out.println("Server started on port 8080");

        } catch (IOException e) {
            System.out.printf("%nERROR: Issues in the init phase of the web server: %s", e.getMessage());
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

                System.out.println("\nNew client connected: " + socketChannelForClient.socket().getRemoteSocketAddress());

                socketChannelForClient.setOption(StandardSocketOptions.SO_KEEPALIVE, true);
//                        socketChannelForClient.setOption(StandardSocketOptions.SO_LINGER, 0);        // avoid
            } else if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();

                ByteBuffer buffer = ByteBuffer.allocateDirect(1024 * 1024);

                int bytesRead = clientChannel.read(buffer);

                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.println("\nClient disconnected");
                    key.cancel();
                    continue;
                }

                buffer.flip();
                String requestData = StandardCharsets.UTF_8.decode(buffer).toString().trim();
                System.out.println("Received request: " + requestData);

                String response = "HTTP/1.1 200 OK\r\n"
                        + "Content-Type: text/plain\r\n"
                        + "Connection: keep-alive\r\n"
                        + "\r\n"
                        + "Hello from the server!\r\n";

                clientChannel.write(ByteBuffer.wrap(response.getBytes(StandardCharsets.UTF_8)));

//                        key.cancel();
                buffer.clear();
//                        clientChannel.close();
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
            System.out.printf("%nERROR: Issues with handling the web connections: %s", e.getMessage());
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
            System.err.println("ERROR: Failed to close resources: " + e.getMessage());
        }
    }
}

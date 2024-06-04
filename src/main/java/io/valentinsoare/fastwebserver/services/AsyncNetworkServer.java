package io.valentinsoare.fastwebserver.services;

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

/**
 * This service class represents an asynchronous network server.
 * It uses non-blocking I/O and a selector to handle multiple network connections.
 */

public class AsyncNetworkServer {
    private final int connectionPort;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SelectionKey serverSocketChannelKey;

    private ServerSocketChannel serverSocketChannelForClientConnection;
    private SocketChannel socketChannelForClient;

    /**
     * Constructor for the AsyncNetworkServer.
     * It initializes the server socket channel, sets the socket options, binds it to a port, and opens a selector.
     */
    public AsyncNetworkServer(int connectionPort) {
        this.connectionPort = connectionPort;

        try {
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEPORT, true);

            serverSocketChannel.bind(new InetSocketAddress(connectionPort));
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();

            serverSocketChannelKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.printf("Server started on port %s...", connectionPort);

        } catch (IOException e) {
            System.out.printf("%n\033[31m ERROR: Issues in the init phase of the web server. %s.%n%n\033[0m", e.getMessage());
            System.exit(1);
        }
    }

    /**
     * Here we are handling the incoming requests from the clients.
     * We iterate over the selected keys, checks if the key is acceptable or readable, and performs the appropriate action.
     *
     * @param keyIterator Iterator for the selected keys.
     * @throws IOException If an I/O error occurs.
     */
    private void workOnRequest(Iterator<SelectionKey> keyIterator) throws IOException {
        while (keyIterator.hasNext()) {
            SelectionKey key = keyIterator.next();
            keyIterator.remove();

            if (key.isAcceptable()) {
                serverSocketChannelForClientConnection = (ServerSocketChannel) key.channel();
                socketChannelForClient = serverSocketChannelForClientConnection.accept();

                socketChannelForClient.configureBlocking(false);
                socketChannelForClient.register(selector, SelectionKey.OP_READ);
                socketChannelForClient.setOption(StandardSocketOptions.SO_KEEPALIVE, true);

                System.out.printf("%n%n New client connected: %s.", socketChannelForClient.socket().getRemoteSocketAddress());
//                socketChannelForClient.setOption(StandardSocketOptions.TCP_NODELAY, true);  // Enable Nagle algorithm for better performance.
//                socketChannelForClient.setOption(StandardSocketOptions.SO_LINGER, 0);   // Reset connection from the server and avoid TIME_WAIT on the server.
            } else if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                int bytesRead = clientChannel.read(buffer);

                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.println("\n Client disconnected.");
                    key.cancel();
                    continue;
                }

                buffer.flip();
                String requestData = StandardCharsets.UTF_8.decode(buffer).toString().trim();
                System.out.printf("%n Received request: %n%s.", requestData);

                StringBuilder answer = new StringBuilder();

                answer.append("HTTP/1.1 200 OK\r\n")
                        .append("Content-Type: text/plain\r\n")
                        .append("Connection: keep-alive\r\n")
                        .append("\r\n")
                        .append(String.format("%s\r%n", "Client connected!"));

                clientChannel.write(ByteBuffer.wrap(answer.toString().getBytes(StandardCharsets.UTF_8)));

                buffer.clear();
//                key.cancel();
//                clientChannel.close();
            }
        }
    }

    /**
     * This method starts the server and keeps it running to accept and handle incoming connections.
     * It continuously selects the ready keys and calls the workOnRequest method to handle the requests.
     */
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
            System.out.printf("%n \033[31mERROR: Issues with handling the web connections. %s.\033[0m%n%n", e.getMessage());
        } finally {
            closeResources();
        }
    }

    /**
     * This method closes the resources (selector and server socket channel) when they are no longer needed.
     */
    private void closeResources() {
        try {
            if (selector != null) {
                selector.close();
            }

            if (serverSocketChannel != null) {
                serverSocketChannel.close();
            }
        } catch (IOException e) {
            System.err.printf("%n \033[31mERROR: Failed to close resources. %s.\033[0m%n%n", e.getMessage());
        }
    }
}

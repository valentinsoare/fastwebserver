package io.valentinsoare.fastwebserver.services;

import io.micrometer.core.instrument.Timer;
import io.valentinsoare.fastwebserver.outputformat.ColorOutput;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.StandardSocketOptions;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.TimeUnit;

public class AsyncNetworkServer {
    private int connectionPort;
    private ServerSocketChannel serverSocketChannel;
    private Selector selector;
    private SelectionKey serverSocketChannelKey;

    private ServerSocketChannel serverSocketChannelForClientConnection;
    private SocketChannel socketChannelForClient;

    private CustomMetric metricService;


    public AsyncNetworkServer(int connectionPort, CustomMetric metricService) {
        this.connectionPort = connectionPort;
        this.metricService = metricService;


        try {
            serverSocketChannel = ServerSocketChannel.open();

            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEADDR, true);
//            serverSocketChannel.setOption(StandardSocketOptions.SO_REUSEPORT, true); // this is only available for linux

            serverSocketChannel.bind(new InetSocketAddress(connectionPort));
            serverSocketChannel.configureBlocking(false);

            selector = Selector.open();

            serverSocketChannelKey = serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.printf(" %sServer started on port %s...%s",
                    ColorOutput.SUCCESS.getTypeOfColor(), connectionPort, ColorOutput.OFF_COLOR.getTypeOfColor());
        } catch (IOException e) {
            System.out.printf("%n%s ERROR: Issues in the init phase of the web server. %s.%n%n%s",
                    ColorOutput.ERROR.getTypeOfColor(), e.getMessage(), ColorOutput.OFF_COLOR.getTypeOfColor());
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

                socketChannelForClient.configureBlocking(false);
                socketChannelForClient.register(selector, SelectionKey.OP_READ, SelectionKey.OP_WRITE);
                socketChannelForClient.setOption(StandardSocketOptions.SO_KEEPALIVE, true);

                System.out.printf("%n%n %sNew client connected: %s.%s",
                        ColorOutput.SUCCESS.getTypeOfColor(), socketChannelForClient.socket().getRemoteSocketAddress(), ColorOutput.OFF_COLOR.getTypeOfColor());
//                socketChannelForClient.setOption(StandardSocketOptions.TCP_NODELAY, true);  // Enable Nagle algorithm for better performance.
//                socketChannelForClient.setOption(StandardSocketOptions.SO_LINGER, 0);   // Reset connection from the server.
            } else if (key.isReadable()) {
                SocketChannel clientChannel = (SocketChannel) key.channel();

                //1 MB buffer by default.
                ByteBuffer buffer = ByteBuffer.allocate(1024 * 1024);
                int bytesRead = clientChannel.read(buffer);

                if (bytesRead == -1) {
                    clientChannel.close();
                    System.out.printf("%n %sClient disconnected.%s%n",
                            ColorOutput.SUCCESS.getTypeOfColor(), ColorOutput.OFF_COLOR.getTypeOfColor());
                    key.cancel();
                    continue;
                }

                buffer.flip();
                String requestData = StandardCharsets.UTF_8.decode(buffer).toString().trim();

                long receivedTime = System.currentTimeMillis();
                System.out.printf("%n %sReceived request: %n%s.%s",
                        ColorOutput.SUCCESS.getTypeOfColor(), requestData, ColorOutput.OFF_COLOR.getTypeOfColor());

                metricService.getHomeEndPointMetrics().incrementHttpRequests();

                StringBuilder answer = new StringBuilder();

                answer.append("HTTP/1.1 200 OK\r\n")
                        .append("Content-Type: text/plain\r\n")
                        .append("Connection: keep-alive\r\n")
                        .append("\r\n")
                        .append(String.format("%s\r%n", "hello from machine!"));

                int numberOfBytesSend = clientChannel.write(ByteBuffer.wrap(answer.toString().getBytes(StandardCharsets.UTF_8)));

                long millis = Duration.ofMillis(System.currentTimeMillis() - receivedTime).toMillis();
                Timer t = metricService.getHomeEndPointMetrics().getHttp_server_response_time();

                t.record(millis, TimeUnit.MILLISECONDS);

                if (numberOfBytesSend > 0) {
                    metricService.getHomeEndPointMetrics().incrementHttpRequestsWithSuccess();
                } else {
                    metricService.getHomeEndPointMetrics().decrementHttpRequests();
                }

                buffer.clear();
            }
        }
    }

    public void runServer() {
        try  {
            while (true) {
                selector.select();

                Set<SelectionKey> selectedKeys = selector.selectedKeys();
                Iterator<SelectionKey> keyIterator = selectedKeys.iterator();

                workOnRequest(keyIterator);
            }
        } catch (IOException e) {
            System.out.printf("%n %sERROR: Issues when handling the web connections. %s.%s%n%n",
                    ColorOutput.ERROR.getTypeOfColor(), e.getMessage(), ColorOutput.OFF_COLOR.getTypeOfColor());
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
            System.err.printf("%n %sERROR: Failed to close resources. %s.%s%n%n",
                    ColorOutput.ERROR.getTypeOfColor(), e.getMessage(), ColorOutput.OFF_COLOR.getTypeOfColor());
        }
    }
}

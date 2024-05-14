package io.valentinsoare.fastweb.services;

import org.springframework.stereotype.Service;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.SocketChannel;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.TimeUnit;

@Service
public class ConnectionPool {
    private Queue<SocketChannel> pool;
    private int maxConnections;
    private InetSocketAddress serverAddress;

    public ConnectionPool() {
        this.pool = new ConcurrentLinkedQueue<>();
        this.maxConnections = 512;
        this.serverAddress = new InetSocketAddress("localhost", 8080);
    }

    public ConnectionPool(int maxConnections, String host, int port) {
        this.pool = new ConcurrentLinkedQueue<>();
        this.maxConnections = maxConnections;
        this.serverAddress = new InetSocketAddress(host, port);
    }

    public SocketChannel borrowConnection() throws IOException {
        SocketChannel connection = pool.poll();

        if (connection != null && connection.isConnected()) {
            // Reuse the existing connection
            return connection;
        }

        // If the pool is empty, and we haven't exceeded the max connections, create a new connection
        if (pool.size() < maxConnections) {
            connection = SocketChannel.open();
            connection.configureBlocking(false);
            connection.connect(serverAddress);

            // Wait for the connection to be established
            while (!connection.finishConnect()) {
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted while connecting", e);
                }
            }

            return connection;
        }

        // If the pool is empty and we reached the maximum number of connections, wait for an available connection
        while (connection == null) {
            connection = pool.poll();

            if (connection == null || !connection.isConnected()) {

                // If the connection is not valid, sleep and try again
                try {
                    TimeUnit.MILLISECONDS.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    throw new IOException("Interrupted while waiting for a connection", e);
                }
            }
        }

        return connection;
    }

    public void returnConnection(SocketChannel connection) {
        pool.offer(connection);
    }

    public void closeAllConnections() throws IOException {
        for (SocketChannel connection : pool) {
            connection.close();
        }

        pool.clear();
    }
}

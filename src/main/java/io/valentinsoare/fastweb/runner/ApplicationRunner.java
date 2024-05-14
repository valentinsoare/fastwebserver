package io.valentinsoare.fastweb.runner;

import io.valentinsoare.fastweb.services.AsyncNetworkServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final AsyncNetworkServer asyncNetworkServer;

    @Autowired
    public ApplicationRunner(AsyncNetworkServer asyncNetworkServer) {
        this.asyncNetworkServer = asyncNetworkServer;
    }

    @Override
    public void run(String... args) {
        asyncNetworkServer.runServer();
    }
}

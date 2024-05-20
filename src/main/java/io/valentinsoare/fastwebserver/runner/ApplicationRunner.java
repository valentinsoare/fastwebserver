package io.valentinsoare.fastwebserver.runner;

import io.valentinsoare.fastwebserver.services.AsyncNetworkServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;


/**
 * This class is a runner for the application.
 * It implements the CommandLineRunner interface, which indicates that a bean should run when it is contained within a SpringApplication.
 * A Spring Boot application will call the run method of all beans implementing this interface after the application context has been loaded.
 */
@Component
public class ApplicationRunner implements CommandLineRunner {
    private final AsyncNetworkServer asyncNetworkServer;

    /**
     * Constructor for the ApplicationRunner.
     * It uses Spring's @Autowired annotation to inject an instance of AsyncNetworkServer into this runner.
     *
     * @param asyncNetworkServer An instance of AsyncNetworkServer.
     */
    @Autowired
    public ApplicationRunner(AsyncNetworkServer asyncNetworkServer) {
        this.asyncNetworkServer = asyncNetworkServer;
    }

    /**
     * This method is the entry point for the runner.
     * It is called after the application context is loaded and just before SpringApplication.run(…) completes.
     * It starts the AsyncNetworkServer.
     *
     * @param args Array of strings representing command line arguments.
     */
    @Override
    public void run(String... args) {
        asyncNetworkServer.runServer();
    }
}

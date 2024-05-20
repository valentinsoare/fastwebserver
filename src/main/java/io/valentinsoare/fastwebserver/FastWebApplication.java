package io.valentinsoare.fastwebserver;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * This is the main entry point for the Spring Boot application.
 * It uses the @SpringBootApplication annotation, which is a convenience annotation that adds all of the following:
 * - @Configuration: Tags the class as a source of bean definitions for the application context.
 * - @EnableAutoConfiguration: Tells Spring Boot to start adding beans based on classpath settings, other beans, and various property settings.
 * - @ComponentScan: Tells Spring to look for other components, configurations, and services in the 'io.valentinsoare.fastwebserver' package, allowing it to find and register the AsyncNetworkServer.
 */
@SpringBootApplication
public class FastWebApplication {

    /**
     * The main method uses Spring Boot’s SpringApplication.run() method to launch an application.
     * This method call is what starts the whole Spring framework infrastructure.
     *
     * @param args Array of strings representing command line arguments.
     */
    public static void main(String[] args) {
        SpringApplication.run(FastWebApplication.class, args);
    }
}

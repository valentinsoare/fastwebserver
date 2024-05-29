package io.valentinsoare.fastwebserver.runner;

import io.valentinsoare.fastwebserver.config.ServerOptionsExecutionTime;
import io.valentinsoare.fastwebserver.services.AsyncNetworkServer;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.*;


/**
 * This class is a runner for the application.
 * It implements the CommandLineRunner interface, which indicates that a bean should run when it is contained within a SpringApplication.
 * A Spring Boot application will call the run method of all beans implementing this interface after the application context has been loaded.
 */
@Component
public class ApplicationRunner implements CommandLineRunner {
    private AsyncNetworkServer asyncNetworkServer;
    private ServerOptionsExecutionTime serverOptionsExecutionTime;

    @Autowired
    public ApplicationRunner(ServerOptionsExecutionTime serverOptionsExecutionTime) {
        this.serverOptionsExecutionTime = serverOptionsExecutionTime;
    }

    private Map<String, String> extractOptionsFromUserInput(String[] arguments) {
        List<String> optionsFromUserInput = new ArrayList<>();
        List<String> valuesFromUserInput = Collections.emptyList();

        CommandLineParser commandLineParser = new DefaultParser();

        try {
            CommandLine commandLine = commandLineParser.parse(serverOptionsExecutionTime.getRequiredOptions(), arguments);

            for (Option o : serverOptionsExecutionTime.getRequiredOptions().getOptions()) {
                if (commandLine.hasOption(o)) {
                    optionsFromUserInput.add(o.getLongOpt());
                }
            }

            valuesFromUserInput = new ArrayList<>(Arrays.asList(commandLine.getArgs()));

        } catch (ParseException e) {
            throw new RuntimeException(e);
        }

        if ((optionsFromUserInput.isEmpty()) || (optionsFromUserInput.size() != valuesFromUserInput.size())) {
            return Map.of("port", "8080");
        }

        Map<String, String> returnForOptions = new HashMap<>();

        for (int i = 0; i < optionsFromUserInput.size(); i++) {
            returnForOptions.put(optionsFromUserInput.get(i), valuesFromUserInput.get(i));
        }

        return returnForOptions;
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
        Map<String, String> optionsToBeUsedOnServer = extractOptionsFromUserInput(args);

        AsyncNetworkServer asyncNetworkServer = new AsyncNetworkServer(Integer.parseInt(optionsToBeUsedOnServer.get("port")));
        asyncNetworkServer.runServer();
    }
}

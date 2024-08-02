package io.valentinsoare.fastwebserver.runner;

import io.valentinsoare.fastwebserver.config.ServerOptionsExecutionTime;
import io.valentinsoare.fastwebserver.services.CustomMetric;
import io.valentinsoare.fastwebserver.outputformat.ColorOutput;
import io.valentinsoare.fastwebserver.services.AsyncNetworkServer;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.PrintWriter;
import java.util.*;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private ServerOptionsExecutionTime serverOptionsExecutionTime;
    private CustomMetric metricService;

    @Autowired
    public ApplicationRunner(ServerOptionsExecutionTime serverOptionsExecutionTime, CustomMetric metricService) {
        this.serverOptionsExecutionTime = serverOptionsExecutionTime;
        this.metricService = metricService;
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
            System.out.printf("%n %sERROR - %s%s%n%n",
                    ColorOutput.ERROR.getTypeOfColor(), e.getMessage(), ColorOutput.OFF_COLOR.getTypeOfColor());
            System.exit(1);
        }

        Map<String, String> returnForOptions = new HashMap<>();

        if (optionsFromUserInput.isEmpty()) {
            return Map.of("port", "8080");
        } else if (!valuesFromUserInput.isEmpty()) {
            for (int i = 0; i < optionsFromUserInput.size(); i++) {
                returnForOptions.put(optionsFromUserInput.get(i), valuesFromUserInput.get(i));
            }
        } else {
            returnForOptions.put("help", "enabled");
        }

        return returnForOptions;
    }

    private int validatePortAsAnOption(String p) {
        try {
            int i = Integer.parseInt(p);

            if (i <= 1024 || i > 65535) {
                throw new NumberFormatException();
            }

            return i;
        } catch (NumberFormatException e) {
            System.out.printf("%n %s %s%s%n%n", ColorOutput.ERROR.getTypeOfColor(),
                    "Port should be a integer value greater than 1024. In this case it will default to 8080!", ColorOutput.OFF_COLOR.getTypeOfColor());
        }

        return 8080;
    }

    private void printHelp(Options options) {
        HelpFormatter helpFormatter = new HelpFormatter();

        try (PrintWriter printWriter = new PrintWriter(System.out)) {
            printWriter.printf("%n%sWelcome!%n", ColorOutput.SUCCESS.getTypeOfColor());

            printWriter.printf("%n%s%n%s%n%n", "Simple webserver written in Java 17 and Spring Boot as an asynchrounous and concurrent application made with Java Non-Blocking IO library.",
                    "Spring Boot modules used: Lombok, Actuator, Micrometer-Registry-Prometheus, Commons-cli`.");
            helpFormatter.printUsage(printWriter, 100, "./fastwebserver [OPTION]...");
            helpFormatter.printOptions(printWriter, 100, options, 2, 5);
            printWriter.printf("%n%s%n%n%s", "FastWebServer was written by Valentin Soare.\nPlease report any bugs to soarevalentinn@gmail.com.", ColorOutput.OFF_COLOR.getTypeOfColor());
        }

        System.exit(0);
    }

    private void runTheNetworkServer(Map<String, String> givenOptions) {
        AsyncNetworkServer asyncNetworkServer = new AsyncNetworkServer(validatePortAsAnOption(givenOptions.get("port")), metricService);
        asyncNetworkServer.runServer();
    }

    private void executeAppWithOptionsGiven(Map<String, String> givenOptions) {
        for (Map.Entry<String, String> option : givenOptions.entrySet()) {
            switch (option.getKey()) {
                case "help" -> printHelp(serverOptionsExecutionTime.getRequiredOptions());
                case "port" -> runTheNetworkServer(givenOptions);
            }
        }
    }

    @Override
    public void run(String... args) {
        Map<String, String> optionsToBeUsedOnServer = extractOptionsFromUserInput(args);
        executeAppWithOptionsGiven(optionsToBeUsedOnServer);
    }
}

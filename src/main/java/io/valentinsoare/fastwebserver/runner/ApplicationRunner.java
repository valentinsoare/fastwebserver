package io.valentinsoare.fastwebserver.runner;

import io.valentinsoare.fastwebserver.auxiliary.PortUtilities;
import io.valentinsoare.fastwebserver.config.ServerOptionsExecutionTime;
import io.valentinsoare.fastwebserver.outputformat.ColorOutput;
import io.valentinsoare.fastwebserver.services.AsyncNetworkServer;
import io.valentinsoare.fastwebserver.services.MetricServices;
import io.valentinsoare.fastwebserver.services.NetworkServices;
import org.apache.commons.cli.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import sun.misc.Signal;

import java.io.PrintWriter;
import java.util.*;
import java.util.List;

@Component
public class ApplicationRunner implements CommandLineRunner {
    private final ServerOptionsExecutionTime serverOptionsExecutionTime;
    private final MetricServices metricService;

    @Autowired
    public ApplicationRunner(ServerOptionsExecutionTime serverOptionsExecutionTime, MetricServices metricService) {
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
        NetworkServices asyncNetworkServer = new AsyncNetworkServer(PortUtilities.validatePortAsAnOption(givenOptions.get("port")), metricService);
        asyncNetworkServer.getAsyncNetworkServer().runServer();
    }

    private void executeAppWithOptionsGiven(Map<String, String> givenOptions) {
        for (Map.Entry<String, String> option : givenOptions.entrySet()) {
            if (option.getKey().equals("port")) {
                runTheNetworkServer(givenOptions);
            } else {
                printHelp(serverOptionsExecutionTime.getRequiredOptions());
            }
        }
    }

    private void handleForceClose() {
        Signal.handle(new Signal("INT"),
                signal -> {
                    System.out.printf("%n%n %s %s%s%n%n",
                            ColorOutput.SUCCESS.getTypeOfColor(), "Exiting...!", ColorOutput.OFF_COLOR.getTypeOfColor());

                    System.out.print("\u001B[?25h");
                    System.exit(0);
                });
    }

    @Override
    public void run(String... args) {
        handleForceClose();

        Map<String, String> optionsToBeUsedOnServer = extractOptionsFromUserInput(args);
        executeAppWithOptionsGiven(optionsToBeUsedOnServer);
    }
}

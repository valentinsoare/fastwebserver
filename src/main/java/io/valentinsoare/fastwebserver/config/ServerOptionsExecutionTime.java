package io.valentinsoare.fastwebserver.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.stereotype.Service;

/**
 * This class is responsible for setting up the options available for the application at execution time.
 * It uses the Apache Commons CLI library to create and manage the options.
 */

@Service
public class ServerOptionsExecutionTime {
    // The Options instance that will hold the options available for the application
    private Options requiredOptions;

    public ServerOptionsExecutionTime() {
        setTheOptionsAvailableForTheApplication();
    }

    /**
     * This method creates an Option instance.
     * An Option represents a command line option.
     *
     * @param shortName The short representation of the option
     * @param longName The long representation of the option
     * @param description The description of the option
     * @return An Option instance
     */
    private Option createOption(String shortName, String longName, String description) {
        return Option.builder(shortName)
                .longOpt(longName)
                .desc(description)
                .required(false)
                .build();
    }

    /**
     * This method sets up the options available for the application.
     * It creates an Option for the port and adds it to the requiredOptions instance.
     */
    private void setTheOptionsAvailableForTheApplication() {
        this.requiredOptions = new Options();

        Option port = createOption("p", "port", "port on which the server will listen for incoming connections");
        requiredOptions.addOption(port);
    }

    /**
     * This method returns the Options instance that holds the options available for the application.
     *
     * @return The Options instance
     */
    public Options getRequiredOptions() {
        return requiredOptions;
    }
}

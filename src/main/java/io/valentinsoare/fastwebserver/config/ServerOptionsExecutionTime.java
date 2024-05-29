package io.valentinsoare.fastwebserver.config;

import org.apache.commons.cli.Option;
import org.apache.commons.cli.Options;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServerOptionsExecutionTime {
    private Options requiredOptions;

    public ServerOptionsExecutionTime() {
        setTheOptionsAvailableForTheApplication();
    }

    private Option createOption(String shortName, String longName, String description) {
        return Option.builder(shortName)
                .longOpt(longName)
                .desc(description)
                .required(false)
                .build();
    }

    private void setTheOptionsAvailableForTheApplication() {
        this.requiredOptions = new Options();

        Option port = createOption("p", "--port", "port on which the server will listen for incoming connections");
        requiredOptions.addOption(port);
    }

    public Options getRequiredOptions() {
        return requiredOptions;
    }
}

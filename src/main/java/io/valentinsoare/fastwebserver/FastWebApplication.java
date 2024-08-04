package io.valentinsoare.fastwebserver;

import io.valentinsoare.fastwebserver.auxiliary.PortUtilities;
import io.valentinsoare.fastwebserver.outputformat.ColorOutput;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.server.WebServerFactoryCustomizer;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.StandardEnvironment;


@SpringBootApplication
public class FastWebApplication {
    public static final StandardEnvironment environment = new StandardEnvironment();
    public static int actuatorPort = 0;

    static {
        actuatorPort = Integer.parseInt(environment.getProperty("server.port", "8181"));
    }

    public static void main(String[] args) {
        System.out.print("\u001B[?25l");

        if (!PortUtilities.isPortAvailable(actuatorPort)) {
            System.out.printf("%n %sERROR: Actuator port %s is already in use. Trying to find an available port!%s%n%n",
                    ColorOutput.ERROR.getTypeOfColor(), actuatorPort, ColorOutput.OFF_COLOR.getTypeOfColor());

            while (!PortUtilities.isPortAvailable(actuatorPort)) {
                actuatorPort++;
            }

            System.setProperty("server.port", String.valueOf(actuatorPort));
        }

        SpringApplication.run(FastWebApplication.class, args);
    }

    @Bean
    public WebServerFactoryCustomizer<TomcatServletWebServerFactory> serverFactoryCustomizer() {
        return factory -> {
            int port = Integer.parseInt(System.getProperty("server.port", "8181"));
            factory.setPort(port);
        };
    }
}

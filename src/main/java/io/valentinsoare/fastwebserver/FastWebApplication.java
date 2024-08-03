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
    public static StandardEnvironment environment = new StandardEnvironment();
    public static int actPort = 0;

    public static void main(String[] args) {
        actPort = Integer.parseInt(environment.getProperty("server.port", "8181"));

        if (!PortUtilities.isPortAvailable(actPort)) {
            System.out.printf("%n %sERROR: Actuator port %s is already in use. Trying to find an available port!%s%n%n",
                    ColorOutput.ERROR.getTypeOfColor(), actPort, ColorOutput.OFF_COLOR.getTypeOfColor());

            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {}

            while (!PortUtilities.isPortAvailable(actPort)) {
                actPort++;
            }

            System.setProperty("server.port", String.valueOf(actPort));
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

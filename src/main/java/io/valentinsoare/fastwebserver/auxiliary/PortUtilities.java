package io.valentinsoare.fastwebserver.auxiliary;

import io.valentinsoare.fastwebserver.outputformat.ColorOutput;

import java.io.IOException;
import java.net.ServerSocket;

public class PortUtilities {

    private static PortUtilities portUtilities;

    private PortUtilities() {}

    public int validatePortAsAnOption(String p) {
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

    public boolean isPortAvailable(int port) {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            serverSocket.setReuseAddress(true);
            return true;
        } catch (IOException e) {
            return false;
        }
    }

    public static PortUtilities getPortUtilities() {
        if (portUtilities == null) {
            portUtilities = new PortUtilities();
        }

        return portUtilities;
    }
}

package io.valentinsoare.fastwebserver.auxiliary;

public abstract class NetworkUtilities implements Utilities {

    NetworkUtilities() {}

    abstract PortUtilities getPortUtilities();

    @Override
    public NetworkUtilities getNetworkUtilities() {
        return this;
    }
}

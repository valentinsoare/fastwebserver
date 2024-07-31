package io.valentinsoare.fastwebserver.outputformat;

public enum ColorOutput {
    OFF_COLOR("\033[0m"),
    DEBUG("\033[36m"),
    INFO("\033[34m"),
    WARN("\033[1;33m"),
    ERROR("\033[1;31m"),
    FATAL("\033[1;31m"),
    SUCCESS("\033[32m");

    private final String typeOfColor;

    ColorOutput(String typeOfColor) {
        this.typeOfColor = typeOfColor;
    }

    public String getTypeOfColor() {
        return typeOfColor;
    }
}

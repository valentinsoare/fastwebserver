package io.valentinsoare.fastwebserver.typeofoutput.exceptionsanderrors;

import io.valentinsoare.fastwebserver.outputformat.ColorOutput;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private String severity;
    private String clazzName;
    private String methodName;
    private String threadName;
    private String message;
    private String dateTime;

    public ErrorMessage() {}

    public ErrorMessage(String severity, String clazzName, String methodName,
                        String threadName, String message, String dateTime) {
        this.severity = severity;
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.threadName = threadName;
        this.message = message;
        this.dateTime = dateTime;
    }

    public void changeColorSeverity(ColorOutput color) {
        this.severity = String.format("%s%s%s", color.getTypeOfColor(), severity, ColorOutput.OFF_COLOR);
    }

    public void changeColorClazzNameAndMethodName(ColorOutput color) {
        this.clazzName = String.format("%s%s%s", color.getTypeOfColor(), clazzName, ColorOutput.OFF_COLOR);
        this.methodName = String.format("%s%s%s",color.getTypeOfColor(), methodName, ColorOutput.OFF_COLOR);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s, %s",
                dateTime, severity, clazzName, methodName, threadName, message);
    }
}

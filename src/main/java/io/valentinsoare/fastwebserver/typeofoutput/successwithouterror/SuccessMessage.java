package io.valentinsoare.fastwebserver.typeofoutput.successwithouterror;

import io.valentinsoare.fastwebserver.outputformat.ColorOutput;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class SuccessMessage {
    private String clazzName;
    private String methodName;
    private String threadName;
    private String message;
    private String dateTime;

    public SuccessMessage() {}

    public SuccessMessage(String clazzName, String methodName,
                          String threadName, String message, String dateTime) {
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.threadName = threadName;
        this.message = message;
        this.dateTime = dateTime;
    }

    public void changeColorClazzNameAndMethodName(ColorOutput color) {
        this.clazzName = String.format("%s%s%s", color.getTypeOfColor(), clazzName, ColorOutput.OFF_COLOR);
        this.methodName = String.format("%s%s%s", color.getTypeOfColor(), methodName, ColorOutput.OFF_COLOR);
    }

    @Override
    public String toString() {
        return String.format("%s, %s, %s, %s, %s",
                dateTime, clazzName, methodName, threadName, message);
    }
}

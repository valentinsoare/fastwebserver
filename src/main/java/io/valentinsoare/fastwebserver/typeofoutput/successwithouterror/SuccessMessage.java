package io.valentinsoare.fastwebserver.typeofoutput.successwithouterror;

import io.valentinsoare.fastwebserver.typeofoutput.exceptionsanderrors.Severity;
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

    @Override
    public String toString() {
        return String.format("ClazzName: %s, MethodName: %s, ThreadName: %s, Message: %s, DateTime: %s",
                clazzName, methodName, threadName, message, dateTime);
    }
}

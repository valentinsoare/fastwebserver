package io.valentinsoare.fastwebserver.typeofoutput.exceptionsanderrors;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ErrorMessage {
    private Severity severity;
    private String clazzName;
    private String methodName;
    private String threadName;
    private String message;
    private String dateTime;

    public ErrorMessage() {}

    public ErrorMessage(Severity severity, String clazzName, String methodName,
                        String threadName, String message, String dateTime) {
        this.severity = severity;
        this.clazzName = clazzName;
        this.methodName = methodName;
        this.threadName = threadName;
        this.message = message;
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return String.format("Severity: %s, ClazzName: %s, MethodName: %s, ThreadName: %s, Message: %s, DateTime: %s",
                severity, clazzName, message, threadName, message, dateTime);
    }
}

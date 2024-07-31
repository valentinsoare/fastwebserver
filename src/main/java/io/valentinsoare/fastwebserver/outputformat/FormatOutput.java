package io.valentinsoare.fastwebserver.outputformat;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import io.valentinsoare.fastwebserver.typeofoutput.exceptionsanderrors.ErrorMessage;
import io.valentinsoare.fastwebserver.typeofoutput.successwithouterror.SuccessMessage;
import org.springframework.stereotype.Component;

@Component
public class FormatOutput {
    private ObjectMapper jsonStyle;

    public FormatOutput() {}

    public ObjectMapper styleAsJSON() {
        if (jsonStyle == null) {
            this.jsonStyle = new ObjectMapper();
            jsonStyle.registerModule(new JavaTimeModule());
        }

        return jsonStyle;
    }

    public ErrorMessage debugColorStyle(ErrorMessage errorMessage) {
        errorMessage.changeColorSeverity(ColorOutput.DEBUG);
        errorMessage.changeColorClazzNameAndMethodName(ColorOutput.DEBUG);

        return errorMessage;
    }

    public ErrorMessage infoColorStyle(ErrorMessage errorMessage) {
        errorMessage.changeColorSeverity(ColorOutput.INFO);
        errorMessage.changeColorClazzNameAndMethodName(ColorOutput.INFO);

        return errorMessage;
    }

    public ErrorMessage warnColorStyle(ErrorMessage errorMessage) {
        errorMessage.changeColorSeverity(ColorOutput.WARN);
        errorMessage.changeColorClazzNameAndMethodName(ColorOutput.WARN);

        return errorMessage;
    }

    public ErrorMessage errorColorStyle(ErrorMessage errorMessage) {
        errorMessage.changeColorSeverity(ColorOutput.ERROR);
        errorMessage.changeColorClazzNameAndMethodName(ColorOutput.ERROR);

        return errorMessage;
    }

    public ErrorMessage fatalColorStyle(ErrorMessage errorMessage) {
        errorMessage.changeColorSeverity(ColorOutput.FATAL);
        errorMessage.changeColorClazzNameAndMethodName(ColorOutput.FATAL);

        return errorMessage;
    }

    public SuccessMessage successColorStyle(SuccessMessage successMessage) {
        successMessage.changeColorClazzNameAndMethodName(ColorOutput.SUCCESS);

        return successMessage;
    }
}

package enums;

import org.apache.commons.codec.binary.StringUtils;

public enum ErrorMessageEnums {

    WRONG_OTP_ENTERED("Wrong OTP entered"),
    TRY_AGAIN_CORRECT_CREDENTIALS("please try again with correct credentials.");



    private final String text;

    ErrorMessageEnums(String value) {
        this.text = value;
    }

    public String value() {
        return text;
    }

    public static ErrorMessageEnums getByValue(String value) {
        for (ErrorMessageEnums errorMessageEnums : values()) {
            if (StringUtils.equals(errorMessageEnums.value(), value)) {
                return errorMessageEnums;
            }
        }
        throw new RuntimeException("Failed to find Error message");
    }
}

package enums;


import org.apache.commons.codec.binary.StringUtils;

public enum LocatorsEnum {

    XPATH("xpath"),
    NAME("name"),
    ACCESSIBILITY_ID("AccessibilityId"),
    CLASS_NAME("className"),
    ID("id"),
    LINK_TEXT("linkText"),
    PARTIAL_LINK_TEXT("partialLinkText");


    private final String attributes;

    LocatorsEnum(String value) {
        this.attributes = value;
    }

    public String value() {
        return attributes;
    }

    public static LocatorsEnum getByValue(String value) {
        for (LocatorsEnum locatorsEnum : values()) {
            if (StringUtils.equals(locatorsEnum.value(), value)) {
                return locatorsEnum;
            }
        }
        throw new RuntimeException("Failed to find locator type");
    }
}

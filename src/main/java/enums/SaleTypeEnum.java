package enums;

import org.apache.commons.codec.binary.StringUtils;

public enum SaleTypeEnum {

    NEW_PROPERTIES("New Properties"),
    RESALE_PROPERTIES("Resale Properties");


    private final String value;

    SaleTypeEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static SaleTypeEnum getByValue(String value) {
        for (SaleTypeEnum saleTypeEnum : values()) {
            if (StringUtils.equals(saleTypeEnum.value(), value)) {
                return saleTypeEnum;
            }
        }
        throw new RuntimeException("Failed to find saleType Type");
    }
}

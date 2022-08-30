package enums;

import org.apache.commons.codec.binary.StringUtils;

public enum BhkEnum{

    ONERK("1"),
    ONEBHK("2"),
    TWOBHK("3"),
    THREEBHK("4"),
    THREEPLUSBHK("5");


    private final String value;

    BhkEnum(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static BhkEnum getByValue(String value) {
        for (BhkEnum bhkEnum : values()) {
            if (StringUtils.equals(bhkEnum.value(), value)) {
                return bhkEnum;
            }
        }
        throw new RuntimeException("Failed to find Bhk Type");
    }
}

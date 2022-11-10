package enums;

import org.apache.commons.codec.binary.StringUtils;

public enum BhkEnum{

    ONERK("1 RK"),
    ONEBHK("1 BHK"),
    TWOBHK("2 BHK"),
    THREEBHK("3 BHK"),
    THREEPLUSBHK("3+ BHK");


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

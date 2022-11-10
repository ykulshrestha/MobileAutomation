package enums;

import org.apache.commons.codec.binary.StringUtils;

public enum MyActivityEnums {

    SAVED_PROPERTIES(0),
    SEEN_PROPERTIES(1),
    CONTACTED_PROPERTIES(2),
    SEARCHED_PROPERTIES(3);


    private final int attributes;

    MyActivityEnums(int value) {
        this.attributes = value;
    }

    public int value() {
        return attributes;
    }

    public static MyActivityEnums getByValue(int value) {
        for (MyActivityEnums myActivityEnums : values()) {
            if (myActivityEnums.value()== value) {
                return myActivityEnums;
            }
        }
        throw new RuntimeException("Failed to find locator type");
    }
}

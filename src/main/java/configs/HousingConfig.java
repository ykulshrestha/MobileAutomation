package configs;

import constants.Constant;
import util.ConfigUtil;

import java.util.Map;

public class HousingConfig {
    public static Map<String,String> readHousingProperties(){
        ConfigUtil configUtil = new ConfigUtil();
        Map<String,String> housingPropetiesMap = configUtil.readProperties(Constant.PATH_TO_HOUSING_PROPERTIES);
        return housingPropetiesMap;
    }
}

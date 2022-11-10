package configs;

import constants.Constant;
import util.ConfigUtil;

import java.util.Map;

public class MoengageConfig {

    public static Map<String,String> readMoengageProperties(){
        ConfigUtil configUtil = new ConfigUtil();
        Map<String,String> moengagePropetiesMap = configUtil.readProperties(Constant.PATH_TO_MOENGAGE_PROPERTIES);
        return moengagePropetiesMap;
    }
}

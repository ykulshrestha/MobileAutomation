package configs;

import constants.Constant;
import util.ConfigUtil;

import java.util.Map;

public class QuickbloxConfig {

    public static Map<String,String> readQuickBloxProperties(){
        ConfigUtil configUtil = new ConfigUtil();
        Map<String,String> quickbloxPropetiesMap = configUtil.readProperties(Constant.PATH_TO_QUICKBLOX_PROPERTIES);
        return quickbloxPropetiesMap;
    }
}

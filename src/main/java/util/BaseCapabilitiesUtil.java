package util;

import constants.Constant;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.IOSMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.util.Map;

public class BaseCapabilitiesUtil {

    private static final Logger logger = LogManager.getLogger(BaseCapabilitiesUtil.class);

    public Map capabilityMap;

  //This functions is setting the base capabilities for a device
    public DesiredCapabilities  setCapability(){
        DesiredCapabilities desiredCapabilities = new DesiredCapabilities();
        capabilityMap = new ConfigUtil().readProperties(Constant.CAPABILITIES);
        logger.info("Setting capabilities for appium driver: ");
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, capabilityMap.get("NO_RESET"));
        if (capabilityMap.get("PLATFORM_NAME").equals("Android"))
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, capabilityMap.get("AUTO_GRANT_PERMISSIONS"));
        if (capabilityMap.get("PLATFORM_NAME").equals("ios"))
            desiredCapabilities.setCapability(IOSMobileCapabilityType.AUTO_ACCEPT_ALERTS, "true"); //to accept all alerts
        logger.info("{} = {}",AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, capabilityMap.get("AUTO_GRANT_PERMISSIONS"));

        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, capabilityMap.get("FULL_RESET"));
//        desiredCapabilities.setCapability(MobileCapabilityType.CLEAR_SYSTEM_FILES, "true");
        logger.info("{} = {}",MobileCapabilityType.FULL_RESET, capabilityMap.get("FULL_RESET"));
        return desiredCapabilities;
    }


}
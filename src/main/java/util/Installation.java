package util;

import constants.Constant;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.AndroidMobileCapabilityType;
import io.appium.java_client.remote.MobileCapabilityType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;
import java.util.Objects;

public class Installation {

    private static final Logger logger = LogManager.getLogger(Installation.class);

    public DesiredCapabilities desiredCapabilities;
    public Map capabilityMap;

  //This functions is setting the capabilities for a device
    DesiredCapabilities  setCapability(){
        capabilityMap = new ConfigUtil().readProperties(Constant.CAPABILITIES);
        desiredCapabilities = new DesiredCapabilities();
        logger.info("Setting capabilities for appium driver: ");
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, capabilityMap.get("AUTOMATION_NAME"));
        logger.info("{} = {}", MobileCapabilityType.AUTOMATION_NAME, capabilityMap.get("AUTOMATION_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, capabilityMap.get("PLATFORM_VERSION"));
        logger.info("{} = {}",MobileCapabilityType.PLATFORM_VERSION, capabilityMap.get("PLATFORM_VERSION"));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, capabilityMap.get("PLATFORM_NAME"));
        logger.info("{} = {}",MobileCapabilityType.PLATFORM_NAME, capabilityMap.get("PLATFORM_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, capabilityMap.get("DEVICE_NAME"));
        logger.info("{} = {}",MobileCapabilityType.DEVICE_NAME, capabilityMap.get("DEVICE_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+ capabilityMap.get("APP"));
        logger.info("{} = {}",MobileCapabilityType.APP, System.getProperty("user.dir")+ capabilityMap.get("APP"));
        desiredCapabilities.setCapability("avd", capabilityMap.get("AVD"));
        logger.info("{} = {}","avd", capabilityMap.get("AVD"));
        desiredCapabilities.setCapability(MobileCapabilityType.NO_RESET, capabilityMap.get("NO_RESET"));
        logger.info("{} = {}",MobileCapabilityType.NO_RESET, capabilityMap.get("NO_RESET"));
        if (capabilityMap.get("PLATFORM_NAME").equals("Android"))
        desiredCapabilities.setCapability(AndroidMobileCapabilityType.AUTO_GRANT_PERMISSIONS, capabilityMap.get("AUTO_GRANT_PERMISSIONS"));
        //TODO: Add handling for IOS
        desiredCapabilities.setCapability(MobileCapabilityType.FULL_RESET, capabilityMap.get("FULL_RESET"));
        logger.info("{} = {}",MobileCapabilityType.FULL_RESET, capabilityMap.get("FULL_RESET"));

        return desiredCapabilities;
    }

    // This functions returns a driver based on the platform type i.e. android driver or ios driver
    public AppiumDriver getDriver()
    {
        AppiumDriver appiumDriver = null;
        DesiredCapabilities desiredCapabilities = setCapability();
        logger.info("Platform type is "+ capabilityMap.get("PLATFORM_NAME") + ", creating "+ capabilityMap.get("PLATFORM_NAME") + "driver");
        if (capabilityMap.get("PLATFORM_NAME").equals("Android"))
        try {
            appiumDriver = new AndroidDriver<MobileElement>(new URL(new ConfigUtil().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), desiredCapabilities);
        } catch (Exception e) {
            logger.error("Error occurred while creating Android driver {} ", e.getMessage());
            e.printStackTrace();
        }
        if (capabilityMap.get("PLATFORM_NAME").equals("ios"))
            try {
                appiumDriver = new IOSDriver<MobileElement>(new URL(new ConfigUtil().readProperties(Constant.APPIUM_PROPERTIES).get("url").toString()), setCapability());

            } catch (MalformedURLException e) {
                logger.error("Error occurred while creating ios driver {} ", e.getMessage());
                e.printStackTrace();
            }
        if (Objects.nonNull(appiumDriver))
            logger.info( capabilityMap.get("PLATFORM_NAME") + " driver created successfully");
        return appiumDriver;
    }



}
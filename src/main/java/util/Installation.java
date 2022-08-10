package util;

import configs.AppiumConfig;
import configs.PlatformConfig;
import constants.Constant;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

public class Installation {


    public DesiredCapabilities desiredCapabilities;
    public Map capabilityMap;

  //This functions is setting the capabilities for a device
    DesiredCapabilities  setCapability(){
        capabilityMap = new PlatformConfig().readProperties();
        desiredCapabilities = new DesiredCapabilities();
        desiredCapabilities.setCapability(MobileCapabilityType.AUTOMATION_NAME, capabilityMap.get("AUTOMATION_NAME"));
//        desiredCapabilities.setCapability(MobileCapabilityType.PL, capabilityMap.get("PLATFORM_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, capabilityMap.get("PLATFORM_VERSION"));
        desiredCapabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, capabilityMap.get("PLATFORM_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.DEVICE_NAME, capabilityMap.get("DEVICE_NAME"));
        desiredCapabilities.setCapability(MobileCapabilityType.APP, System.getProperty("user.dir")+ capabilityMap.get("APP"));

        return desiredCapabilities;
    }

    // This functions returns a driver based on the platform type i.e. android driver or ios driver
    public AppiumDriver getDriver()
    {
        DesiredCapabilities desiredCapabilities = setCapability();
        if (capabilityMap.get("PLATFORM_NAME").equals("Android"))
        try {
            return new AndroidDriver<MobileElement>(new URL(new AppiumConfig().readProperties().get("url").toString()), desiredCapabilities);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        if (capabilityMap.get("PLATFORM_NAME").equals("ios"))
            try {
                return new IOSDriver<MobileElement>(new URL(new AppiumConfig().readProperties().get("url").toString()), setCapability());

            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return null;
    }



}
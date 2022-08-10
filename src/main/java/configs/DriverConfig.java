package configs;

import io.appium.java_client.AppiumDriver;

public class DriverConfig {

    private static AppiumDriver driver;

    public static AppiumDriver getDriver() {
        return driver;
    }

    public static void setDriver(AppiumDriver appiumDriver) {
        driver = appiumDriver;
    }
}

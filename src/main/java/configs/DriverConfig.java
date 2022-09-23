package configs;

import io.appium.java_client.AppiumDriver;

public class DriverConfig {

    private static ThreadLocal<AppiumDriver> driver = new ThreadLocal<>();

    public static synchronized AppiumDriver getDriver() {
        return driver.get();
    }

    public static void setDriver(AppiumDriver appiumDriver) {
        driver.set(appiumDriver);
    }
}

package pages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

import java.util.Objects;

@Getter
public class Tabs {

    @AndroidFindBy(accessibility = "home_profileButton")
    private MobileElement profileTab;

    @AndroidFindBy(accessibility = "home_servicesButton")
    private MobileElement EdgeServiceTab;

    @AndroidFindBy(accessibility = "home_dealsButton")
    private MobileElement dealsTab;

    @AndroidFindBy(accessibility = "home_pay on creditButton")
    private MobileElement payOnCredit;

    @AndroidFindBy(accessibility = "home_searchButton")
    private MobileElement search;

     public Tabs() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

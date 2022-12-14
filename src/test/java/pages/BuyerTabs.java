package pages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class BuyerTabs {

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

     public BuyerTabs() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

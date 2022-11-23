package pages.onboarding;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;


@Getter
public class WelcomePage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home search']")
    private MobileElement HomeSearch;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Pay on Credit']")
    private MobileElement PayOnCredit;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Post your property']")
    private MobileElement PostYourProperty;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Home related services']")
    private MobileElement HomeRelatedServices;


    public WelcomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
}

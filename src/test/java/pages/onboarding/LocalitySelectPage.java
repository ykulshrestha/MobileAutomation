package pages.onboarding;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class LocalitySelectPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Skip and go to home page']")
    private MobileElement skip;

    public LocalitySelectPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

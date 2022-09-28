package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import modals.LoginModal;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage {


    @AndroidFindBy(accessibility = "Buy")
    private MobileElement buy;

    @AndroidFindBy(accessibility = "Login-Now-CTA")
    private MobileElement loginNowButton;



    public HomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
}

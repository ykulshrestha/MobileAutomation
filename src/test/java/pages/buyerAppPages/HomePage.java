package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import modals.LoginModal;
import org.openqa.selenium.support.PageFactory;
import pages.Tabs;

@Getter
public class HomePage {

    private Tabs tabs;
    private LoginModal loginModal;


    @AndroidFindBy(accessibility = "Buy")
    private MobileElement buy;


    public HomePage() {
        loginModal = new LoginModal();
        tabs = new Tabs();
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
}

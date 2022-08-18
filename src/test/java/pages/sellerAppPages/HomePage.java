package pages.sellerAppPages;

import configs.DriverConfig;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import org.openqa.selenium.support.PageFactory;
import pages.ChatFloatingCta;

public class HomePage extends ChatFloatingCta {

    public HomePage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

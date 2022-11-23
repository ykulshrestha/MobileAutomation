package pages.onboarding;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class CategoryPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Buy']")
    private MobileElement Buy;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Rent']")
    private MobileElement Rent;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='P.G.']")
    private MobileElement Pg;


    public CategoryPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }


}

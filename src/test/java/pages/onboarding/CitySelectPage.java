package pages.onboarding;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class CitySelectPage {

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Type city here...']")
    private MobileElement cityTextBox;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'filter_localitySelect')]/android.view.ViewGroup/android.widget.TextView[1]")
    private List<MobileElement> elements;


    public CitySelectPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

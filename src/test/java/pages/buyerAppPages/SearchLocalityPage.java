package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class SearchLocalityPage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.widget.EditText")
    private MobileElement SearchTextBox;

    @AndroidFindBy(accessibility = "results_searchLocality")
    private MobileElement SearchButton;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[contains(@content-desc,'filter_localitySelect')]/android.view.ViewGroup/android.widget.TextView[1]")
    private List<MobileElement> searchedProjectList;


    public SearchLocalityPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
}
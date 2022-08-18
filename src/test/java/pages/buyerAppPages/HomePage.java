package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class HomePage {


    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[4]/android.view.ViewGroup[1]/android.widget.HorizontalScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup")
    private MobileElement buy;

    /////////////////////////Tabs on HomePage/////////////////////////////
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

    /////////////////////////////////////////////////////////////////////

    public HomePage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
}

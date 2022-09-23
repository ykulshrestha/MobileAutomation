package pages.buyerAppPages;

import configs.DriverConfig;
import enums.BhkEnum;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import pages.Tabs;
import util.ActionUtils;

@Getter
public class FilterPage {

    @AndroidFindBy(xpath = "((/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup/android.widget.HorizontalScrollView)[3]/android.view.ViewGroup")
    private MobileElement Bhk;

    @AndroidFindBy(accessibility = "filter_viewProperties_button")
    private MobileElement viewProperties;

    public FilterPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
    public void setBhk(String index){
        String bhkType = "(//android.view.ViewGroup[" + index + "]/android.view.ViewGroup/android.widget.ImageView)";
        ActionUtils.clickButton(Bhk.findElement(MobileBy.xpath(bhkType)));
    }
}

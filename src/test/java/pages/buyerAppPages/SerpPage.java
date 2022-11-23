package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import modals.LoginModal;
import org.openqa.selenium.support.PageFactory;
import util.ActionUtils;

import java.util.List;

//TODO: Replace xpath with id, getting header also in list from used xpath
@Getter
public class SerpPage {

    @AndroidFindBy(xpath = "((/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[1]/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> propertyCards;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Profile']")
    private MobileElement profileFloatingCta;

    public MobileElement getContactCTA (MobileElement propertyCard){
        return ActionUtils.scroll((MobileElement) propertyCard.findElement(MobileBy.xpath("//android.widget.TextView[@text='Contact']")),"up");
    }

    public MobileElement getProjectName (MobileElement propertyCard){
        return ActionUtils.scroll((MobileElement) propertyCard.findElement(MobileBy.xpath("//android.widget.TextView[@text='Contact']")),"up");
    }

    public SerpPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

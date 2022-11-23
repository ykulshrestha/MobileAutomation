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

@Getter
public class DetailsPage {

    @AndroidFindBy(accessibility = "project_detail_chat_now")
    private MobileElement chatNow;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Chat for Details']")
    private MobileElement chatForDetails;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@content-desc=\"_chatButton\"]/android.view.ViewGroup/android.widget.ImageView")
    private MobileElement chatHeaderIcon;

    @AndroidFindBy(xpath = "//android.widget.TextView[@index='4']")
    private MobileElement propertyName;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@index='10']/android.widget.TextView[1]")
    private MobileElement sellerName;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Ask Location Information']")
    private MobileElement askForLocation;

    //TODO: use list once accessibility id is added
    @AndroidFindBy(xpath = "((/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[2]/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup)[3]")
    private MobileElement chatFloatingCta;

    //TODO: use list once accessibility id is added
    @AndroidFindBy(xpath = "//android.view.ViewGroup[@bounds='[135,2031][473,2121]']")
    private MobileElement housingChatInBottomTray;

    public DetailsPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }




}

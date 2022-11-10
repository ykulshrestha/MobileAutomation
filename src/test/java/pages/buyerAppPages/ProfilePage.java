package pages.buyerAppPages;

import configs.DriverConfig;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import modals.LoginModal;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ProfilePage {


    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup[3]")
    private MobileElement loginButton;

    @AndroidFindBy(xpath = "(((/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup)[1]/android.widget.FrameLayout/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup" +
            "/android.view.ViewGroup)[2]/android.view.ViewGroup)[1]/android.view.ViewGroup")
    private List<MobileElement> myActivityTab;

    @AndroidFindBy(xpath = "((((/hierarchy/android.widget.FrameLayout/" +
            "android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/" +
            "android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
            "/android.view.ViewGroup)[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout" +
            "/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/" +
            "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[1]/" +
            "android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup" +
            "/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup)[2]" +
            "/android.view.ViewGroup)[3]/android.widget.HorizontalScrollView/android.view.ViewGroup" +
            "/android.view.ViewGroup")
    private List<MobileElement> myActivityTabProperties;

    public MobileElement getChatCta(int index){
        return myActivityTabProperties.get(index).findElement(MobileBy.xpath("//(android.view.ViewGroup)[2]/android.view.ViewGroup"));
    }


    public ProfilePage() {

        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

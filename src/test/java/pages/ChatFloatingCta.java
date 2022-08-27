package pages;

import configs.DriverConfig;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.ActionUtils;

@Getter
public class ChatFloatingCta {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup")
    private MobileElement floatingCta;

    public boolean isUnreadMessageExist(){
        String name = "//android.view.ViewGroup[@index='1']";
        WebDriverWait webDriverWait = new WebDriverWait(DriverConfig.getDriver(), 6000);
        try {
            webDriverWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(floatingCta, MobileBy.xpath(name)));
            return true;
        }catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public ChatFloatingCta(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

}

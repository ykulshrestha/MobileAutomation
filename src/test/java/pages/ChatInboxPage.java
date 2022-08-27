package pages;

import configs.DriverConfig;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ChatInboxPage {

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> chatThreads;

//    private MobileElement unreadCount;
//    private MobileElement name;
//    private MobileElement propertyDetails;

    public MobileElement getUnreadCount(int index){
        WebDriverWait webDriverWait = new WebDriverWait(DriverConfig.getDriver(), 1000);
        String unreadCount = "(//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='5'])";
        webDriverWait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(chatThreads.get(index), By.xpath(unreadCount)));
        return chatThreads.get(index).findElement(MobileBy.xpath(unreadCount));
    }

    public MobileElement getName(int index){
        String name = "(//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])[1]";
        return chatThreads.get(index).findElement(MobileBy.xpath(name));
    }

    public MobileElement getPropertyDetails(int index){
        String propertyDetails = "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView";
        return chatThreads.get(index).findElement(MobileBy.xpath(propertyDetails));
    }


    public ChatInboxPage(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }


    public List<MobileElement> getUnreadThreads(){
        List<MobileElement> unreadList = new ArrayList<>();
        for (int i=0; i < chatThreads.size(); i++){
            MobileElement mobileElement = chatThreads.get(i).findElement(By.xpath("/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[1]/android.view.ViewGroup/android.view.ViewGroup/[contains(@content-desc,'android.view.ViewGroup')]/android.widget.TextView"));
                if (mobileElement != null)
                    unreadList.add(chatThreads.get(i));
        }
        return unreadList;
    }
}

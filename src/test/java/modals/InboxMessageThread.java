package modals;

import configs.DriverConfig;
import io.appium.java_client.MobileDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import pages.ChatInboxPage;
import util.ContextUtil;

import java.util.List;

//TODO: use this class for child elements
@Getter
public class InboxMessageThread {

//    @AndroidFindBy(xpath = "/android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[1]")
//    private MobileElement name;

//    @AndroidFindBy(xpath = "(//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[3]")
//    private MobileElement unreadCount;

//    @AndroidFindBy(xpath = "(.//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])[1]")
//    private MobileElement name;
//
//    @AndroidFindBy(xpath = ".(//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])[1]")
//    private MobileElement name2;
    @AndroidFindBy(xpath = ".//(android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])[1]")
    private MobileElement name3;

    public InboxMessageThread(ChatInboxPage chatInboxPage){
       ContextUtil parentContext = new ContextUtil(chatInboxPage.getChatThreads());
        chatInboxPage.getChatThreads().get(0).findElement(By.xpath("(//android.view.ViewGroup/android.view.ViewGroup/" +
                "android.widget.TextView[@index='2'])[1]"));
        PageFactory.initElements(parentContext, this);
        System.out.println("-");
    }


}

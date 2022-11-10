package pages;

import apiResponse.chatDialogResponse.ChatDialogResponse;
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
import org.testng.Assert;
import util.ActionUtils;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Getter
public class ChatInboxPage {

    @AndroidFindBy(xpath = "(/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> chatThreads;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup/android.view.ViewGroup/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private MobileElement back;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='My Chats']")
    private MobileElement myChatsText;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Archived Chats']")
    private MobileElement archivedChats;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Explore More Properties']")
    private MobileElement exploreMoreProperties;

    public MobileElement getUnreadCount(int index){
        String unreadCount = "(//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='5'])";
        return chatThreads.get(index).findElement(MobileBy.xpath(unreadCount));
    }

    public boolean waitForVisibilityOfUnreadCount(int index, String senderName, int timeOutInSeconds){
        ChatFloatingCta chatFloatingCta = new ChatFloatingCta();
        String unreadCount = "(//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[@index='5'])";
        String name = "(//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])[1]";
        long start = System.currentTimeMillis();
        long end = start + timeOutInSeconds * 1000;
        while ((chatThreads.get(index).findElements(MobileBy.xpath(unreadCount)).size() == 0
        || !chatThreads.get(index).findElement(MobileBy.xpath(name)).getText().equalsIgnoreCase(senderName))
                && System.currentTimeMillis() < end){
            try {
                Thread.sleep(25000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            ActionUtils.clickButton(this.getBack());
            ActionUtils.clickButton(chatFloatingCta.getFloatingCta());
        }
        if (chatThreads.get(index).findElements(MobileBy.xpath(unreadCount)).size() > 0
                && chatThreads.get(index).findElement(MobileBy.xpath(name)).getText().equalsIgnoreCase(senderName))
            return true;
        return false;
    }

    public MobileElement getName(int index){
        String name = "(//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[@index='2'])";
        if (chatThreads.get(index).findElements(MobileBy.xpath(name)).size()==0)
            return null;
            return chatThreads.get(index).findElement(MobileBy.xpath(name));
    }

    public MobileElement getPropertyDetails(int index){
        String propertyDetails = "//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[@index='1']";
        if (chatThreads.get(index).findElements(MobileBy.xpath(propertyDetails)).size()==0)
            return null;
        return chatThreads.get(index).findElement(MobileBy.xpath(propertyDetails));
    }

    public MobileElement getLastMessage(int index){
        String lastMessage = "//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[3][@index='4']";
        if (chatThreads.get(index).findElements(MobileBy.xpath(lastMessage)).size()==0)
            return null;
        return chatThreads.get(index).findElement(MobileBy.xpath(lastMessage));
    }

    public void verifyChatCardData(ChatDialogResponse chatDialogResponse, String role){
        for (int i=0 ;i<this.getChatThreads().size(); i++){
            if (this.getName(i)== null
                    && this.getPropertyDetails(i)== null
                    && this.getLastMessage(i)== null)
                    return;
            if (role.equalsIgnoreCase("SELLER"))
                Assert.assertEquals(this.getName(i).getText(), chatDialogResponse.getItems().get(i).getData().getBuyerName());
            else
            Assert.assertEquals(this.getName(i).getText(), chatDialogResponse.getItems().get(i).getData().getSellerName());
//            Assert.assertEquals(chatDialogResponse.getItems().get(i).getName(), chatDialogResponse.getItems().get(i).getName());
            Assert.assertEquals(this.getPropertyDetails(i).getText(), chatDialogResponse.getItems().get(i).getName());
//            Assert.assertEquals(chatDialogResponse.getItems().get(i).getLast_message(), chatDialogResponse.getItems().get(i).getLast_message());
            Assert.assertEquals(this.getLastMessage(i).getText(), chatDialogResponse.getItems().get(i).getLast_message());
        }
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

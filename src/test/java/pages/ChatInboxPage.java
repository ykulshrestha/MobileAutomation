package pages;

import apiResponse.chatDialogResponse.ChatDialogResponse;
import apiResponse.chatDialogResponse.Item;
import configs.DriverConfig;
import enums.ChatUserEnums;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
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
            return chatThreads.get(index).findElement(MobileBy.xpath(name));
    }

    public MobileElement getPropertyDetails(int index){
        String propertyDetails = "//android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup[1]/android.widget.TextView[@index='1']";
        return chatThreads.get(index).findElement(MobileBy.xpath(propertyDetails));
    }

    public MobileElement getLastMessage(int index){
        String lastMessage = "//android.view.ViewGroup/android.view.ViewGroup/android.widget.TextView[3][@index='4']";
        return chatThreads.get(index).findElement(MobileBy.xpath(lastMessage));
    }

    public void verifyChatCardDataInView(ChatDialogResponse chatDialogResponse, String role){
        for (int i=0 ;i<this.getChatThreads().size(); i++){
            if (this.getName(i)== null
                    && this.getPropertyDetails(i)== null
                    && this.getLastMessage(i)== null)
                    return;
            if (role.equalsIgnoreCase("SELLER"))
                Assert.assertEquals(this.getName(i).getText(), chatDialogResponse.getItems().get(i).getData().getBuyerName());
            else
            Assert.assertEquals(this.getName(i).getText(), chatDialogResponse.getItems().get(i).getData().getSellerName());
            Assert.assertEquals(this.getPropertyDetails(i).getText(), chatDialogResponse.getItems().get(i).getName());
            Assert.assertEquals(this.getLastMessage(i).getText(), chatDialogResponse.getItems().get(i).getLast_message());
        }
    }


    //Scrolling may cause issue when desired card is partially scrolled up, may cause problem
    public void verifyChatCardDataScrollSingleCard(String name, ChatDialogResponse chatDialogResponse, String role){
        List<Item> items = chatDialogResponse.getItems();
        List<Item> userRoleItem = new ArrayList<>();
        Dimension screenSize = DriverConfig.getDriver().manage().window().getSize();
        Dimension chatCardSize = chatThreads.get(0).getSize();
        int startX = (int)(screenSize.width * 0.5);
        int startY = chatCardSize.height + chatThreads.get(0).getLocation().getY();
        int endX = (int)(screenSize.width * 0.5);
        int endY = chatThreads.get(0).getLocation().getY();
        int apiResponseCounter = 0;
        int chatCardCounter = 0;
        MobileElement lastElement;
        for (int i=0; i< items.size(); i++){
            if (role.equalsIgnoreCase("SELLER") && items.get(i).getData().getSellerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue()) )
                userRoleItem.add(items.get(i));
            if (role.equalsIgnoreCase("BUYER") && items.get(i).getData().getBuyerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue()) )
                userRoleItem.add(items.get(i));
        }
        while (apiResponseCounter < userRoleItem.size()) {
            Assert.assertEquals(this.getName(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getData().getSellerName().trim());
            Assert.assertEquals(this.getPropertyDetails(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getName().trim());
            Assert.assertEquals(this.getLastMessage(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getLast_message().trim());
            if (userRoleItem.size() - apiResponseCounter <= 3)
                chatCardCounter++;
             else {
                lastElement = chatThreads.get(0);
                ActionUtils.swipe(startX, startY, endX, 500, 7000);
                intializeChatThread();
                try {
                    if (lastElement.isDisplayed()) {
                        ActionUtils.swipe(startX, chatThreads.get(1).getLocation().getY(), endX, 500, 7000);
                        intializeChatThread();
                    }
                } catch (Exception e) {

                }
            }
            apiResponseCounter++;

        }

        }



    public void verifyChatCardDataScrollby3Cards(String name, ChatDialogResponse chatDialogResponse, String role){
        List<Item> items = chatDialogResponse.getItems();
        List<Item> userRoleItem = new ArrayList<>();
        Dimension screenSize = DriverConfig.getDriver().manage().window().getSize();
        Dimension chatCardSize = chatThreads.get(0).getSize();
        int startX = (int)(screenSize.width * 0.5);
        int startY = chatCardSize.height + chatThreads.get(2).getLocation().getY();
        int endX = (int)(screenSize.width * 0.5);
        int endY = chatThreads.get(0).getLocation().getY();
        int apiResponseCounter = 0;
        int chatCardCounter = 0;
        MobileElement lastElement;
        for (int i=0; i< items.size(); i++){
            if (role.equalsIgnoreCase("SELLER") && items.get(i).getData().getSellerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue()) )
                userRoleItem.add(items.get(i));
            if (role.equalsIgnoreCase("BUYER") && items.get(i).getData().getBuyerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue()) )
                userRoleItem.add(items.get(i));
        }
        while (apiResponseCounter < userRoleItem.size() && apiResponseCounter < 30){

            Assert.assertEquals(this.getName(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getData().getSellerName().trim());
            Assert.assertEquals(this.getPropertyDetails(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getName().trim());
            Assert.assertEquals(this.getLastMessage(chatCardCounter).getText().trim(), chatDialogResponse.getItems().get(apiResponseCounter).getLast_message().trim());
            apiResponseCounter++;
            chatCardCounter++;
            if(chatCardCounter == 3) {
    //TODO: Handle loader
                lastElement = chatThreads.get(2);
                ActionUtils.swipe(startX, startY, endX, 500, 7000);
                intializeChatThread();
                chatCardCounter = 0;
                try{
                    if (lastElement.isDisplayed()) {
                        ActionUtils.swipe(startX, chatThreads.get(1).getLocation().getY(), endX, 500, 7000);
                        intializeChatThread();
                    }
                }catch (Exception e){

                }

            }

        }
    }

    private void intializeChatThread() {
        this.chatThreads = ActionUtils.findElementsBylocators("xpath",
                "(/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout" +
                        "/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout" +
                        "/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/" +
                        "android.view.ViewGroup/android.view.ViewGroup)[2]/android.view.ViewGroup/" +
                        "android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup" +
                        "/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup");
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

package pages;

import apiRequest.housing.GetBulkBuyDetails;
import apiRequest.housing.GetBulkRentDetails;
import apiResponse.chatDialogResponse.ChatDialogResponse;
import apiResponse.chatDialogResponse.Item;
import apiResponse.getBulkdetails.ChatPropertyDetails;
import configs.DriverConfig;
import enums.ChatUserEnums;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import io.restassured.path.json.JsonPath;
import lombok.Getter;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import util.ActionUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Getter
public class ChatInboxPage {

    @AndroidFindBy(xpath = "(/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[2]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup")
    private List<MobileElement> chatThreads;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@bounds='[0,95][168,275]']")
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

    public void verifyChatCardDataInView(String name, ChatDialogResponse chatDialogResponse, String role){
        List<Item> items = chatDialogResponse.getItems();
        List<Item> userRoleItem = new ArrayList<>();
          Map<String, ChatPropertyDetails> propertyDetailsMap= new HashMap<>();
        for (int i=0; i< items.size(); i++){
            getValidInboxItem(name, role, items, userRoleItem, propertyDetailsMap, i);
            if (userRoleItem.size() == 3)
                break;
        }
        //TODO: Discuss from where these fields are picked, for prabal seller facing upper case-lowercase issue
        int sizeOfCards;
        if (this.chatThreads.size() < 3)
            sizeOfCards = this.chatThreads.size();
        else
            sizeOfCards = 3;
        for (int i=0; i< sizeOfCards; i++) {
            verifyRecieverName(role, userRoleItem, i, i);
            verifyLastMessage(userRoleItem, i, i);
            verifyPropertyDetails(userRoleItem, i, i, propertyDetailsMap);
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
        ChatPropertyDetails chatPropertyDetails;
        Map<String, ChatPropertyDetails> propertyDetailsMap= new HashMap<>();
        MobileElement lastElement = null;
        for (int i=0; i< items.size(); i++){
            getValidInboxItem(name, role, items, userRoleItem, propertyDetailsMap, i);
        }
        //TODO: Discuss from where these fields are picked, for prabal seller facing upper case-lowercase issue
        while (apiResponseCounter < userRoleItem.size()) {
            verifyRecieverName(role, userRoleItem, apiResponseCounter, chatCardCounter);
            verifyLastMessage(userRoleItem, apiResponseCounter, chatCardCounter);
            verifyPropertyDetails(userRoleItem, apiResponseCounter, chatCardCounter, propertyDetailsMap);

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
             try {
                 if (userRoleItem.size() - apiResponseCounter <= 3 && lastElement.isDisplayed() && chatCardCounter == 0)
                     chatCardCounter = 1;
             } catch (Exception e){}
        }

    }

    private void getValidInboxItem(String name, String role, List<Item> items, List<Item> userRoleItem, Map<String, ChatPropertyDetails> propertyDetailsMap, int i) {
        ChatPropertyDetails chatPropertyDetails;
        chatPropertyDetails = this.getApiPropertyDetails(items, i);
        if (chatPropertyDetails.getStatus() == null)
            return;
        if (role.equalsIgnoreCase("SELLER")
                && !items.get(i).getData().getBuyerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue())
                && chatPropertyDetails != null) {
            userRoleItem.add(items.get(i));
            propertyDetailsMap.put(userRoleItem.get(userRoleItem.size()-1).getData().getPropertyId(), chatPropertyDetails);
        }
        if (role.equalsIgnoreCase("BUYER")
                && !items.get(i).getData().getSellerId().toString().equalsIgnoreCase(ChatUserEnums.valueOf(name).getValue())
                && chatPropertyDetails != null) {
            userRoleItem.add(items.get(i));
            propertyDetailsMap.put(userRoleItem.get(userRoleItem.size()-1).getData().getPropertyId(), chatPropertyDetails);

        }
    }

    private void verifyPropertyDetails(List<Item> userRoleItem, int apiResponseCounter, int chatCardCounter, Map<String, ChatPropertyDetails> propertyDetailsMap) {
        if (userRoleItem.get(apiResponseCounter).getData().getPropertyType().equalsIgnoreCase("Project"))
            Assert.assertEquals(this.getPropertyDetails(chatCardCounter).getText().trim(), propertyDetailsMap.get(userRoleItem.get(apiResponseCounter).getData().getPropertyId()).getName().trim());
        else
            Assert.assertEquals(this.getPropertyDetails(chatCardCounter).getText().trim(), propertyDetailsMap.get(userRoleItem.get(apiResponseCounter).getData().getPropertyId()).getAddress().trim());
    }

    private void verifyLastMessage(List<Item> userRoleItem, int apiResponseCounter, int chatCardCounter) {
        Assert.assertEquals(this.getLastMessage(chatCardCounter).getText().trim(), userRoleItem.get(apiResponseCounter).getLast_message().trim());
    }

    private void  verifyRecieverName(String role, List<Item> userRoleItem, int apiResponseCounter, int chatCardCounter) {
        if (role.equalsIgnoreCase("BUYER"))
        Assert.assertEquals(this.getName(chatCardCounter).getText().trim(), userRoleItem.get(apiResponseCounter).getData().getSellerName().trim());
        else
        Assert.assertEquals(this.getName(chatCardCounter).getText().trim().toLowerCase(), userRoleItem.get(apiResponseCounter).getData().getBuyerName().trim().toLowerCase());
    }

    private ChatPropertyDetails getApiPropertyDetails(List<Item> items, int i) {
        ChatPropertyDetails chatPropertyDetails = new ChatPropertyDetails();
        if (items.get(i).getData().getPropertyType().equalsIgnoreCase("rent")){
            JsonPath response = new GetBulkRentDetails().getPropertyDetail(items.get(i).getData().getPropertyType(),
                    items.get(i).getData().getPropertyId());
            chatPropertyDetails.setAddress(response.getString("data[0].address"));
            chatPropertyDetails.setApartment_type(response.getString("data[0].inventory_configs.apartment_type"));
            chatPropertyDetails.setPrice(response.getString("data[0].price"));
            chatPropertyDetails.setStatus(response.getString("data[0].status"));
        }
       else {
            JsonPath response = new GetBulkBuyDetails().getPropertyDetail(items.get(i).getData().getPropertyType(),
                    items.get(i).getData().getPropertyId());
            chatPropertyDetails.setTitle(response.getString("title[0]"));
            chatPropertyDetails.setFormatted_price(response.getString("formatted_price[0]"));
            chatPropertyDetails.setName(response.getString("name[0]"));
            chatPropertyDetails.setFormatted_min_price(response.getString("formatted_min_price[0]"));
            chatPropertyDetails.setFormatted_max_price(response.getString("formatted_max_price[0]"));
            chatPropertyDetails.setStatus(response.getString("status[0]"));
            chatPropertyDetails.setAddress(response.getString("address[0]"));

        }
       return chatPropertyDetails;
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
            verifyLastMessage(chatDialogResponse.getItems(), apiResponseCounter, chatCardCounter);
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

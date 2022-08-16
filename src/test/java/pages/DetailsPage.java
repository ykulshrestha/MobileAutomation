package pages;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;

@Getter
public class DetailsPage {

    @AndroidFindBy(accessibility = "project_detail_chat_now")
    private MobileElement chatNow;

    @AndroidFindBy(id = "Chat_for_Details")
    private MobileElement chatForDetails;

    public DetailsPage() {
         PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }




}

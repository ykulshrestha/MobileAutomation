package pages.buyerAppPages;

import configs.DriverConfig;
import enums.BhkEnum;
import io.appium.java_client.MobileBy;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import pages.Tabs;
import util.ActionUtils;

@Getter
public class FilterPage {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'BHK Type']")
    private MobileElement Bhk;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Sale Type']")
    private MobileElement saleType;

    @AndroidFindBy(accessibility = "filter_viewProperties_button")
    private MobileElement viewProperties;

    public FilterPage() {
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }
    public void setBhk(String bhk){
        ActionUtils.waitForVisibilityOf(Bhk);
        String bhkType = "//android.widget.TextView[@text = '" + bhk + "']";
        ActionUtils.clickButton((MobileElement) DriverConfig.getDriver().findElement(MobileBy.xpath(bhkType)));
    }

    public void setSaleType(String sale){
        String saleType = "//android.widget.TextView[@text = '" + sale + "']";
        ActionUtils.clickButton((MobileElement) DriverConfig.getDriver().findElement(MobileBy.xpath(saleType)));
    }
}

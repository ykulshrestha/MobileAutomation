package modals;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import util.ActionUtils;

@Getter
public class CrfFormModal {

    @AndroidFindBy(xpath = "(/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[5]")
    private MobileElement modalContainer;

    @AndroidFindBy(xpath = "(//android.widget.EditText[@content-desc=\"_input\"])[1]")
    private MobileElement nameTextBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText[@content-desc=\"_input\"])[2]")
    private MobileElement phoneTextBox;

    @AndroidFindBy(xpath = "(//android.widget.EditText[@content-desc=\"_input\"])[3]")
    private MobileElement emailTextBox;

    @AndroidFindBy(xpath = "(/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup)[6]/android.view.ViewGroup/android.view.ViewGroup/android.view.ViewGroup")
    private MobileElement contactCTA;

    //OTP modal
    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Enter verification code']")
    private MobileElement otpTextBox;

    @AndroidFindBy(xpath = "//android.widget.EditText")
    private MobileElement otpEditableTextBox;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text = 'Verify']")
    private MobileElement verifyCta;

    public CrfFormModal(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

    public void fillCrfForm(String name, String phone, String email){
       ActionUtils.sendText(getNameTextBox(), name);
       ActionUtils.sendText(getPhoneTextBox(), phone);
       ActionUtils.sendText(getEmailTextBox(), email);
       ActionUtils.clickButton(getContactCTA());
       ActionUtils.clickButton(getOtpTextBox());
       ActionUtils.sendText(getOtpEditableTextBox(), "1111");
       ActionUtils.clickButton(getVerifyCta());
    }


}

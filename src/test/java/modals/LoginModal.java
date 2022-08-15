package modals;

import configs.DriverConfig;
import io.appium.java_client.MobileElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import io.appium.java_client.pagefactory.AppiumFieldDecorator;
import lombok.Getter;
import org.openqa.selenium.support.PageFactory;
import util.ActionUtils;

public class LoginModal {

    private String modalId;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]")
    private MobileElement modal;

    @AndroidFindBy(accessibility = "_input")
    private MobileElement enterPhone;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[2]")
    private MobileElement signInWithGoogle;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[4]")
    private MobileElement continueButton;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/grant_dialog")
    private MobileElement grantDialog;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_allow_button")
    private MobileElement grantDialogAllow;

    @AndroidFindBy(id = "com.android.permissioncontroller:id/permission_deny_button")
    private MobileElement grantDialogDeny;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]/android.widget.EditText")
    private MobileElement otpTextBoxAfterKeyboard;

    @AndroidFindBy(accessibility = "_input")
    private MobileElement passwordTextbox;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[5]")
    private MobileElement continuePassword;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[6]")
    private MobileElement submit;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[3]/android.widget.TextView")
    private MobileElement otpTextBox;

    @AndroidFindBy(xpath = "/hierarchy/android.widget.FrameLayout/android.widget.LinearLayout/android.widget.FrameLayout/android.widget.FrameLayout/android.view.ViewGroup/android.view.ViewGroup/android.widget.ScrollView/android.view.ViewGroup/android.view.ViewGroup[2]/android.view.ViewGroup[4]/android.widget.TextView\n")
    private MobileElement  loginWithPassword;


    @AndroidFindBy(id = "android:id/message")
    private MobileElement  callsAndPhonePermission;

    @AndroidFindBy(id = "android:id/button1")
    private MobileElement  callsAndPhonePermissionOk;

    @AndroidFindBy(id = "android:id/button2")
    private MobileElement  callsAndPhonePermissionCancel;

    public LoginModal(){
        PageFactory.initElements(new AppiumFieldDecorator(DriverConfig.getDriver()), this);
    }

    public void loginWithPassword(String phoneNumber, String password){
        ActionUtils.sendText(enterPhone, phoneNumber);
        ActionUtils.clickButton(continueButton);
        ActionUtils.clickButton(grantDialogDeny);
        ActionUtils.clickButton(grantDialogDeny);
        ActionUtils.clickButton(loginWithPassword);
        ActionUtils.sendText(passwordTextbox, password);
        ActionUtils.clickButton(continuePassword);
    }

    public void loginWithOtp(String phoneNumber, String otp){
        ActionUtils.sendText(enterPhone, phoneNumber);
        ActionUtils.clickButton(continueButton);
        ActionUtils.clickButton(grantDialogDeny);
        ActionUtils.clickButton(grantDialogDeny);
        ActionUtils.clickButton(otpTextBox);
        ActionUtils.sendText(otpTextBoxAfterKeyboard, otp);
        ActionUtils.clickButton(submit);
    }

}

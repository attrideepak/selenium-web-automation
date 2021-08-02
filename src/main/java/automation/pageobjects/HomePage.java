package automation.pageobjects;

import automation.core.extentreport.ExtentReportManager;
import automation.core.utils.CommonActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage {
    private WebDriver localWebDriver;
    private CommonActions commonActions;

    public HomePage(WebDriver webDriver) {
        localWebDriver = webDriver;
        commonActions = new CommonActions(localWebDriver);
        PageFactory.initElements(localWebDriver, this);
    }

    //<span>LOGIN</span>
    @FindBy(xpath = "//div[@class='user-bar']/span[2]")
    private WebElement loginButton;

    @FindBy(xpath = "//input[@type = 'text']")
    private WebElement phoneTextBox;

    //<input type="password" placeholder="Password" autofocus="autofocus" class="zc-auth-textfield">
    @FindBy(xpath = "//input[@type = 'password']")
    private WebElement passwordTextBox;

    //<img src="/build/img/next-icon.5aaa7a34fca1e56685dfc1041338b9a2.svg" alt="" class="submit disabled" style="">
    @FindBy(xpath = "//img[contains(@src,'next-icon')]")
    private WebElement nextButton;

    //<span>Deepak</span>
    @FindBy(xpath = "//div[@class='user-details']/span")
    private WebElement userName;

    @FindBy(xpath = "//p[@class='error']")
    private WebElement incorrectPassword;

    @FindBy(xpath = "//div[@class = 'log-out']")
    private WebElement logOut;

    @FindBy(xpath = "//div[@class = 'user-details']")
    private WebElement userDetails;

    @FindBy(xpath = "//div[@class='user-bar']/span[1]")
    private WebElement signUpButton;

    @FindBy(xpath = "//button[@class = 'login-btn google-login']")
    private WebElement googleButton;


    public HomePage clickLoginButton() {
        commonActions.waitForElementToBeClickable(loginButton);
        ExtentReportManager.getExtentTest().info(MarkupHelper.createLabel("Login Button Clicked", ExtentColor.BLUE));
        commonActions.clickElement(loginButton);
        return this;
    }

    public HomePage enterPhoneNumber(String phoneNumber) throws IOException {
        //attach image to extent report
        ExtentReportManager.getExtentTest().info("phone number entered and next clicked", MediaEntityBuilder.createScreenCaptureFromBase64String(commonActions.getBase64Image()).build());
        commonActions.enterText(phoneTextBox, phoneNumber);
        commonActions.clickElement(nextButton);
        return this;
    }

    public HomePage enterPassword(String password) {
        ExtentReportManager.getExtentTest().info("password entered and next clicked");
        commonActions.clickElement(passwordTextBox);
        commonActions.enterText(passwordTextBox, password);
        commonActions.clickElement(nextButton);
        return this;
    }

    public String getUserName() {
        return commonActions.getText(userName);
    }

    public String getErrortext() {
        return commonActions.getText(incorrectPassword);
    }

    public HomePage logout() {
        ExtentReportManager.getExtentTest().info(MarkupHelper.createLabel("User details is clicked", ExtentColor.BLUE));
        commonActions.clickElement(userDetails);
        ExtentReportManager.getExtentTest().info(MarkupHelper.createLabel("logOut is clicked", ExtentColor.BLUE));
        commonActions.clickElement(logOut);
        return this;
    }

    public String getLoginText() {
        String logintext = commonActions.getText(loginButton);
        System.out.println(logintext);
        return logintext;
    }

    public HomePage clickSignUp() {
        commonActions.clickElement(signUpButton);
        return this;
    }

    public String getGoogleText() {
        return commonActions.getText(googleButton);
    }

}

package automation.pageobjects;

import automation.core.extentreport.ExtentReportManager;
import automation.core.extentreport.ExtentReportUtils;
import automation.core.utils.CommonActions;
import com.aventstack.extentreports.MediaEntityBuilder;
import com.aventstack.extentreports.markuputils.ExtentColor;
import com.aventstack.extentreports.markuputils.Markup;
import com.aventstack.extentreports.markuputils.MarkupHelper;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.io.IOException;

public class HomePage {
    private WebDriver localWebDriver;
    private CommonActions commonActions;

    public HomePage(WebDriver webDriver){
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


    public HomePage clickLoginButton(){
        commonActions.waitForElementToBeClickable(loginButton);
        ExtentReportManager.getExtentTest().info(MarkupHelper.createLabel("Login Button Clicked", ExtentColor.BLUE));
        commonActions.clickElement(loginButton);
        commonActions.takeScreenShot("Login Page");
        return this;
    }

    public HomePage enterPhoneNumber(String phoneNumber) throws IOException {
        ExtentReportManager.getExtentTest().info("phone number entered and next clicked", MediaEntityBuilder.createScreenCaptureFromBase64String(commonActions.getBase64Image()).build());
        commonActions.enterText(phoneTextBox,phoneNumber);
        commonActions.clickElement(nextButton);
        commonActions.takeScreenShot("After entering phone number");
        return this;
    }

    public HomePage enterPassword(String password){
        ExtentReportManager.getExtentTest().info("password entered and next clicked");
        commonActions.enterText(passwordTextBox,password);
        commonActions.clickElement(nextButton);
        commonActions.takeScreenShot("Entering Password");
        return this;
    }

    public String getUserName(){
        return commonActions.getText(userName);
    }

    public String getErrortext(){
        return commonActions.getText(incorrectPassword);
    }

}

package automation.pageobjects;

import automation.core.CommonActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

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
        commonActions.clickElement(loginButton);
        return this;
    }

    public HomePage enterPhoneNumber(String phoneNumber){
        commonActions.enterText(phoneTextBox,phoneNumber);
        commonActions.clickElement(nextButton);
        return this;
    }

    public HomePage enterPassword(String password){
        commonActions.enterText(passwordTextBox,password);
        commonActions.clickElement(nextButton);
        return this;
    }

    public String getUserName(){
        return commonActions.getText(userName);
    }

    public String getErrortext(){
        return commonActions.getText(incorrectPassword);
    }

}

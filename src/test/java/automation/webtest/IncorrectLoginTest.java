package automation.webtest;

import automation.base.BaseTest;
import automation.core.annotations.ExtentReportAnnotation;
import automation.core.driver.LocalDriverManager;
import automation.core.utils.CommonActions;
import automation.pageobjects.HomePage;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

@Epic("Negative test case for login")
public class IncorrectLoginTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = LogManager.getLogger(LoginTest.class);
    private HomePage homePage;
    CommonActions commonActions;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        homePage = new HomePage(localWebDriver);
        commonActions = new CommonActions(localWebDriver);
    }

    @Feature("Login with incorrect password")
    @ExtentReportAnnotation(author = {"alpha","beta"},category = {"sanity","regression"})
    @Test(description = "Incorrect password test")
    public void loginWithIncorrectPassword() throws IOException {
        localWebDriver.navigate().refresh();
        System.out.println("The thread ID for loginWithPhoneNumber is "+ Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + localWebDriver.hashCode());
        String userName = homePage.clickLoginButton().enterPhoneNumber("******").enterPassword("dfdfdfggf").getErrortext();
        commonActions.takeScreenShot();
        Assert.assertEquals(userName,"Incorrect Password","User name does not match");
    }

    @Feature("Login with invalid credential")
    @ExtentReportAnnotation(author = {"alpha","beta"},category = {"sanity","regression"})
    @Test(description = "Invalid email test")
    public void loginWithInvalidCreds() throws IOException {
        localWebDriver.navigate().refresh();
        String errorMessage = homePage.clickLoginButton().enterPhoneNumber("9988").getErrortext();
        commonActions.takeScreenShot();
        Assert.assertTrue(errorMessage.equalsIgnoreCase("Please enter a valid email address or mobile number."),"Invalid Email");
    }

    @Feature("Click signup test")
    @ExtentReportAnnotation(author = {"alpha","beta"},category = {"sanity","regression"})
    @Test(description = "signUp test")
    public void clickSignUp() {
        localWebDriver.navigate().refresh();
        String text = homePage.clickSignUp().getGoogleText();
        commonActions.takeScreenShot();
        Assert.assertTrue(text.equalsIgnoreCase("GOOGLE"),"Invalid Email");
    }
}

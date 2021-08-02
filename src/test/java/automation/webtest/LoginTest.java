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

@Epic("Login and Logout test")
public class LoginTest extends BaseTest {
    private static WebDriver localWebDriver;
    private static final Logger logger = LogManager.getLogger(BaseTest.class);
    private HomePage homePage;
    CommonActions commonActions;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        homePage = new HomePage(localWebDriver);
        commonActions = new CommonActions(localWebDriver);
    }

    @Feature("Login with phone number")
    @ExtentReportAnnotation(author = {"alpha"}, category = {"sanity"})
    @Test(description = "User login sucessfully")
    public void loginWithPhoneNumber() throws IOException {
        logger.info("The thread ID for loginWithPhoneNumber is " + Thread.currentThread().getId());
        logger.info("Hashcode of webDriver instance = " + localWebDriver.hashCode());
        String userName = homePage.clickLoginButton().enterPhoneNumber("*****").enterPassword("*******").getUserName();
        commonActions.takeScreenShot("Logged In");
        Assert.assertFalse(userName.equals("Deepak"), "User name does not match");
    }

    @Feature("Logout user")
    @ExtentReportAnnotation(author = {"alpha"}, category = {"sanity"})
    @Test(description = "User logout", dependsOnMethods = "loginWithPhoneNumber", alwaysRun = true)
    public void logOut() {
        String text = homePage.logout().getLoginText();
        logger.info(text);
        commonActions.takeScreenShot("Logged Out");
        Assert.assertFalse(text.equalsIgnoreCase("login"), "Logged out");
    }
}

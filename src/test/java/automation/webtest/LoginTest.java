package automation.webtest;

import automation.base.BaseTest;
import automation.core.annotations.ExtentReportAnnotation;
import automation.core.driver.LocalDriverManager;
import automation.pageobjects.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class LoginTest extends BaseTest {
    private  static WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(LoginTest.class);
    private HomePage homePage;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        homePage = new HomePage(localWebDriver);
    }

    @ExtentReportAnnotation(author = {"alpha"},category = {"sanity"})
    @Test(description = "User login sucessfully")
    public void loginWithPhoneNumber() throws IOException {
        System.out.println("The thread ID for loginWithPhoneNumber is "+ Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + localWebDriver.hashCode());
        String userName = homePage.clickLoginButton().enterPhoneNumber("7769954301").enterPassword("Rajnikant@123").getUserName();
       // Assert.assertEquals(userName,"hhjhkhkh","User name does not match");
    }
}

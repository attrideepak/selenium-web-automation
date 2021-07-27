package automation.webtest;

import automation.base.BaseTest;
import automation.core.annotations.ExtentReportAnnotation;
import automation.core.driver.LocalDriverManager;
import automation.core.extentreport.ExtentReportUtils;
import automation.pageobjects.HomePage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.io.IOException;

public class IncorrectLoginTest extends BaseTest {
    private WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(LoginTest.class);
    private HomePage homePage;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        homePage = new HomePage(localWebDriver);
    }

    @ExtentReportAnnotation(author = {"alpha","beta"},category = {"sanity","regression"})
    @Test(description = "Incorrect password test")
    public void loginWithIncorrectPassword() throws IOException {
        System.out.println("The thread ID for loginWithPhoneNumber is "+ Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + localWebDriver.hashCode());
        String userName = homePage.clickLoginButton().enterPhoneNumber("*********").enterPassword("dfdfdfggf").getErrortext();
        Assert.assertEquals(userName,"Incorrect Password","User name does not match");
    }
}

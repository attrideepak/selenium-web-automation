package automation.webtest;

import automation.base.BaseTest;
import automation.core.LocalDriverManager;
import automation.pageobjects.HomePage;
import automation.pageobjects.SelectCityPage;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    private  static WebDriver localWebDriver;
    private Logger logger = Logger.getLogger(LoginTest.class);
    private HomePage homePage;


    @BeforeClass
    public void initialiseClass() {
        localWebDriver = LocalDriverManager.getDriver();
        homePage = new HomePage(localWebDriver);
    }

    @Test(priority = 1)
    public void loginWithPhoneNumber(){
        System.out.println("The thread ID for loginWithPhoneNumber is "+ Thread.currentThread().getId());
        System.out.println("Hashcode of webDriver instance = " + localWebDriver.hashCode());
        String userName = homePage.clickLoginButton().enterPhoneNumber("********").enterPassword("******").getUserName();
        Assert.assertEquals(userName,"Deepak","User name does not match");
    }
}

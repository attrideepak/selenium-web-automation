package automation.webtest.devtoolstest;

import automation.base.BaseTest;
import automation.pageobjects.SelectCityPage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.log.Log;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class ConsoleLogsTest extends BaseTest {
    private WebDriver driver;
    private SelectCityPage selectCityPage;

    @BeforeClass
    public void initialiseClass() {
       // driver = super.driver;
        selectCityPage = new SelectCityPage(driver);
       // localWebDriver.get("https://www.zoomcar.com");
    }

    @Test
    public void consoleLogs(){
        DevTools devTools = ((ChromeDriver)driver).getDevTools();
        devTools.createSession();

        devTools.send(Log.enable());
        devTools.addListener(Log.entryAdded(),
                logEntry -> {
                    System.out.println("log: "+logEntry.getText());
                    System.out.println("level: "+logEntry.getLevel());
                });
        driver.get("https://www.zoomcar.com/bangalore");
        driver.findElement(By.className("search")).click();
        devTools.send(Log.clear());
        devTools.send(Log.disable());


    }
}

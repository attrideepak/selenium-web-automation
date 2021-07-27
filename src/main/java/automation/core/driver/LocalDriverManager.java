package automation.core.driver;

import org.openqa.selenium.WebDriver;

public class LocalDriverManager {
    private LocalDriverManager(){

    }
    private static ThreadLocal<WebDriver> webDriver = new ThreadLocal<>();

    public static WebDriver getDriver() {
        return webDriver.get();
    }

    public static void setWebDriver(WebDriver driver) {
        webDriver.set(driver);
    }

    public static void removeWebDriver(){
        webDriver.remove();
    }


}

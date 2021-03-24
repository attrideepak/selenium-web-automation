package automation.core;


import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.FileUtils;
import org.apache.log4j.Logger;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.network.Network;
import org.openqa.selenium.devtools.v89.network.model.Headers;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import org.openqa.selenium.devtools.DevTools;


import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class CommonActions {
    private WebDriver localWebDriver;
    private static Logger logger = Logger.getLogger(CommonActions.class);

    public CommonActions(WebDriver localWebDriver) {
        this.localWebDriver = localWebDriver;
    }

    public void waitForElementToBeClickable(WebElement myElement) {
            WebDriverWait wait = new WebDriverWait(localWebDriver, 20);
            wait.until(ExpectedConditions.elementToBeClickable(myElement));
    }

    public void waitForElementVisibility(WebElement myElement) {
            WebDriverWait wait = new WebDriverWait(localWebDriver, 20);
            wait.until(ExpectedConditions.visibilityOf(myElement));
    }

    public void clickElement(WebElement myElement) {
        waitForElementToBeClickable(myElement);
            myElement.click();
    }

    public void enterText(WebElement myElement, String text) {
            waitForElementVisibility(myElement);
            myElement.sendKeys(text.trim());
    }


    public String getText(WebElement myElement) {
            String text;
            text = myElement.getText();
            return text;
    }

    /**
     * Takes screenshot of the entire page
     * @param name
     */
    public void takeScreenShot(String name) {
        logger.info("taking screenshot!");

        File scrFile;
        try {
            scrFile = ((TakesScreenshot) localWebDriver).getScreenshotAs(OutputType.FILE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            return;
        }
        String path = System.getProperty("user.dir") + "/target/screenshots/";
        String timeStamp;
        timeStamp = String.valueOf(DateUtils.getTimeInMilliSecond());
        String fileName = path + (name == null ? "" : name + "_") + timeStamp + ".png";
        logger.info(fileName);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path content = Paths.get(fileName);
        try (InputStream is = Files.newInputStream(content)) {
            // Allure.addAttachment("Failed Test Screenshot - " + fileName.split(path)[1], is);
        } catch (IOException e) {
            logger.error("attachment error");
        }
    }

    /**
     * Takes screenshot of an element
     * @param name
     * @param ekement
     */
    public void takeScreenShot(String name, WebElement ekement) {
        logger.info("taking screenshot!");

        File scrFile;
        try {
            scrFile = ekement.getScreenshotAs(OutputType.FILE);
        } catch (Exception e) {
            logger.error(e.getMessage());
            logger.error(e.getStackTrace());
            return;
        }
        String path = System.getProperty("user.dir") + "/target/screenshots/";
        String timeStamp;
        timeStamp = String.valueOf(DateUtils.getTimeInMilliSecond());
        String fileName = path + (name == null ? "" : name + "_") + timeStamp + ".png";
        logger.info(fileName);
        try {
            FileUtils.copyFile(scrFile, new File(fileName));
        } catch (IOException e) {
            e.printStackTrace();
        }
        Path content = Paths.get(fileName);
        try (InputStream is = Files.newInputStream(content)) {
            // Allure.addAttachment("Failed Test Screenshot - " + fileName.split(path)[1], is);
        } catch (IOException e) {
            logger.error("attachment error");
        }
    }

    public void basicAuthentication(String userName,String password){
        DevTools devTools = ((ChromeDriver)localWebDriver).getDevTools();
        devTools.createSession();
        devTools.send(Network.enable(Optional.empty(), Optional.empty(), Optional.empty()));
        Map<String,Object> headers = new HashMap<>();
        String basicAuth = "Basic " + new String(new Base64().encode(String.format("%s:%s", userName, password).getBytes()));
        headers.put("Authorization",basicAuth);
        devTools.send(Network.setExtraHTTPHeaders(new Headers(headers)));
    }

}

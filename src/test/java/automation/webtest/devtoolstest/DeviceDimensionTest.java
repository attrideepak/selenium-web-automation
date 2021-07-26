package automation.webtest.devtoolstest;

import automation.base.BaseTest;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.devtools.DevTools;
import org.openqa.selenium.devtools.v89.emulation.Emulation;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

public class DeviceDimensionTest extends BaseTest {
    private WebDriver localWebDriver;

    @BeforeClass
    public void initialiseClass() {
        localWebDriver = super.threadLocalDriver;
    }

    @Test
    public void simulateDeviceDimensions(){
        DevTools devTools = ((ChromeDriver)threadLocalDriver).getDevTools();
        devTools.createSession();
        Map deviceMetrics = new HashMap()
        {{
            put("width", 600);
            put("height", 1000);
            put("mobile", true);
            put("deviceScaleFactor", 50);
        }};
       ((ChromeDriver)threadLocalDriver).executeCdpCommand("Emulation.setDeviceMetricsOverride", deviceMetrics);
        threadLocalDriver.get("https://www.zoomcar.com"); //set device first and then launch
    }
}

package automation.core.extentreport;

import com.aventstack.extentreports.ExtentTest;

public class ExtentReportManager {
    private ExtentReportManager(){

    }

    private static ThreadLocal<ExtentTest>extentTestThreadLocal = new ThreadLocal<>();

    public static ExtentTest getExtentTest() {
        return extentTestThreadLocal.get();
    }

    public static void setExtentTest(ExtentTest extentTest) {
        extentTestThreadLocal.set(extentTest);
    }

    public static void removeExtentThread(){
        extentTestThreadLocal.remove();
    }
}

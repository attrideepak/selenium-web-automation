package automation.core.allurelistener;

import automation.core.driver.LocalDriverManager;
import automation.core.utils.CommonActions;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class AllureListener implements ITestListener {
    @Override
    public void onTestStart(ITestResult result) {

    }

    @Override
    public void onTestSuccess(ITestResult result) {

    }

    /**
     * Add screenshot on failure to allure reports
     */
    @Override
    public void onTestFailure(ITestResult result) {
        CommonActions commonActions = new CommonActions(LocalDriverManager.getDriver());
        if (LocalDriverManager.getDriver() != null) {
            System.out.println(
                    "Screen captured for testcase : "
                            + result.getMethod().getConstructorOrMethod().getName());
            commonActions.takeScreenShot(result.getMethod().getConstructorOrMethod().getName());
        } else {
            System.out.println("could not take failed test screenshot due to driver is null");
        }
    }

    @Override
    public void onTestSkipped(ITestResult result) {

    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {

    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {

    }

    @Override
    public void onStart(ITestContext context) {

    }

    @Override
    public void onFinish(ITestContext context) {

    }
}

package util;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

public class TestListener implements ITestListener {
    private Logger log = LogManager.getRootLogger();

    @Override
    public void onTestStart(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---START");
    }

    @Override
    public void onTestSuccess(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---Success");
    }

    @Override
    public void onTestFailure(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---Failure");
    }

    @Override
    public void onTestSkipped(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---Skipped");
    }

    @Override
    public void onTestFailedButWithinSuccessPercentage(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---Failed but within Success percentage");
    }

    @Override
    public void onTestFailedWithTimeout(ITestResult result) {
        log.info(result.getMethod().getMethodName() + "---Failed with timeout");
    }

    @Override
    public void onStart(ITestContext context) {
        log.info(context.getName() + "-----START TESTS");
    }

    @Override
    public void onFinish(ITestContext context) {
        log.info(context.getName() + "-----FINISH TESTS");
        log.info("----------------------------------------------");
        log.info(" ");
        log.info("----------------------------------------------");
    }
}

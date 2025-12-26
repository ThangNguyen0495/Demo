package utils.listener;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.testng.ITestResult;
import org.testng.TestListenerAdapter;
import utils.driver.Driver;

import java.io.File;
import java.io.IOException;

public class TestListener extends TestListenerAdapter {
    @Override
    public void onTestFailure(ITestResult result) {
        WebDriver driver = Driver.getWebDriver();

        File srcFile = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        try {
            FileUtils.copyFile(srcFile, new File(System.getProperty("user.dir") + "/src/out/screenshots/" + result.getName() + ".png"));
        } catch (IOException e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
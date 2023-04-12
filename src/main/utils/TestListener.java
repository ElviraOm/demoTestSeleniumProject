package main.utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.testng.ITestListener;
import org.testng.ITestResult;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class TestListener implements ITestListener {
    @Override
    public void onTestFailure(ITestResult result) {
        IDriverAware driverHolder = (IDriverAware)result.getInstance();
        String testName = result.getName();
        LocalDateTime aLDT = LocalDateTime.now();
        DateTimeFormatter dTF = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        String failTime = dTF.format(aLDT);
        TakesScreenshot screenshot = (TakesScreenshot)driverHolder.getDriver();
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        File dest = new File("./test-output/screenshots/" + failTime + "_" + testName + ".png");
        try {
            Files.copy(file.toPath(), dest.toPath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}

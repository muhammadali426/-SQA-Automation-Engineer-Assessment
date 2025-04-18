package utils;

import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

public class ScreenshotUtil {

    public static void captureScreenshot(WebDriver driver, String filename) {
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destFile = new File("screenshots/" + filename);
        destFile.getParentFile().mkdirs();

        try {
            Files.copy(screenshot.toPath(), destFile.toPath());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.FileUploadPage;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.io.File;
import java.time.Duration;

import static org.testng.Assert.assertTrue;

public class FileUploadTest {

    private WebDriver driver;
    private FileUploadPage fileUploadPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        fileUploadPage = new FileUploadPage(driver);
        fileUploadPage.open();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testFileUploadWithRobot() throws Exception {
        File file = new File("C:\\Users\\Fine\\Downloads\\SQA Automation Engineer Assessment (1).docx");
        if (!file.exists()) {
            throw new RuntimeException("File not found: " + file.getAbsolutePath());
        }

        // Use JavaScript to click the file input (avoid InvalidArgumentException)
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", fileUploadPage.getFileInputElement());

        // Small delay for OS dialog to appear
        Thread.sleep(1000);

        // Use Robot to upload
        StringSelection filePath = new StringSelection(file.getAbsolutePath());
        Toolkit.getDefaultToolkit().getSystemClipboard().setContents(filePath, null);

        Robot robot = new Robot();
        robot.setAutoDelay(500);

        robot.keyPress(KeyEvent.VK_CONTROL);
        robot.keyPress(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_V);
        robot.keyRelease(KeyEvent.VK_CONTROL);

        robot.keyPress(KeyEvent.VK_ENTER);
        robot.keyRelease(KeyEvent.VK_ENTER);

        // Click upload
        fileUploadPage.clickUploadButton();

        // Wait for confirmation
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.textToBePresentInElementLocated(
                fileUploadPage.getUploadMessageLocator(), "File Uploaded!"
        ));

        // Validate uploaded filename
        String uploadedFile = fileUploadPage.getUploadedFileName();
        assertTrue(uploadedFile.contains("SQA Automation Engineer Assessment"), "Uploaded file name mismatch!");
    }
}

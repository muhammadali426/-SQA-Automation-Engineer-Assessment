package tests;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import pages.JavaScriptAlertsPage;

import static org.testng.Assert.*;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class JavaScriptAlertsTest {

    private WebDriver driver;
    private JavaScriptAlertsPage alertsPage;

    @BeforeClass
    public void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void setupTest() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        alertsPage = new JavaScriptAlertsPage(driver);
        alertsPage.open();
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void testJSAlert() {
        alertsPage.clickJSAlert();
        String alertText = alertsPage.handleAlert("accept", null);
        assertEquals(alertText, "I am a JS Alert");
        assertEquals(alertsPage.getResultText(), "You successfully clicked an alert");
    }

    @Test
    public void testJSConfirmAccept() {
        alertsPage.clickJSConfirm();
        String alertText = alertsPage.handleAlert("accept", null);
        assertEquals(alertText, "I am a JS Confirm");
        assertEquals(alertsPage.getResultText(), "You clicked: Ok");
    }

    @Test
    public void testJSConfirmDismiss() {
        alertsPage.clickJSConfirm();
        alertsPage.handleAlert("dismiss", null);
        assertEquals(alertsPage.getResultText(), "You clicked: Cancel");
    }

    @Test
    public void testJSPromptAccept() {
        alertsPage.clickJSPrompt();
        String name = "Muhammad Ali";
        alertsPage.handleAlert("accept", name);
        assertEquals(alertsPage.getResultText(), "You entered: " + name);
    }

    @Test
    public void testJSPromptDismiss() {
        alertsPage.clickJSPrompt();
        alertsPage.handleAlert("dismiss", null);
        assertEquals(alertsPage.getResultText(), "You entered: null");
    }
}

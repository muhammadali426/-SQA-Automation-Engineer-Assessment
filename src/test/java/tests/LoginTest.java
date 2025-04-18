package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.LoginPage;
import utils.ScreenshotUtil;

public class LoginTest {

    WebDriver driver;
    LoginPage loginPage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        loginPage = new LoginPage(driver);
        loginPage.open();
    }

    @Test
    public void testValidLogin() {
        loginPage.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(loginPage.isLoginSuccessful(), "Valid login failed.");
    }

    @Test
    public void testInvalidLogin() {
        loginPage.login("wronguser", "wrongpass");
        if (loginPage.isLoginSuccessful()) {
            Assert.fail("Login should not be successful with invalid credentials.");
        } else {
            ScreenshotUtil.captureScreenshot(driver, "invalid_login.png");
            Assert.assertTrue(loginPage.getFlashMessage().contains("Your username is invalid")
                    || loginPage.getFlashMessage().contains("Your password is invalid"));
        }
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


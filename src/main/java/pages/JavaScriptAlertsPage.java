package pages;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class JavaScriptAlertsPage {

    private WebDriver driver;

    // Locators
    private By jsAlertBtn = By.xpath("//button[text()='Click for JS Alert']");
    private By jsConfirmBtn = By.xpath("//button[text()='Click for JS Confirm']");
    private By jsPromptBtn = By.xpath("//button[text()='Click for JS Prompt']");
    private By resultText = By.id("result");

    // Constructor
    public JavaScriptAlertsPage(WebDriver driver) {
        this.driver = driver;
    }

    // Open the alerts page
    public void open() {
        driver.get("https://the-internet.herokuapp.com/javascript_alerts");
    }

    // Click Methods
    public void clickJSAlert() {
        driver.findElement(jsAlertBtn).click();
    }

    public void clickJSConfirm() {
        driver.findElement(jsConfirmBtn).click();
    }

    public void clickJSPrompt() {
        driver.findElement(jsPromptBtn).click();
    }

    // Get result text displayed after alert interaction
    public String getResultText() {
        return driver.findElement(resultText).getText();
    }

    /**
     * Handle JavaScript alert
     * 
     * @param action     - "accept" or "dismiss"
     * @param inputText  - text to send to prompt (null if not needed)
     * @return alert text before handling
     */
    public String handleAlert(String action, String inputText) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();

        if (inputText != null) {
            alert.sendKeys(inputText);
        }

        if ("accept".equalsIgnoreCase(action)) {
            alert.accept();
        } else {
            alert.dismiss();
        }

        return alertText;
    }
}

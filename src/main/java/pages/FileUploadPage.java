package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class FileUploadPage {

    private WebDriver driver;

    private By fileInput = By.id("file-upload");
    private By uploadButton = By.id("file-submit");
    private By uploadMessage = By.xpath("//h3[.='File Uploaded!']");
    private By uploadedFileName = By.id("uploaded-files");

    public FileUploadPage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/upload");
    }

    public void clickChooseFile() {
        driver.findElement(fileInput).click();
    }

    public WebElement getFileInputElement() {
        return driver.findElement(fileInput);
    }

    public void clickUploadButton() {
        driver.findElement(uploadButton).click();
    }

    public By getUploadMessageLocator() {
        return uploadMessage;
    }

    public String getUploadedFileName() {
        return driver.findElement(uploadedFileName).getText();
    }
}

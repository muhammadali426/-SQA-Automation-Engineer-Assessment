package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;
import pages.TablePage;

import java.util.List;

import static org.testng.Assert.*;

public class TableTest {

    WebDriver driver;
    TablePage tablePage;

    @BeforeClass
    public void setup() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeMethod
    public void init() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();

        tablePage = new TablePage(driver);
        tablePage.open();
    }

    @Test
    public void testPrintAllLastNames() {
        List<String> lastNames = tablePage.getColumnValues(1, 0); // 1st table, 1st column (Last Name)
        System.out.println("Last Names in Table:");
        for (String name : lastNames) {
            System.out.println(name);
        }
        assertFalse(lastNames.isEmpty(), "No names found in the table!");
    }

    @Test
    public void testVerifySpecificNameExists() {
    	 boolean exists = tablePage.isTextPresentInRow(1, "Doe Jason");
    	    Assert.assertTrue(exists, "Expected name not found in the table.");
    }

    @Test
    public void testExtractGenericTableData() {
        List<List<String>> data = tablePage.extractTableData(1); // 1st table
        System.out.println("Complete Table Data:");
        for (List<String> row : data) {
            System.out.println(row);
        }
        assertTrue(data.size() > 0, "No table data extracted.");
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}

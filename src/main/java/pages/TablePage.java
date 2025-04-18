package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class TablePage {
    WebDriver driver;

    public TablePage(WebDriver driver) {
        this.driver = driver;
    }

    public void open() {
        driver.get("https://the-internet.herokuapp.com/tables");
    }

   
    public List<String> getColumnValues(int tableIndex, int columnIndex) {
        List<String> values = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.xpath("(//table)[" + tableIndex + "]//tbody/tr"));

        for (WebElement row : rows) {
            List<WebElement> columns = row.findElements(By.tagName("td"));
            if (columns.size() > columnIndex) {
                values.add(columns.get(columnIndex).getText());
            }
        }

        return values;
    }

   
    public boolean isTextPresentInTable(int tableIndex, String textToFind) {
        List<WebElement> cells = driver.findElements(By.xpath("(//table)[" + tableIndex + "]//tbody//td"));
        System.out.println("Looking for: " + textToFind);
        for (WebElement cell : cells) {
            String cellText = cell.getText().trim();
            System.out.println("Cell: '" + cellText + "'");
            if (cellText.toLowerCase().contains(textToFind.toLowerCase())) {
                return true;
            }
        }
        return false;
    }

    public boolean isTextPresentInRow(int tableIndex, String textToFind) {
        List<WebElement> rows = driver.findElements(By.xpath("(//table)[" + tableIndex + "]//tbody/tr"));
        System.out.println("Looking for: " + textToFind);

        for (WebElement row : rows) {
            List<WebElement> cells = row.findElements(By.tagName("td"));
            StringBuilder sb = new StringBuilder();

            for (WebElement cell : cells) {
                sb.append(cell.getText().trim()).append(" ");
            }

            String fullRowText = sb.toString().trim();
            System.out.println(">> ROW: " + fullRowText);

            if (fullRowText.toLowerCase().contains(textToFind.toLowerCase())) {
                System.out.println("MATCH FOUND");
                return true;
            }
        }

        System.out.println("MATCH NOT FOUND ");
        return false;
    }

   
    public List<List<String>> extractTableData(int tableIndex) {
        List<List<String>> tableData = new ArrayList<>();
        List<WebElement> rows = driver.findElements(By.xpath("(//table)[" + tableIndex + "]//tbody/tr"));

        for (WebElement row : rows) {
            List<String> rowData = new ArrayList<>();
            List<WebElement> cells = row.findElements(By.tagName("td"));
            for (WebElement cell : cells) {
                rowData.add(cell.getText());
            }
            tableData.add(rowData);
        }

        return tableData;
    }
}

package tests.basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.Test;

import java.util.List;

import static utils.MethodsProvider.clearRedundantElements;

public class TableTest extends BaseTest {

    private final String countryToCheck = "Switzerland";

    @Test
    public void tableTest() {
        List<WebElement> peaks = driver.findElements(By.cssSelector("tr"));
        clearRedundantElements(peaks);
        for (WebElement peak : peaks) {
            String[] columns = peak.getText().split(" ");
            for (String column : columns) {
                if ((column.contains(countryToCheck))) {
                    int height = Integer.parseInt(columns[columns.length - 1]);
                    if (height > 4000) {
                        System.out.println(columns[0] + ". " + columns[1] + " - " + columns[2]);
                    }
                }
            }
        }
    }
}
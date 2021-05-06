package basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class TableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/");
    }

    @Test
    public void tableTest() {
        List<WebElement> peaks = driver.findElements(By.cssSelector("tr"));
        clearRedundantElements(peaks);
        for (WebElement peak : peaks) {
            String[] columns = peak
                    .getText()
                    .split(" ");
            for (String column : columns) {
                if ((column.contains("Switzerland"))) {
                    int height = Integer.parseInt(columns[columns.length - 1]);
                    if (height > 4000) {
                        System.out.println(columns[0] + " " + columns[1] + " " + columns[2]);
                    }
                }
            }
        }
    }

    public void clearRedundantElements(List<WebElement> list) {
        list.subList(0, 2)
                .clear();
    }
}
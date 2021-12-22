package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.DatePickerPage;

import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;

import static pages.widgets.DatePickerPage.LEFT;
import static pages.widgets.DatePickerPage.RIGHT;

public class DatePickerTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
    }

    @Test
    public void datePickerTest() throws IOException, UnsupportedFlavorException {

        DatePickerPage page = new DatePickerPage(driver);
        String localDate = page.reverseLocalDate(LocalDate.now().toString());
        String[] datesToCheck = {"30.10.2018", "25.09.2018", "25.09.2018", "01.01.2018", "01.02.2018", localDate, "10.10.2021"};

        for (int i = 0; i < datesToCheck.length; i++) {
            page.datePickerField.click();
            String[] date = page.splitTestedDate(datesToCheck, i);
            if ((Integer.parseInt(date[2])) < (Integer.parseInt(page.year.getText()))) {
                page.moveIntoDesiredDirection(i, page, LEFT, datesToCheck);
            } else if ((Integer.parseInt(date[2])) == (Integer.parseInt(page.year.getText()))) {
                if ((Integer.parseInt(date[1])) < (Integer.parseInt(page.convertMonthToNumber(page)))) {
                    page.moveIntoDesiredDirection(i, page, LEFT, datesToCheck);
                } else if ((Integer.parseInt(date[1])) > (Integer.parseInt(page.convertMonthToNumber(page)))) {
                    page.moveIntoDesiredDirection(i, page, RIGHT, datesToCheck);
                } else {
                    try {
                        page.highlighted.click();
                    } catch (NoSuchElementException ignored) {
                        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();
                    }
                }
            } else {
                page.moveIntoDesiredDirection(i, page, RIGHT, datesToCheck);
            }
            System.out.println((page.convertToNonAmericanDate(page.getDateFromField(page))) + " compared to: " + datesToCheck[i]);
            Assert.assertEquals((page.convertToNonAmericanDate(page.getDateFromField(page))), datesToCheck[i]);
        }
    }
}
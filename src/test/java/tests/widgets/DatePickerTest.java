package tests.widgets;

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

import static pages.widgets.DatePickerPage.*;

public class DatePickerTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get(DATEPICKER_PAGE);
    }

    @Test
    public void datePickerTest() throws IOException, UnsupportedFlavorException {
        DatePickerPage datePickerPage = new DatePickerPage(driver);
        String localDate = datePickerPage.reverseLocalDate(LocalDate.now().toString());
        String[] datesToCheck = {"30.10.2018", "25.09.2018", "25.09.2018", "01.01.2018", "01.02.2018", localDate, "10.10.2021"};
        for (int i = 0; i < datesToCheck.length; i++) {
            datePickerPage.datePickerField.click();
            String[] date = datePickerPage.splitTestedDate(datesToCheck, i);
            if ((Integer.parseInt(date[2])) < (Integer.parseInt(datePickerPage.year.getText()))) {
                datePickerPage.moveIntoDesiredDirection(i, datePickerPage, LEFT, datesToCheck);
            } else if ((Integer.parseInt(date[2])) == (Integer.parseInt(datePickerPage.year.getText()))) {
                if ((Integer.parseInt(date[1])) < (Integer.parseInt(
                        datePickerPage.convertMonthToNumber(datePickerPage)))) {
                    datePickerPage.moveIntoDesiredDirection(i, datePickerPage, LEFT, datesToCheck);
                } else if ((Integer.parseInt(date[1])) > (Integer.parseInt(
                        datePickerPage.convertMonthToNumber(datePickerPage)))) {
                    datePickerPage.moveIntoDesiredDirection(i, datePickerPage, RIGHT, datesToCheck);
                } else {
                    try {
                        datePickerPage.highlighted.click();
                    } catch (NoSuchElementException ignored) {
                        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active")).click();
                    }
                }
            } else {
                datePickerPage.moveIntoDesiredDirection(i, datePickerPage, RIGHT, datesToCheck);
            }
            System.out.println((datePickerPage.convertToNonAmericanDate(
                    datePickerPage.getDateFromField(datePickerPage))) + " compared to: " + datesToCheck[i]);
            Assert.assertEquals(
                    (datePickerPage.convertToNonAmericanDate(datePickerPage.getDateFromField(datePickerPage))),
                    datesToCheck[i]);
        }
    }
}
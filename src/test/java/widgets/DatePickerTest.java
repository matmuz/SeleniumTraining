package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.DatePickerPage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DatePickerTest extends BaseTest {

    static final String LEFT = "left";
    static final String RIGHT = "right";
    static final List<Month> MONTHS = Arrays.asList(Month.values());
    String localDate = reverseLocalDate(LocalDate.now()
                                                .toString());
    String[] datesToCheck = {"30.10.2018", "25.09.2018", "25.09.2018", "01.01.2018", "01.02.2018", localDate, "10.10.2021"};

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
    }

    @Test
    public void datePickerTest() throws IOException, UnsupportedFlavorException {
        for (int i = 0; i < datesToCheck.length; i++) {
            DatePickerPage page = new DatePickerPage(driver);
            page.datePickerField.click();
            String[] date = splitTestedDate(datesToCheck, i);
            if ((Integer.parseInt(date[2])) < (Integer.parseInt(page.year.getText()))) {
                moveIntoDesiredDirection(i, page, LEFT);
            } else if ((Integer.parseInt(date[2])) == (Integer.parseInt(page.year.getText()))) {
                if ((Integer.parseInt(date[1])) < (Integer.parseInt(convertMonthToNumber(page)))) {
                    moveIntoDesiredDirection(i, page, LEFT);
                } else if ((Integer.parseInt(date[1])) > (Integer.parseInt(convertMonthToNumber(page)))) {
                    moveIntoDesiredDirection(i, page, RIGHT);
                } else {
                    try {
                        page.highlighted.click();
                    } catch (NoSuchElementException ignored) {
                        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active"))
                                .click();
                    }
                }
            } else {
                moveIntoDesiredDirection(i, page, RIGHT);
            }
            System.out.println((convertToNonAmericanDate(getDateFromField(page))) + " compared to: " + datesToCheck[i]);
            Assert.assertEquals((convertToNonAmericanDate(getDateFromField(page))), datesToCheck[i]);
        }
    }

    public void moveIntoDesiredDirection(int index, DatePickerPage page, String direction) {
        while (true) {
            if (direction.equals(LEFT)) {
                page.leftArrow.click();
            } else if (direction.equals(RIGHT)) {
                page.rightArrow.click();
            } else {
                throw new RuntimeException("Unsupported direction passed to the direction parameter");
            }
            String[] date = splitTestedDate(datesToCheck, index);
            if ((page.year.getText()
                    .equals(date[2])) && ((Integer.parseInt(convertMonthToNumber(page))) ==
                    (Integer.parseInt(date[1])))) {
                List<WebElement> listOfDays = getListOfDays(page);
                for (WebElement listOfDay : listOfDays) {
                    if (((Integer.parseInt(listOfDay.getText())) == (Integer.parseInt(date[0])))) {
                        listOfDay
                                .click();
                        break;
                    }
                }
                break;
            }
        }
    }

    public String[] splitTestedDate(String[] dates, int index) {
        String[] data = dates[index].split("\\.");
        String day = data[0];
        String month = data[1];
        String year = data[2];
        return new String[]{day, month, year};
    }

    public String getDateFromField(DatePickerPage page) throws IOException, UnsupportedFlavorException {
        Actions actions = new Actions(driver);
        actions.click(page.datePickerField)
                .doubleClick(page.datePickerField)
                .build()
                .perform();
        page.datePickerField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;
        String date = (String) clipboard.getData(dataFlavor);
        return date.replaceAll("/", "\\.");
    }

    public String convertToNonAmericanDate(String dateFromField) {
        String[] data = dateFromField.split("\\.");
        String temp = data[0];
        data[0] = data[1];
        data[1] = temp;
        return data[0] + "." + data[1] + "." + data[2];
    }

    public List<WebElement> getListOfDays(DatePickerPage page) {
        int month = (Integer.parseInt(convertMonthToNumber(page))) - 1;
        return driver.findElements(By.cssSelector("td[data-month='" + month + "']"));
    }

    public String convertMonthToNumber(DatePickerPage page) {
        String month = page.month.getText()
                .toUpperCase();
        for (Month MONTH : MONTHS) {
            if (MONTH.toString()
                    .equals(month)) {
                return String.valueOf(MONTH.getValue());
            }
        }
        return null;

    }

    public String reverseLocalDate(String localDate) {
        String[] data = localDate.split("-");
        return data[2] + "." + data[1] + "." + data[0];
    }
}
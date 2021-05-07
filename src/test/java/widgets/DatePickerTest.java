package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;

public class DatePickerTest extends BaseTest {

    @FindBy(css = "#datepicker")
    WebElement datePickerField;

    @FindBy(css = ".ui-icon.ui-icon-circle-triangle-w")
    WebElement leftArrow;

    @FindBy(css = ".ui-icon.ui-icon-circle-triangle-e")
    WebElement rightArrow;

    @FindBy(css = ".ui-datepicker-month")
    WebElement month;

    @FindBy(css = ".ui-datepicker-year")
    WebElement year;

    @FindBy(css = ".ui-state-default.ui-state-highlight")
    WebElement highlighted;

    String localDate = convertLocalDate(LocalDate.now().toString());
    String[] dates = {"30.10.2018", "25.09.2018", "25.09.2018", "01.01.2018", "01.02.2018", localDate, "10.10.2021"};
    String currentDate;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/datepicker.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void datePickerTest() throws IOException, UnsupportedFlavorException {
        for (int i = 0; i < dates.length; i++) {
            datePickerField.click();
            try {
                currentDate = highlighted.getText() + "." + convertMonthToNumber() + "." + year.getText();
            } catch (NoSuchElementException ignored) {
                currentDate = driver.findElement(By.cssSelector(".ui-state-default.ui-state-active"))
                        .getText() + "." + convertMonthToNumber() + "." + year.getText();
            }
            String[] date = splitDates(dates, i);
            if ((Integer.parseInt(date[2])) < (Integer.parseInt(year.getText()))) {
                goLeft(i);
            } else if ((Integer.parseInt(date[2])) == (Integer.parseInt(year.getText()))) {
                if ((Integer.parseInt(date[1])) < (Integer.parseInt(convertMonthToNumber()))) {
                    goLeft(i);
                } else if ((Integer.parseInt(date[1])) > (Integer.parseInt(convertMonthToNumber()))) {
                    goRight(i);
                } else {
                    try {
                        highlighted.click();
                    } catch (NoSuchElementException ignored) {
                        driver.findElement(By.cssSelector(".ui-state-default.ui-state-active"))
                                .click();
                    }
                }
            } else {
                goRight(i);
            }
            System.out.println((convertToNonAmericanDate(getDateFromField())) + " compared to: " + dates[i]);
            Assert.assertEquals((convertToNonAmericanDate(getDateFromField())), dates[i]);
        }
    }

    @AfterMethod
    public void clearDatePickerField() {
        datePickerField.sendKeys(Keys.BACK_SPACE);
        driver.findElement(By.cssSelector(".display-4"))
                .click();
    }

    public void goLeft(int index) {
        while (true) {
            leftArrow.click();
            String[] date = splitDates(dates, index);
            if ((year.getText()
                    .equals(date[2])) && ((Integer.parseInt(convertMonthToNumber())) ==
                    (Integer.parseInt(date[1])))) {
                List<WebElement> listOfDays = getListOfDays();
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

    public void goRight(int index) {
        while (true) {
            rightArrow.click();
            String[] date = splitDates(dates, index);
            if ((year.getText()
                    .equals(date[2])) && ((Integer.parseInt(convertMonthToNumber())) ==
                    (Integer.parseInt(date[1])))) {
                List<WebElement> listOfDays = getListOfDays();
                for (WebElement listOfDay : listOfDays) {
                    if ((Integer.parseInt(listOfDay.getText())) == (Integer.parseInt(date[0]))) {
                        listOfDay
                                .click();
                        break;
                    }
                }
                break;
            }
        }
    }

    public String[] splitDates(String[] dates, int index) {
        String[] split = dates[index].split("\\.");
        String day = split[0];
        String month = split[1];
        String year = split[2];
        return new String[]{day, month, year};
    }

    public String getDateFromField() throws IOException, UnsupportedFlavorException {
        Actions actions = new Actions(driver);
        actions.click(datePickerField)
                .doubleClick(datePickerField)
                .build()
                .perform();
        datePickerField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;
        String date = (String) clipboard.getData(dataFlavor);
        return date.replaceAll("/", "\\.");
    }

    public String convertToNonAmericanDate(String dateFromField) {
        String[] split = dateFromField.split("\\.");
        String temp = split[0];
        split[0] = split[1];
        split[1] = temp;
        return split[0] + "." + split[1] + "." + split[2];
    }

    public List<WebElement> getListOfDays() {
        int month = (Integer.parseInt(convertMonthToNumber())) - 1;
        return driver.findElements(By.cssSelector("td[data-month='" + month + "']"));
    }

    public String convertMonthToNumber() {
        switch (month.getText()) {
            case "January":
                return "1";
            case "February":
                return "2";
            case "March":
                return "3";
            case "April":
                return "4";
            case "May":
                return "5";
            case "June":
                return "6";
            case "July":
                return "7";
            case "August":
                return "8";
            case "September":
                return "9";
            case "October":
                return "10";
            case "November":
                return "11";
            case "December":
                return "12";
        }
        return null;
    }

    public String convertLocalDate(String localDate) {
        String[] split = localDate.split("-");
        return split[2] + "." + split[1] + "." + split[0];
    }
}
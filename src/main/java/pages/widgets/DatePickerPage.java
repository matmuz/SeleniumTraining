package pages.widgets;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.time.Month;
import java.util.Arrays;
import java.util.List;

public class DatePickerPage extends BasePage {

    public static final List<Month> MONTHS = Arrays.asList(Month.values());
    public static final String LEFT = "left";
    public static final String RIGHT = "right";

    public DatePickerPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#datepicker")
    public WebElement datePickerField;

    @FindBy(css = ".ui-icon.ui-icon-circle-triangle-w")
    public WebElement leftArrow;

    @FindBy(css = ".ui-icon.ui-icon-circle-triangle-e")
    public WebElement rightArrow;

    @FindBy(css = ".ui-datepicker-month")
    public WebElement month;

    @FindBy(css = ".ui-datepicker-year")
    public WebElement year;

    @FindBy(css = ".ui-state-default.ui-state-highlight")
    public WebElement highlighted;


    public void moveIntoDesiredDirection(int index, DatePickerPage page, String direction, String[] datesToCheck) {
        while (true) {
            if (direction.equals(LEFT)) {
                page.leftArrow.click();
            } else if (direction.equals(RIGHT)) {
                page.rightArrow.click();
            } else {
                throw new RuntimeException("Unsupported direction passed to the direction parameter");
            }
            String[] date = splitTestedDate(datesToCheck, index);
            if ((page.year.getText().equals(date[2])) && ((Integer.parseInt(convertMonthToNumber(page))) ==
                    (Integer.parseInt(date[1])))) {
                List<WebElement> listOfDays = getListOfDays(page);
                for (WebElement listOfDay : listOfDays) {
                    if (((Integer.parseInt(listOfDay.getText())) == (Integer.parseInt(date[0])))) {
                        listOfDay.click();
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
        actions.click(page.datePickerField).doubleClick(page.datePickerField).build().perform();
        page.datePickerField.sendKeys(Keys.chord(Keys.CONTROL, "c"));
        Clipboard clipboard = Toolkit.getDefaultToolkit().getSystemClipboard();
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
        String month = page.month.getText().toUpperCase();
        for (Month MONTH : MONTHS) {
            if (MONTH.toString().equals(month)) {
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
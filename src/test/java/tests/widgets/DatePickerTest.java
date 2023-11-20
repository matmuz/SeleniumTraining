package tests.widgets;

import driver.ChromeDriverManager;
import driver.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import static java.time.Duration.ofSeconds;
import static org.openqa.selenium.By.cssSelector;
import static org.openqa.selenium.support.ui.ExpectedConditions.elementToBeClickable;
import static org.testng.Assert.assertEquals;

public class DatePickerTest {

    private ChromeDriverManager driverManager;
    private WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driverManager = DriverManagerFactory.getManager();
        driver = driverManager.getDriver();
        driver.get("http://seleniumui.moderntester.pl/datepicker.php");
    }

    @Test
    public void datePickerTest() throws ParseException {
        DateFormat givenFormat = new SimpleDateFormat("dd.MM.yyyy");
        DateFormat datePickerFormat = new SimpleDateFormat("MMMM yyyy", Locale.ENGLISH);
        DateFormat displayedFormat = new SimpleDateFormat("MM/dd/yyyy");

        String[] datesToCheck = {"30.10.2018", "25.09.2018", "25.09.2018", "01.01.2018", "01.02.2018",
                givenFormat.format(new Date()), "10.10.2021"};

        for (String dateToCheck : datesToCheck) {

            String formattedExpectedDate = displayedFormat.format(givenFormat.parse(dateToCheck));

            driver.findElement(cssSelector("#datepicker")).click();
            WebDriverWait wait = new WebDriverWait(driver, ofSeconds(5));
            wait.until(elementToBeClickable(driver.findElement(cssSelector(".ui-datepicker-title"))));

            Date dateToCheckAsDate = datePickerFormat.parse(datePickerFormat.format(givenFormat.parse(dateToCheck)));
            Date displayedDateAsDate = datePickerFormat.parse(driver.findElement(cssSelector(".ui-datepicker-title"))
                                                                    .getText());

            String singleDay = dateToCheck.substring(0, dateToCheck.indexOf("."));

            while (dateToCheckAsDate.compareTo(displayedDateAsDate) < 0) {
                driver.findElement(cssSelector(".ui-icon.ui-icon-circle-triangle-w")).click();
                displayedDateAsDate = datePickerFormat.parse(driver.findElement(cssSelector(".ui-datepicker-title"))
                                                                   .getText());
            }
            while (dateToCheckAsDate.compareTo(displayedDateAsDate) > 0) {
                driver.findElement(cssSelector(".ui-icon.ui-icon-circle-triangle-e")).click();
                displayedDateAsDate = datePickerFormat.parse(driver.findElement(cssSelector(".ui-datepicker-title"))
                                                                   .getText());
            }
            driver.findElements(cssSelector(".ui-state-default.ui-state-highlight, .ui-state-default.ui-state-active," +
                                                    " a[class='ui-state-default']"))
                  .get(Integer.parseInt(singleDay) - 1)
                  .click();

            assertEquals(driver.findElement(cssSelector("#datepicker")).getAttribute("value"),
                         formattedExpectedDate);
        }
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver(driver);
    }
}
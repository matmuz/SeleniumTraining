package pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class DatePickerPage extends BasePage {

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
}

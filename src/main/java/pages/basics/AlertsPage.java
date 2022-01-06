package pages.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AlertsPage extends BasePage {

    public static final String ALERTS_PAGE = "https://seleniumui.moderntester.pl/alerts.php";
    public static final String OK_PRESSED = "OK button pressed";
    public static final String CANCEL_PRESSED = "You pressed Cancel!";
    public static final String YOU_PRESSED_OK = "You pressed OK!";

    public AlertsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#simple-alert")
    public WebElement simpleAlert;

    @FindBy(css = "#simple-alert-label")
    public WebElement simpleAlertLabel;

    @FindBy(css = "#prompt-alert")
    public WebElement promptAlert;

    @FindBy(css = "#prompt-label")
    public WebElement promptLabel;

    @FindBy(css = "#confirm-alert")
    public WebElement confirmAlert;

    @FindBy(css = "#confirm-label")
    public WebElement confirmLabel;

    @FindBy(css = "#delayed-alert")
    public WebElement delayedAlert;

    @FindBy(css = "#delayed-alert-label")
    public WebElement delayedLabel;

    public static String getAlertResponseMessage(String firstName, String lastName) {
        return "Hello " + firstName + " " + lastName + "! How are you today?";
    }
}
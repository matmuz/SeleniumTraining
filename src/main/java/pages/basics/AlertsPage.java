package pages.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class AlertsPage extends BasePage {

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
}
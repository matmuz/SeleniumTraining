package basics;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class AlertsTests extends BaseTest {

    @FindBy(css = "#simple-alert")
    WebElement simpleAlert;

    @FindBy(css = "#simple-alert-label")
    WebElement simpleAlertLabel;

    @FindBy(css = "#prompt-alert")
    WebElement promptAlert;

    @FindBy(css = "#prompt-label")
    WebElement promptLabel;

    @FindBy(css = "#confirm-alert")
    WebElement confirmAlert;

    @FindBy(css = "#confirm-label")
    WebElement confirmLabel;

    @FindBy(css = "#delayed-alert")
    WebElement delayedAlert;

    @FindBy(css = "#delayed-alert-label")
    WebElement delayedLabel;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/alerts.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void alertsTests(){

        simpleAlert.click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(simpleAlertLabel.getText(), "OK button pressed");

        promptAlert.click();
        Alert temp = driver.switchTo().alert();
        temp.sendKeys("Lord Voldemort");
        temp.accept();
        Assert.assertEquals(promptLabel.getText(), "Hello Lord Voldemort! How are you today?");

        confirmAlert.click();
        temp = driver.switchTo().alert();
        temp.accept();
        Assert.assertEquals(confirmLabel.getText(), "You pressed OK!");
        confirmAlert.click();
        temp = driver.switchTo().alert();
        temp.dismiss();
        Assert.assertEquals(confirmLabel.getText(), "You pressed Cancel!");

        delayedAlert.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        temp = driver.switchTo().alert();
        temp.accept();
        Assert.assertEquals(delayedLabel.getText(), "OK button pressed");
    }
}
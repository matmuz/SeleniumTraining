package basics;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.AlertsPage;

public class AlertsTests extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/alerts.php");
    }

    @Test
    public void alertsTests() {
        AlertsPage alertsPage = new AlertsPage(driver);

        alertsPage.simpleAlert.click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertsPage.simpleAlertLabel.getText(), "OK button pressed");

        alertsPage.promptAlert.click();
        Alert alertContainer = driver.switchTo().alert();
        alertContainer.sendKeys("Lord Voldemort");
        alertContainer.accept();
        Assert.assertEquals(alertsPage.promptLabel.getText(), "Hello Lord Voldemort! How are you today?");

        alertsPage.confirmAlert.click();
        alertContainer = driver.switchTo().alert();
        alertContainer.accept();
        Assert.assertEquals(alertsPage.confirmLabel.getText(), "You pressed OK!");

        alertsPage.confirmAlert.click();
        alertContainer = driver.switchTo().alert();
        alertContainer.dismiss();
        Assert.assertEquals(alertsPage.confirmLabel.getText(), "You pressed Cancel!");

        alertsPage.delayedAlert.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        alertContainer = driver.switchTo().alert();
        alertContainer.accept();
        Assert.assertEquals(alertsPage.delayedLabel.getText(), "OK button pressed");
    }
}
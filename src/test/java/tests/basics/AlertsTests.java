package tests.basics;

import base.BaseTest;
import org.openqa.selenium.Alert;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.AlertsPage;
import waiter.Waiter;

import static pages.basics.AlertsPage.*;
import static utils.DataConstants.*;

public class AlertsTests extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get(ALERTS_PAGE);
    }

    @Test
    public void alertsTests() {
        AlertsPage alertsPage = new AlertsPage(driver);

        alertsPage.simpleAlert.click();
        driver.switchTo().alert().accept();
        Assert.assertEquals(alertsPage.simpleAlertLabel.getText(), OK_PRESSED);

        alertsPage.promptAlert.click();
        Alert alertContainer = driver.switchTo().alert();
        alertContainer.sendKeys(getFullName());
        alertContainer.accept();
        Assert.assertEquals(alertsPage.promptLabel.getText(), getAlertResponseMessage(FIRSTNAME, LASTNAME));

        alertsPage.confirmAlert.click();
        alertContainer = driver.switchTo().alert();
        alertContainer.accept();
        Assert.assertEquals(alertsPage.confirmLabel.getText(), YOU_PRESSED_OK);

        alertsPage.confirmAlert.click();
        alertContainer = driver.switchTo().alert();
        alertContainer.dismiss();
        Assert.assertEquals(alertsPage.confirmLabel.getText(), CANCEL_PRESSED);

        alertsPage.delayedAlert.click();
        Waiter.wait(driver).until(ExpectedConditions.alertIsPresent());
        alertContainer = driver.switchTo().alert();
        alertContainer.accept();
        Assert.assertEquals(alertsPage.delayedLabel.getText(), OK_PRESSED);
    }
}
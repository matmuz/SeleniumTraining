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

        AlertsPage page = new AlertsPage(driver);

        page.simpleAlert.click();
        driver.switchTo()
                .alert()
                .accept();
        Assert.assertEquals(page.simpleAlertLabel.getText(), "OK button pressed");

        page.promptAlert.click();
        Alert temp = driver.switchTo()
                .alert();
        temp.sendKeys("Lord Voldemort");
        temp.accept();
        Assert.assertEquals(page.promptLabel.getText(), "Hello Lord Voldemort! How are you today?");

        page.confirmAlert.click();
        temp = driver.switchTo()
                .alert();
        temp.accept();
        Assert.assertEquals(page.confirmLabel.getText(), "You pressed OK!");
        page.confirmAlert.click();
        temp = driver.switchTo()
                .alert();
        temp.dismiss();
        Assert.assertEquals(page.confirmLabel.getText(), "You pressed Cancel!");

        page.delayedAlert.click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.alertIsPresent());
        temp = driver.switchTo()
                .alert();
        temp.accept();
        Assert.assertEquals(page.delayedLabel.getText(), "OK button pressed");
    }
}
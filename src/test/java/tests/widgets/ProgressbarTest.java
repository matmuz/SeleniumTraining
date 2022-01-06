package tests.widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waiter.Waiter;

public class ProgressbarTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/progressbar.php");
    }

    @Test
    public void progressbarTest() {
        Assert.assertTrue(Waiter.wait(driver)
                                .until(ExpectedConditions.textToBePresentInElement(
                                        driver.findElement(By.cssSelector("#progressbar")), "Complete!")));
    }
}

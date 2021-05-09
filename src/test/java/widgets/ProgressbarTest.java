package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ProgressbarTest extends BaseTest {

    @BeforeMethod
    public void getPage(){
        driver.get("https://seleniumui.moderntester.pl/progressbar.php");
    }

    @Test
    public void progressbarTest(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        Assert.assertTrue(wait.until(ExpectedConditions.textToBePresentInElement(driver.findElement(By.cssSelector("#progressbar")), "Complete!")));
    }
}

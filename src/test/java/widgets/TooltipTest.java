package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class TooltipTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/tooltip.php");
    }

    @Test
    public void tooltipTest() {
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        actions.moveToElement(driver.findElement(By.cssSelector("a[title=\"That's what this widget is\"]")))
               .build()
               .perform();
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role='tooltip']"))));
        System.out.println(driver.findElement(By.cssSelector("div[role='tooltip']")).getText());
        actions.moveToElement(
                       driver.findElement(By.cssSelector("a[title=\"ThemeRoller: jQuery UI's theme builder application\"]")))
               .build()
               .perform();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[role='tooltip']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role='tooltip']"))));
        System.out.println(driver.findElement(By.cssSelector("div[role='tooltip']")).getText());
        actions.moveToElement(driver.findElement(By.cssSelector("#age"))).build().perform();
        wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector("div[role='tooltip']"))));
        wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("div[role='tooltip']"))));
        System.out.println(driver.findElement(By.cssSelector("div[role='tooltip']")).getText());
    }
}

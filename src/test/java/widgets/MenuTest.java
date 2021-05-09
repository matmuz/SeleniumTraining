package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class MenuTest extends BaseTest {

    @BeforeMethod
    public void getPage(){
        driver.get("https://seleniumui.moderntester.pl/menu-item.php");
    }

    @Test
    public void menuTest(){
        Actions actions = new Actions(driver);
        WebDriverWait wait = new WebDriverWait(driver, 10);
        WebElement music = driver.findElement(By.cssSelector("#ui-id-9"));
        WebElement jazz = driver.findElement(By.cssSelector("#ui-id-13"));
        WebElement modern = driver.findElement(By.cssSelector("#ui-id-16"));
        actions.moveToElement(music).build().perform();
        wait.until(ExpectedConditions.visibilityOf(jazz));
        actions.moveToElement(jazz).build().perform();
        wait.until(ExpectedConditions.visibilityOf(modern));
        actions.moveToElement(modern).click().build().perform();
    }
}

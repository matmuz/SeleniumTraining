package actions;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DraggableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/draggable.php");
    }

    /*
        This test will work only for FHD resolution (1920x1080)
     */
    @Test
    public void draggableTest() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(driver.findElement(By.cssSelector("#draggable")))
                .moveByOffset(1350, -150)
                .moveByOffset(0, 750)
                .moveByOffset(-900, -400)
                .moveByOffset(-800, 400)
                .build()
                .perform();
    }
}
package actions;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResizableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/resizable.php");
    }

    @Test
    public void resizableTest() {
        new Actions(driver).clickAndHold(driver.findElement(
                                   By.cssSelector(".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se")))
                           .moveByOffset(100, 0)
                           .moveByOffset(0, 100)
                           .moveByOffset(100, 100)
                           .release()
                           .build()
                           .perform();
    }
}
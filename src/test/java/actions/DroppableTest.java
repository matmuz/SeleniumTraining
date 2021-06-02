package actions;

import base.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.actions.DroppablePage;

public class DroppableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/droppable.php");
    }

    @Test
    public void droppableTest() {
        DroppablePage page = new DroppablePage(driver);
        Actions actions = new Actions(driver);
        actions.clickAndHold(page.elementToDrag)
                .moveToElement(page.elementToDrop)
                .release()
                .build()
                .perform();
        Assert.assertEquals(page.elementToDrop.getText(), "Dropped!");
    }
}
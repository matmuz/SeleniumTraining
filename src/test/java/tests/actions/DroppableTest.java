package tests.actions;

import base.BaseTest;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.actions.DroppablePage;

import static pages.actions.DroppablePage.DROPPABLE_PAGE;
import static pages.actions.DroppablePage.DROPPED_MESSAGE;

public class DroppableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get(DROPPABLE_PAGE);
    }

    @Test
    public void droppableTest() {
        DroppablePage droppablePage = new DroppablePage(driver);

        new Actions(driver).clickAndHold(droppablePage.elementToDrag)
                .moveToElement(droppablePage.elementToDrop)
                .release()
                .build()
                .perform();
        Assert.assertEquals(droppablePage.elementToDrop.getText(), DROPPED_MESSAGE);
    }
}
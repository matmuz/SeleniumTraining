package actions;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class DroppableTest extends BaseTest {

    @FindBy(css = "#draggable")
    WebElement elementToDrag;

    @FindBy(css = "#droppable")
    WebElement elementToDrop;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/droppable.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void droppableTest() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(elementToDrag)
                .moveToElement(elementToDrop)
                .release()
                .build()
                .perform();
        Assert.assertEquals(elementToDrop.getText(), "Dropped!");
    }
}
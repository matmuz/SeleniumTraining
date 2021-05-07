package actions;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class ResizableTest extends BaseTest {

    @FindBy(css = ".ui-resizable-handle.ui-resizable-se.ui-icon.ui-icon-gripsmall-diagonal-se")
    WebElement resizableIcon;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/resizable.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void resizableTest() {
        Actions actions = new Actions(driver);
        actions.clickAndHold(resizableIcon)
                .moveByOffset(100, 0)
                .moveByOffset(0, 100)
                .moveByOffset(100, 100)
                .release()
                .build()
                .perform();
    }
}

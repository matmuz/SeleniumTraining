package actions;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class SelectableTest extends BaseTest {

    @FindBy(css = ".ui-widget-content.ui-selectee")
    List<WebElement> selectableObjects;

    @FindBy(css = "#feedback")
    WebElement feedback;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/selectable.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void selectableTest() {
        Actions actions = new Actions(driver);
        for (WebElement selectableObject : selectableObjects) {
            if (selectableObject
                    .getText()
                    .contains("1") || selectableObject
                    .getText()
                    .contains("3") || selectableObject
                    .getText()
                    .contains("4")) {
                actions.keyDown(Keys.CONTROL)
                        .click(selectableObject)
                        .build()
                        .perform();
            }
        }
        Assert.assertEquals(feedback.getText(), "You've selected: #1 #3 #4.");
    }
}

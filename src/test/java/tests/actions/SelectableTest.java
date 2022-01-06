package tests.actions;

import base.BaseTest;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.actions.SelectablePage;

import static pages.actions.SelectablePage.SELECTABLE_PAGE;

public class SelectableTest extends BaseTest {


    @BeforeMethod
    public void getPage() {
        driver.get(SELECTABLE_PAGE);
    }

    @Test
    public void selectableTest() {
        SelectablePage selectablePage = new SelectablePage(driver);
        for (WebElement selectableObject : selectablePage.selectableObjects) {
            if (selectableObject.getText().contains("1") || selectableObject.getText().contains("3") ||
                    selectableObject.getText().contains("4")) {
                new Actions(driver).keyDown(Keys.CONTROL).click(selectableObject).build().perform();
            }
        }
        Assert.assertEquals(selectablePage.feedback.getText(), "You've selected: #1 #3 #4.");
    }
}
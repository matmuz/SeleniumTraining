package actions;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortableTest extends BaseTest {

    @FindBy(css = ".ui-state-default.ui-sortable-handle")
    List<WebElement> sortableObjects;

    List<Integer> numbers = new ArrayList<>();

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/sortable.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void sortableTest() {
        for (int i = 1; i <= 7; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Actions actions = new Actions(driver);
        for (int i = 0; i < sortableObjects.size(); i++) {
            if (getNumberFromWebElement(sortableObjects, i) != numbers.get(i)) {
                for (int y = 0; y < sortableObjects.size(); y++) {
                    if (getNumberFromWebElement(sortableObjects, y) == numbers.get(i)) {
                        actions.clickAndHold(sortableObjects.get(y))
                                .moveToElement(sortableObjects.get(i))
                                .release()
                                .build()
                                .perform();
                    }
                }
            }
        }
        List<Integer> numbersFromPage = new ArrayList<>();
        for (int i = 0; i < sortableObjects.size(); i++) {
            numbersFromPage.add(getNumberFromWebElement(sortableObjects, i));
        }
        Assert.assertEquals(numbers, numbersFromPage);
    }

    public int getNumberFromWebElement(List<WebElement> webElements, int index) {
        String[] split = webElements.get(index)
                .getText()
                .split(" ");
        return Integer.parseInt(split[1]);
    }
}

package actions;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.actions.SortablePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SortableTest extends BaseTest {

    List<Integer> numbers = new ArrayList<>();

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/sortable.php");
    }

    @Test
    public void sortableTest() {
        SortablePage sortablePage = new SortablePage(driver);
        for (int i = 1; i <= 7; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Actions actions = new Actions(driver);
        for (int i = 0; i < sortablePage.sortableObjects.size(); i++) {
            if (getNumberFromWebElement(sortablePage.sortableObjects, i) != numbers.get(i)) {
                for (int y = 0; y < sortablePage.sortableObjects.size(); y++) {
                    if (getNumberFromWebElement(sortablePage.sortableObjects, y) == numbers.get(i)) {
                        actions.clickAndHold(sortablePage.sortableObjects.get(y))
                               .moveToElement(sortablePage.sortableObjects.get(i))
                               .release()
                               .build()
                               .perform();
                    }
                }
            }
        }
        List<Integer> numbersFromPage = new ArrayList<>();
        for (int i = 0; i < sortablePage.sortableObjects.size(); i++) {
            numbersFromPage.add(getNumberFromWebElement(sortablePage.sortableObjects, i));
        }
        Assert.assertEquals(numbers, numbersFromPage);
    }

    public int getNumberFromWebElement(List<WebElement> webElements, int index) {
        String[] split = webElements.get(index).getText().split(" ");
        return Integer.parseInt(split[1]);
    }
}

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
        SortablePage page = new SortablePage(driver);
        for (int i = 1; i <= 7; i++) {
            numbers.add(i);
        }
        Collections.shuffle(numbers);
        Actions actions = new Actions(driver);
        for (int i = 0; i < page.sortableObjects.size(); i++) {
            if (getNumberFromWebElement(page.sortableObjects, i) != numbers.get(i)) {
                for (int y = 0; y < page.sortableObjects.size(); y++) {
                    if (getNumberFromWebElement(page.sortableObjects, y) == numbers.get(i)) {
                        actions.clickAndHold(page.sortableObjects.get(y))
                               .moveToElement(page.sortableObjects.get(i))
                               .release()
                               .build()
                               .perform();
                    }
                }
            }
        }
        List<Integer> numbersFromPage = new ArrayList<>();
        for (int i = 0; i < page.sortableObjects.size(); i++) {
            numbersFromPage.add(getNumberFromWebElement(page.sortableObjects, i));
        }
        Assert.assertEquals(numbers, numbersFromPage);
    }

    public int getNumberFromWebElement(List<WebElement> webElements, int index) {
        String[] split = webElements.get(index).getText().split(" ");
        return Integer.parseInt(split[1]);
    }
}

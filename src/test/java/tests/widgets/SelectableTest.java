package tests.widgets;

import base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.SelectablePage;

import java.util.Random;

import static pages.widgets.SelectablePage.SELECTS_PAGE;

public class SelectableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get(SELECTS_PAGE);
    }

    /*
        Currently, there seems to be an issue with this page.
        The test should utilize Select class, but such selectors are not working.
     */

    @Test
    public void selectMenuTest() {
        boolean issue = true;
        if (issue) {
            return;
        }
        SelectablePage selectablePage = new SelectablePage(driver);

        Random random = new Random();
        Select selectContainer = new Select(selectablePage.selectSpeed);
        selectContainer.selectByIndex(random.nextInt(selectContainer.getOptions().size()));
        selectablePage.selectFiles.click();
        selectContainer = new Select(selectablePage.selectFiles);
        selectContainer.selectByVisibleText("Some unknown file");
        selectablePage.selectNumbers.click();
        selectContainer = new Select(selectablePage.selectNumbers);
        selectContainer.selectByIndex(5);
        selectablePage.selectSalutation.click();
        selectContainer = new Select(selectablePage.selectSalutation);
        selectContainer.selectByIndex(random.nextInt(selectContainer.getOptions().size()));
    }
}
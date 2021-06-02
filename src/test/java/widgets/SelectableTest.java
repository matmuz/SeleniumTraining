package widgets;

import base.BaseTest;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.SelectablePage;

import java.util.Random;

public class SelectableTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/selectmenu.php");
    }

    /*
        Currently there seems to be an issue with this page.
        The test should utilize Select class, but such selectors are not working.
     */

    @Test
    public void selectMenuTest() {
        boolean issue = true;
        if (issue) {
            return;
        }
        SelectablePage page = new SelectablePage(driver);
        Random random = new Random();
        Select select = new Select(page.selectSpeed);
        select.selectByIndex(random.nextInt(select.getOptions()
                                                    .size()));
        page.selectFiles.click();
        select = new Select(page.selectFiles);
        select.selectByVisibleText("Some unknown file");
        page.selectNumbers.click();
        select = new Select(page.selectNumbers);
        select.selectByIndex(5);
        page.selectSalutation.click();
        select = new Select(page.selectSalutation);
        select.selectByIndex(random.nextInt(select.getOptions()
                                                    .size()));
    }
}
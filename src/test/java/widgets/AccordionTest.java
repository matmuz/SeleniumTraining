package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.AccordionsPage;

public class AccordionTest extends BaseTest {


    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/accordion.php");
    }

    @Test
    public void accordionTest() {
        AccordionsPage accordionsPage = new AccordionsPage(driver);
        accordionsPage.accordionHeaders.get(0).click();
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i < accordionsPage.accordionHeaders.size(); i++) {
            accordionsPage.accordionHeaders.get(i).click();
            wait.until(ExpectedConditions.attributeToBe(driver.findElements(By.cssSelector("h3")).
                                                              get(i), "aria-expanded", "true"));
            System.out.println(accordionsPage.accordionContents.get(i).getText());
        }
    }
}
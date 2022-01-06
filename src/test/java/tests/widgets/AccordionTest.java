package tests.widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.widgets.AccordionsPage;
import waiter.Waiter;

import static pages.widgets.AccordionsPage.ACCORDION_PAGE;

public class AccordionTest extends BaseTest {


    @BeforeMethod
    public void getPage() {
        driver.get(ACCORDION_PAGE);
    }

    @Test
    public void accordionTest() {
        AccordionsPage accordionsPage = new AccordionsPage(driver);
        accordionsPage.accordionHeaders.get(0).click();
        for (int i = 0; i < accordionsPage.accordionHeaders.size(); i++) {
            accordionsPage.accordionHeaders.get(i).click();
            Waiter.wait(driver)
                  .until(ExpectedConditions.attributeToBe(driver.findElements(By.cssSelector("h3")).get(i),
                                                          "aria-expanded", "true"));
            System.out.println(accordionsPage.accordionContents.get(i).getText());
        }
    }
}
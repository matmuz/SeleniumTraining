package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;

public class AccordionTest extends BaseTest {

    @FindBy(css = "h3[role='tab']")
    List<WebElement> accordionHeaders;

    @FindBy(css = "div[role='tabpanel']")
    List<WebElement> accordionContents;

    @BeforeMethod
    public void initializeElements(){
        driver.get("https://seleniumui.moderntester.pl/accordion.php");
        PageFactory.initElements(driver, this);
        accordionHeaders.get(0).click();
    }

    @Test
    public void accordionTest(){
        WebDriverWait wait = new WebDriverWait(driver, 10);
        for (int i = 0; i < accordionHeaders.size(); i++){
            accordionHeaders.get(i).click();
            wait.until(ExpectedConditions.attributeToBe(driver.findElements(By.cssSelector("h3")).get(i), "aria-expanded", "true"));
            System.out.println(accordionContents.get(i).getText());
        }
    }
}
package basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.IFramesPage;

import java.util.Random;

public class IFramesTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/iframes.php");
    }

    @Test
    public void iFramesTest() {
        IFramesPage page = new IFramesPage(driver);
        driver.switchTo().frame(page.firstIFrame);
        page.firstName.sendKeys("Arthur");
        page.surname.sendKeys("Morgan");
        page.signInButton.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(page.secondIFrame);
        page.login.sendKeys("mat");
        page.password.sendKeys("test123");
        Select select = new Select(page.selectContinents);
        select.selectByValue("europe");
        Random random = new Random();
        page.yearsOfExperienceRadioButtons.get(random.nextInt(page.yearsOfExperienceRadioButtons.size())).click();
        page.signInButton.click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".nav-link.dropdown-toggle")).click();
    }
}
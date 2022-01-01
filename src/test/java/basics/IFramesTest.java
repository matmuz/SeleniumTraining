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
        IFramesPage iFramesPage = new IFramesPage(driver);

        driver.switchTo().frame(iFramesPage.firstIFrame);
        iFramesPage.firstName.sendKeys("Arthur");
        iFramesPage.surname.sendKeys("Morgan");
        iFramesPage.signInButton.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iFramesPage.secondIFrame);
        iFramesPage.login.sendKeys("mat");
        iFramesPage.password.sendKeys("test123");
        Select select = new Select(iFramesPage.selectContinents);
        select.selectByValue("europe");
        Random random = new Random();
        iFramesPage.yearsOfExperienceRadioButtons.get(random.nextInt(iFramesPage.yearsOfExperienceRadioButtons.size())).click();
        iFramesPage.signInButton.click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".nav-link.dropdown-toggle")).click();
    }
}
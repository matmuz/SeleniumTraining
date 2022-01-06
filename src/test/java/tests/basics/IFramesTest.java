package tests.basics;

import base.BaseTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.IFramesPage;

import static pages.basics.IFramesPage.IFRAMES_PAGE;
import static utils.DataConstants.*;
import static utils.MethodsProvider.*;

public class IFramesTest extends BaseTest {

    private final String valueToCheck = "europe";

    @BeforeMethod
    public void getPage() {
        driver.get(IFRAMES_PAGE);
    }

    @Test
    public void iFramesTest() {
        IFramesPage iFramesPage = new IFramesPage(driver);

        driver.switchTo().frame(iFramesPage.firstIFrame);
        iFramesPage.firstName.sendKeys(FIRSTNAME);
        iFramesPage.surname.sendKeys(LASTNAME);
        iFramesPage.signInButton.click();
        driver.switchTo().defaultContent();
        driver.switchTo().frame(iFramesPage.secondIFrame);
        iFramesPage.login.sendKeys(EMAIL);
        iFramesPage.password.sendKeys(PASSWORD);
        selectOptions(iFramesPage.selectContinents).selectByValue(valueToCheck);
        iFramesPage.yearsOfExperienceRadioButtons.get(
                chooseRandom(iFramesPage.yearsOfExperienceRadioButtons.size(), RANDOM)).click();
        iFramesPage.signInButton.click();
        driver.switchTo().defaultContent();
        iFramesPage.pageElement.click();
    }
}
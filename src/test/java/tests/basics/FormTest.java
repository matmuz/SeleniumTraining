package tests.basics;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.FormPage;

import java.io.File;

import static pages.basics.FormPage.FORM_PAGE_URL;
import static pages.basics.FormPage.FORM_SUCCESS;
import static utils.DataConstants.*;
import static utils.MethodsProvider.*;

public class FormTest extends BaseTest {

    private final String[] commandsToCheck = {"Browser Commands", "Wait Commands"};
    private final File testFile = new File(FILE_PATH);
    private final String filePath = testFile.getAbsolutePath();

    @BeforeMethod
    public void getPage() {
        driver.get(FORM_PAGE_URL);
    }

    @Test
    public void formTest() {
        FormPage formPage = new FormPage(driver);

        formPage.firstName.sendKeys(FIRSTNAME);
        formPage.lastName.sendKeys(LASTNAME);
        formPage.email.sendKeys(EMAIL);
        chooseRandom(formPage.sexRadioButtons, RANDOM);
        formPage.age.sendKeys(AGE);
        chooseRandom(formPage.yearsOfExperienceRadioButtons, RANDOM);
        formPage.professionRadioButtons.get(1).click();
        selectOptions(formPage.continents).selectByIndex(chooseRandom(selectOptions(
                formPage.continents).getOptions().size(), RANDOM));
        for (String s : commandsToCheck) {
            selectOptions(formPage.commands).selectByVisibleText(s);
        }
        formPage.filePath.sendKeys(filePath);
        formPage.additionalInfo.sendKeys("This is a test");
        formPage.signInButton.click();

        Assert.assertEquals(formPage.validatorMessage.getText(), FORM_SUCCESS);
    }
}
package basics;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.FormPage;

import java.io.File;
import java.util.List;
import java.util.Random;

public class FormTest extends BaseTest {


    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/form.php");
    }

    @Test
    public void formTest() {
        File testFile = new File("src/test/resources/docTestPDFFill.pdf");
        String filePath =  testFile.getAbsolutePath();
        FormPage formPage = new FormPage(driver);

        formPage.firstName.sendKeys("Arthur");
        formPage.lastName.sendKeys("Morgan");
        formPage.email.sendKeys("test@test.com");
        chooseRandom(formPage.sexRadioButtons);
        formPage.age.sendKeys("26");
        chooseRandom(formPage.yearsOfExperienceRadioButtons);
        formPage.professionRadioButtons.get(1).click();
        selectOptions(formPage.continents).selectByIndex(chooseRandom(selectOptions(
                formPage.continents).getOptions().size()));
        String[] commandsToCheck = {"Browser Commands", "Wait Commands"};
        for (String s : commandsToCheck) {
            selectOptions(formPage.commands).selectByVisibleText(s);
        }
        formPage.filePath.sendKeys(filePath);
        formPage.additionalInfo.sendKeys("This is a test");
        formPage.signInButton.click();
        Assert.assertEquals(formPage.validatorMessage.getText(), "Form send with success");
    }

    public void chooseRandom(List<WebElement> radios) {
        Random random = new Random();
        radios.get(random.nextInt(radios.size())).click();
    }

    public int chooseRandom(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public Select selectOptions(WebElement selectElement) {
        return new Select(selectElement);
    }
}
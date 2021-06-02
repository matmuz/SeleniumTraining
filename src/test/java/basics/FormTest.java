package basics;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.FormPage;

import java.util.List;
import java.util.Random;

public class FormTest extends BaseTest {


    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/form.php");
    }

    @Test
    public void formTest() {
        FormPage page = new FormPage(driver);
        page.firstName.sendKeys("Arthur");
        page.lastName.sendKeys("Morgan");
        page.email.sendKeys("test@test.com");
        chooseRandom(page.sexRadioButtons);
        page.age.sendKeys("26");
        chooseRandom(page.yearsOfExperienceRadioButtons);
        page.professionRadioButtons.get(1)
                .click();
        selectOptions(page.continents).selectByIndex(chooseRandom(selectOptions(page.continents).getOptions()
                                                                          .size()));
        String[] commandsToCheck = {"Browser Commands", "Wait Commands"};
        for (String s : commandsToCheck) {
            selectOptions(page.commands).selectByVisibleText(s);
        }
        page.filePath.sendKeys("C:\\Users\\mateu\\Desktop\\Pliki\\test.txt");
        page.additionalInfo.sendKeys("This is a test");
        page.signInButton.click();
        Assert.assertEquals(page.validatorMessage.getText(), "Form send with success");
    }

    public void chooseRandom(List<WebElement> radios) {
        Random random = new Random();
        radios.get(random.nextInt(radios.size()))
                .click();
    }

    public int chooseRandom(int size) {
        Random random = new Random();
        return random.nextInt(size);
    }

    public Select selectOptions(WebElement selectElement) {
        return new Select(selectElement);
    }
}
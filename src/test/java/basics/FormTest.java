package basics;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class FormTest extends BaseTest {

    @FindBy(css = "#inputFirstName3")
    WebElement firstName;

    @FindBy(css = "#inputLastName3")
    WebElement lastName;

    @FindBy(css = "#inputEmail3")
    WebElement email;

    @FindBy(css = "input[name='gridRadiosSex']")
    List<WebElement> sexRadioButtons;

    @FindBy(css = "#inputAge3")
    WebElement age;

    @FindBy(css = "input[name='gridRadiosExperience']")
    List<WebElement> yearsOfExperienceRadioButtons;

    @FindBy(css = "input[name='gridCheckboxProfession']")
    List<WebElement> professionRadioButtons;

    @FindBy(css = "select[id='selectContinents']")
    WebElement continents;

    @FindBy(css = "select[id='selectSeleniumCommands']")
    WebElement commands;

    @FindBy(css = "#chooseFile")
    WebElement filePath;

    @FindBy(css = "#additionalInformations")
    WebElement additionalInfo;

    @FindBy(css = ".btn.btn-secondary.btn-lg.active")
    WebElement testFileButton;

    @FindBy(css = ".btn.btn-primary")
    WebElement signInButton;

    @FindBy(css = "#validator-message")
    WebElement validatorMessage;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/form.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void formTest() {
        firstName.sendKeys("Arthur");
        lastName.sendKeys("Morgan");
        email.sendKeys("test@test.com");
        chooseRandom(sexRadioButtons);
        age.sendKeys("26");
        chooseRandom(yearsOfExperienceRadioButtons);
        professionRadioButtons.get(1)
                .click();
        selectOptions(continents).selectByIndex(chooseRandom(selectOptions(continents).getOptions()
                                                                     .size()));
        String[] commandsToCheck = {"Browser Commands", "Wait Commands", "WebElement Commands"};
        for (String s : commandsToCheck) {
            selectOptions(commands).selectByVisibleText(s);
        }
        filePath.sendKeys("C:\\Users\\mateu\\Desktop\\Pliki\\test.txt");
        additionalInfo.sendKeys("This is a test");
        signInButton.click();
        Assert.assertEquals(validatorMessage.getText(), "Form send with success");
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
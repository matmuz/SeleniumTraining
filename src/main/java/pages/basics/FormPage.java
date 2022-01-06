package pages.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class FormPage extends BasePage {

    public static final String FORM_PAGE_URL = "https://seleniumui.moderntester.pl/form.php";
    public static final String FORM_SUCCESS = "Form send with success";

    public FormPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#inputFirstName3")
    public WebElement firstName;

    @FindBy(css = "#inputLastName3")
    public WebElement lastName;

    @FindBy(css = "#inputEmail3")
    public WebElement email;

    @FindBy(css = "input[name='gridRadiosSex']")
    public List<WebElement> sexRadioButtons;

    @FindBy(css = "#inputAge3")
    public WebElement age;

    @FindBy(css = "input[name='gridRadiosExperience']")
    public List<WebElement> yearsOfExperienceRadioButtons;

    @FindBy(css = "input[name='gridCheckboxProfession']")
    public List<WebElement> professionRadioButtons;

    @FindBy(css = "select[id='selectContinents']")
    public WebElement continents;

    @FindBy(css = "select[id='selectSeleniumCommands']")
    public WebElement commands;

    @FindBy(css = "#chooseFile")
    public WebElement filePath;

    @FindBy(css = "#additionalInformations")
    public WebElement additionalInfo;

    @FindBy(css = ".btn.btn-primary")
    public WebElement signInButton;

    @FindBy(css = "#validator-message")
    public WebElement validatorMessage;
}
package pages.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BasePage {

    protected WebDriver driver;

    public BasePage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
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

    @FindBy(css = ".btn.btn-secondary.btn-lg.active")
    public WebElement testFileButton;

    @FindBy(css = ".btn.btn-primary")
    public WebElement signInButton;

    @FindBy(css = "#validator-message")
    public WebElement validatorMessage;
}

package pages.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class IFramesPage extends BasePage {

    public IFramesPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "iframe[name='iframe1']")
    public WebElement firstIFrame;

    @FindBy(css = "iframe[name='iframe2']")
    public WebElement secondIFrame;

    @FindBy(css = "#inputFirstName3")
    public WebElement firstName;

    @FindBy(css = "#inputSurname3")
    public WebElement surname;

    @FindBy(css = "button[type='submit']")
    public WebElement signInButton;

    @FindBy(css = "#inputLogin")
    public WebElement login;

    @FindBy(css = "#inputPassword")
    public WebElement password;

    @FindBy(css = "#inlineFormCustomSelectPref")
    public WebElement selectContinents;

    @FindBy(css = ".form-check")
    public List<WebElement> yearsOfExperienceRadioButtons;
}

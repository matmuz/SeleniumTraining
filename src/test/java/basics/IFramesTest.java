package basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Random;

public class IFramesTest extends BaseTest {

    @FindBy(css = "iframe[name='iframe1']")
    WebElement firstIFrame;

    @FindBy(css = "iframe[name='iframe2']")
    WebElement secondIFrame;

    @FindBy(css = "#inputFirstName3")
    WebElement firstName;

    @FindBy(css = "#inputSurname3")
    WebElement surname;

    @FindBy(css = "button[type='submit']")
    WebElement signInButton;

    @FindBy(css = "#inputLogin")
    WebElement login;

    @FindBy(css = "#inputPassword")
    WebElement password;

    @FindBy(css = "#inlineFormCustomSelectPref")
    WebElement selectContinents;

    @FindBy(css = ".form-check")
    List<WebElement> yearsOfExperienceRadioButtons;

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/iframes.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void iFramesTest() {
        driver.switchTo()
                .frame(firstIFrame);
        firstName.sendKeys("Arthur");
        surname.sendKeys("Morgan");
        signInButton.click();
        driver.switchTo()
                .defaultContent();
        driver.switchTo()
                .frame(secondIFrame);
        login.sendKeys("mat");
        password.sendKeys("test123");
        Select select = new Select(selectContinents);
        select.selectByValue("europe");
        Random random = new Random();
        yearsOfExperienceRadioButtons.get(random.nextInt(yearsOfExperienceRadioButtons.size()))
                .click();
        signInButton.click();
        driver.switchTo().defaultContent();
        driver.findElement(By.cssSelector(".nav-link.dropdown-toggle")).click();
    }
}

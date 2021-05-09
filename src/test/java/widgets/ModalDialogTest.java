package widgets;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import java.util.List;

public class ModalDialogTest extends BaseTest {

    @FindBy(css = "#create-user")
    WebElement createNewUserButton;

    @FindBy(css = "tr:nth-child(2) td")
    List<WebElement> createdUserDetails;

    @FindBy(css = "#name")
    WebElement name;

    @FindBy(css = "#email")
    WebElement email;

    @FindBy(css = "#password")
    WebElement password;

    @FindBy(css = "button[type='button']")
    List<WebElement> dialogButtons;

    @BeforeMethod
    @DataProvider(name = "data-provider")
    public Object[][] createProvider() {
        return new Object[][]{{"Arthur Morgan", "arthurmorgan@gmail.com", "test123"}};
    }

    @BeforeMethod
    public void initializeElements() {
        driver.get("https://seleniumui.moderntester.pl/modal-dialog.php");
        PageFactory.initElements(driver, this);
    }

    @Test(dataProvider = "data-provider")
    public void modalDialogTest(String parameterName, String parameterEmail, String parameterPassword) {
        createNewUserButton.click();
        driver.switchTo()
                .activeElement();
        name.clear();
        email.clear();
        password.clear();
        name.sendKeys(parameterName);
        email.sendKeys(parameterEmail);
        password.sendKeys(parameterPassword);
        dialogButtons.get(1)
                .click();
        Assert.assertEquals(parameterName, createdUserDetails.get(0)
                .getText());
        Assert.assertEquals(parameterEmail, createdUserDetails.get(1)
                .getText());
        Assert.assertEquals(parameterPassword, createdUserDetails.get(2)
                .getText());
    }
}
package widgets;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.widgets.ModalDialogPage;

public class ModalDialogTest extends BaseTest {

    @BeforeMethod
    @DataProvider(name = "data-provider")
    public Object[][] createProvider() {
        return new Object[][]{{"Arthur Morgan", "arthurmorgan@gmail.com", "test123"}};
    }

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/modal-dialog.php");
    }

    @Test(dataProvider = "data-provider")
    public void modalDialogTest(String parameterName, String parameterEmail, String parameterPassword) {
        ModalDialogPage page = new ModalDialogPage(driver);
        page.createNewUserButton.click();
        driver.switchTo()
                .activeElement();
        page.name.clear();
        page.email.clear();
        page.password.clear();
        page.name.sendKeys(parameterName);
        page.email.sendKeys(parameterEmail);
        page.password.sendKeys(parameterPassword);
        page.dialogButtons.get(1)
                .click();
        Assert.assertEquals(parameterName, page.createdUserDetails.get(0)
                .getText());
        Assert.assertEquals(parameterEmail, page.createdUserDetails.get(1)
                .getText());
        Assert.assertEquals(parameterPassword, page.createdUserDetails.get(2)
                .getText());
    }
}
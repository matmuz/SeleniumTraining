package tests.widgets;

import base.BaseTest;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import pages.widgets.ModalDialogPage;

import static pages.widgets.ModalDialogPage.MODAL_DIALOG_PAGE;
import static utils.DataConstants.*;

public class ModalDialogTest extends BaseTest {

    @BeforeMethod
    @DataProvider(name = "data-provider")
    public Object[][] createProvider() {
        return new Object[][]{{getFullName(), EMAIL, PASSWORD}};
    }

    @BeforeMethod
    public void getPage() {
        driver.get(MODAL_DIALOG_PAGE);
    }

    @Test(dataProvider = "data-provider")
    public void modalDialogTest(String parameterName, String parameterEmail, String parameterPassword) {
        ModalDialogPage modalDialogPage = new ModalDialogPage(driver);

        modalDialogPage.createNewUserButton.click();
        driver.switchTo().activeElement();
        modalDialogPage.name.clear();
        modalDialogPage.email.clear();
        modalDialogPage.password.clear();
        modalDialogPage.name.sendKeys(parameterName);
        modalDialogPage.email.sendKeys(parameterEmail);
        modalDialogPage.password.sendKeys(parameterPassword);
        modalDialogPage.dialogButtons.get(1).click();
        Assert.assertEquals(parameterName, modalDialogPage.createdUserDetails.get(0).getText());
        Assert.assertEquals(parameterEmail, modalDialogPage.createdUserDetails.get(1).getText());
        Assert.assertEquals(parameterPassword, modalDialogPage.createdUserDetails.get(2).getText());
    }
}
package pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class ModalDialogPage extends BasePage {

    public static final String MODAL_DIALOG_PAGE = "https://seleniumui.moderntester.pl/modal-dialog.php";

    public ModalDialogPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#create-user")
    public WebElement createNewUserButton;

    @FindBy(css = "tr:nth-child(2) td")
    public List<WebElement> createdUserDetails;

    @FindBy(css = "#name")
    public WebElement name;

    @FindBy(css = "#email")
    public WebElement email;

    @FindBy(css = "#password")
    public WebElement password;

    @FindBy(css = "button[type='button']")
    public List<WebElement> dialogButtons;
}

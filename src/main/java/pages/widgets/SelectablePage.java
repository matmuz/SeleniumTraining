package pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class SelectablePage extends BasePage {

    public static final String SELECTS_PAGE = "https://seleniumui.moderntester.pl/selectmenu.php";

    public SelectablePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "select[id='speed']")
    public WebElement selectSpeed;

    @FindBy(css = "select[id='files']")
    public WebElement selectFiles;

    @FindBy(css = "select[id='number']")
    public WebElement selectNumbers;

    @FindBy(css = "select[id='salutation']")
    public WebElement selectSalutation;
}

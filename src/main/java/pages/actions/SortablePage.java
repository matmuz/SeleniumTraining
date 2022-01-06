package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class SortablePage extends BasePage {

    public static final String SORTABLE_PAGE = "https://seleniumui.moderntester.pl/sortable.php";

    public SortablePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-state-default.ui-sortable-handle")
    public List<WebElement> sortableObjects;
}

package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class DroppablePage extends BasePage {

    public static final String DROPPABLE_PAGE = "https://seleniumui.moderntester.pl/droppable.php";
    public static final String DROPPED_MESSAGE = "Dropped!";

    public DroppablePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#draggable")
    public WebElement elementToDrag;

    @FindBy(css = "#droppable")
    public WebElement elementToDrop;
}
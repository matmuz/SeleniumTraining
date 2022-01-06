package pages.widgets;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class AccordionsPage extends BasePage {

    public static final String ACCORDION_PAGE = "https://seleniumui.moderntester.pl/accordion.php";

    public AccordionsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "h3[role='tab']")
    public List<WebElement> accordionHeaders;

    @FindBy(css = "div[role='tabpanel']")
    public List<WebElement> accordionContents;
}

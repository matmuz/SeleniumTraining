package pages.actions;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

import java.util.List;

public class SelectablePage extends BasePage {

    public SelectablePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = ".ui-widget-content.ui-selectee")
    public List<WebElement> selectableObjects;

    @FindBy(css = "#feedback")
    public WebElement feedback;
}

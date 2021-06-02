package pages.basics;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import pages.base.BasePage;

public class WindowsAndTabsPage extends BasePage {

    public WindowsAndTabsPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "#newBrowserWindow")
    public WebElement newBrowserWindow;

    @FindBy(css = "#newMessageWindow")
    public WebElement newMessageWindow;

    @FindBy(css = "#newBrowserTab")
    public WebElement newBrowserTab;
}
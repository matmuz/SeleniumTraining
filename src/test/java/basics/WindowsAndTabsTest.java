package basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Set;

public class WindowsAndTabsTest extends BaseTest {

    @FindBy(css = "#newBrowserWindow")
    WebElement newBrowserWindow;

    @FindBy(css = "#newMessageWindow")
    WebElement newMessageWindow;

    @FindBy(css = "#newBrowserTab")
    WebElement newBrowserTab;

    @BeforeMethod
    public void initializeElements(){
        driver.get("https://seleniumui.moderntester.pl/windows-tabs.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void windowsAndTabsTest() {
        String firstWindow = driver.getWindowHandle();
        newBrowserWindow.click();
        Set<String> handles = driver.getWindowHandles();
        for (String handle: handles){
            if (!handle.equals(firstWindow)){
                driver.switchTo().window(handle);
            }
        }
        driver.manage().window().maximize();
        tableTest();
        driver.close();
        driver.switchTo().window(firstWindow);
        newMessageWindow.click();
        handles = driver.getWindowHandles();
        for (String handle: handles){
            if (!handle.equals(firstWindow)){
                driver.switchTo().window(handle);
            }
        }
        System.out.println(driver.findElement(By.cssSelector("body")).getText());
        driver.close();
        driver.switchTo().window(firstWindow);
        newBrowserTab.click();
        handles = driver.getWindowHandles();
        for (String handle: handles){
            if (!handle.equals(firstWindow)){
                driver.switchTo().window(handle);
            }
        }
        tableTest();
    }

    public void tableTest() {
        List<WebElement> peaks = driver.findElements(By.cssSelector("tr"));
        clearRedundantElements(peaks);
        for (WebElement peak : peaks) {
            String[] columns = peak
                    .getText()
                    .split(" ");
            for (String column : columns) {
                if ((column.contains("Switzerland"))) {
                    int height = Integer.parseInt(columns[columns.length - 1]);
                    if (height > 4000) {
                        System.out.println(columns[0] + " " + columns[1] + " " + columns[2]);
                    }
                }
            }
        }
    }

    public void clearRedundantElements(List<WebElement> list) {
        list.subList(0, 2)
                .clear();
    }
}
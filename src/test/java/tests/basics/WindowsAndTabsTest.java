package tests.basics;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.basics.WindowsAndTabsPage;

import java.util.List;
import java.util.Set;

import static pages.basics.WindowsAndTabsPage.WINDOWS_AND_TABS;
import static utils.MethodsProvider.clearRedundantElements;

public class WindowsAndTabsTest extends BaseTest {

    private final String countryToCheck = "Switzerland";

    @BeforeMethod
    public void getPage() {
        driver.get(WINDOWS_AND_TABS);
    }

    @Test
    public void windowsAndTabsTest() {
        WindowsAndTabsPage windowsAndTabsPage = new WindowsAndTabsPage(driver);

        String firstWindow = driver.getWindowHandle();
        windowsAndTabsPage.newBrowserWindow.click();
        Set<String> handlesContainer = driver.getWindowHandles();
        for (String handle : handlesContainer) {
            if (!handle.equals(firstWindow)) {
                driver.switchTo().window(handle);
            }
        }
        driver.manage().window().maximize();
        tableTest();
        driver.close();
        driver.switchTo().window(firstWindow);
        windowsAndTabsPage.newMessageWindow.click();
        handlesContainer = driver.getWindowHandles();
        for (String handle : handlesContainer) {
            if (!handle.equals(firstWindow)) {
                driver.switchTo().window(handle);
            }
        }
        System.out.println(windowsAndTabsPage.body.getText());
        driver.close();
        driver.switchTo().window(firstWindow);
        windowsAndTabsPage.newBrowserTab.click();
        handlesContainer = driver.getWindowHandles();
        for (String handle : handlesContainer) {
            if (!handle.equals(firstWindow)) {
                driver.switchTo().window(handle);
            }
        }
        tableTest();
    }

    public void tableTest() {
        List<WebElement> peaks = driver.findElements(By.cssSelector("tr"));
        clearRedundantElements(peaks);
        for (WebElement peak : peaks) {
            String[] columns = peak.getText().split(" ");
            for (String column : columns) {
                if ((column.contains(countryToCheck))) {
                    int height = Integer.parseInt(columns[columns.length - 1]);
                    if (height > 4000) {
                        System.out.println(columns[0] + ". " + columns[1] + " - " + columns[2]);
                    }
                }
            }
        }
    }
}
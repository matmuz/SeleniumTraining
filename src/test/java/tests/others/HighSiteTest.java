package tests.others;

import base.BaseTest;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import waiter.Waiter;

import java.io.File;
import java.io.IOException;

public class HighSiteTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/high-site.php");
    }

    @Test
    public void highSiteTest() {
        boolean done = false;
        while (!done) {
            ((JavascriptExecutor) driver).executeScript("window.scrollBy(0,50)");
            try {
                if (Waiter.wait(driver)
                          .until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#scroll-button"))))
                          .isDisplayed()) {
                    driver.findElement(By.cssSelector("#scroll-button")).click();
                    done = true;
                }
            } catch (NoSuchElementException ignored) {
            }
        }
    }

    @AfterMethod
    public void takeScreenshot() throws IOException {
        TakesScreenshot screenshot = ((TakesScreenshot) driver);
        File file = screenshot.getScreenshotAs(OutputType.FILE);
        File destination = new File("src/test/java/tests/others/test.png");
        FileUtils.copyFile(file, destination);
    }
}
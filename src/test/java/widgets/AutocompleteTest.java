package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.Random;

public class AutocompleteTest extends BaseTest {

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/autocomplete.php");
    }

    @Test
    public void autocompleteTest() throws IOException, UnsupportedFlavorException {
        WebElement search = driver.findElement(By.cssSelector("#search"));
        search.sendKeys("a");
        List<WebElement> options = driver.findElements(By.cssSelector(".ui-menu-item"));
        for (WebElement option : options) {
            System.out.println(option.getText());
        }
        Random random = new Random();
        int option = random.nextInt(options.size());
        String stringOption = options.get(option).getText();
        options.get(option).click();
        search.sendKeys(Keys.CONTROL, "a");
        search.sendKeys(Keys.CONTROL, "c");
        Clipboard clipboard = Toolkit.getDefaultToolkit()
                .getSystemClipboard();
        DataFlavor dataFlavor = DataFlavor.stringFlavor;
        String optionsText = (String) clipboard.getData(dataFlavor);
        Assert.assertEquals(optionsText, stringOption);
    }
}
package widgets;

import base.BaseTest;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Random;

public class SelectableTest extends BaseTest {

    @FindBy(css = "select[id='speed']")
    WebElement selectSpeed;

    @FindBy(css = "select[id='files']")
    WebElement selectFiles;

    @FindBy(css = "select[id='number']")
    WebElement selectNumbers;

    @FindBy(css = "select[id='salutation']")
    WebElement selectSalutation;

    @BeforeMethod
    public void initializeElements(){
        driver.get("https://seleniumui.moderntester.pl/selectmenu.php");
        PageFactory.initElements(driver, this);
    }

    @Test
    public void selectMenuTest() {
        Random random = new Random();
        Select select = new Select(selectSpeed);
        select.selectByIndex(random.nextInt(select.getOptions().size()));
        selectFiles.click();
        select = new Select(selectFiles);
        select.selectByVisibleText("Some unknown file");
        selectNumbers.click();
        select = new Select(selectNumbers);
        select.selectByIndex(5);
        selectSalutation.click();
        select = new Select(selectSalutation);
        select.selectByIndex(random.nextInt(select.getOptions().size()));
    }
}

package widgets;

import base.BaseTest;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class SliderTest extends BaseTest {

    private final int[] conditions = {50, 80, 80, 20, 0};
    private WebElement slider;

    @BeforeMethod
    public void getPage() {
        driver.get("https://seleniumui.moderntester.pl/slider.php");
    }

    @Test
    public void sliderTest() {
        slider = driver.findElement(By.cssSelector("#custom-handle"));
        for (int i = 0; i < conditions.length; i++) {
            if (Integer.parseInt(slider.getText()) == conditions[i]) {
                checkCondition(i);
            }
            while (Integer.parseInt(slider.getText()) != conditions[i]) {
                if (Integer.parseInt(slider.getText()) < conditions[i]) {
                    goRight();
                } else {
                    goLeft();
                }
                if (Integer.parseInt(slider.getText()) == conditions[i]) {
                    checkCondition(i);
                }
            }
        }
    }

    public void goLeft() {
        slider.sendKeys(Keys.ARROW_LEFT);
    }

    public void goRight() {
        slider.sendKeys(Keys.ARROW_RIGHT);
    }

    public void checkCondition(int index) {
        System.out.println("Comparing " + slider.getText() + " to " + conditions[index]);
        Assert.assertEquals(Integer.parseInt(slider.getText()), conditions[index]);
    }
}

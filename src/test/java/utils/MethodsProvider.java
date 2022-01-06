package utils;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.Random;

public class MethodsProvider {

    public static final Random RANDOM = new Random();

    public static void chooseRandom(List<WebElement> radios, Random random) {
        radios.get(random.nextInt(radios.size())).click();
    }

    public static int chooseRandom(int size, Random random) {
        return random.nextInt(size);
    }

    public static Select selectOptions(WebElement selectElement) {
        return new Select(selectElement);
    }

    public static void clearRedundantElements(List<WebElement> list) {
        list.subList(0, 2).clear();
    }

    public static int getNumberFromWebElement(List<WebElement> webElements, int index) {
        String[] split = webElements.get(index).getText().split(" ");
        return Integer.parseInt(split[1]);
    }
}
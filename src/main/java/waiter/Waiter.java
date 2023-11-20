package waiter;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class Waiter {

    private static WebDriverWait wait;

    private Waiter() {
    }

    public static WebDriverWait wait(WebDriver driver) {
        if (wait == null) {
            wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        }
        return wait;
    }
}

package driver;

import static driver.Browsers.CHROME;

public class DriverManagerFactory {

    private static ChromeDriverManager driverManager;

    public static ChromeDriverManager getManager(Browsers browser) {
        if (driverManager == null) {
            if (browser.equals(CHROME)) {
                driverManager = new ChromeDriverManager();
                return driverManager;
            }
        }
        return driverManager;
    }
}
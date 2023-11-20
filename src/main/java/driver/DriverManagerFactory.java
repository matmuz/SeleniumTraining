package driver;

public class DriverManagerFactory {

    private static ChromeDriverManager driverManager;

    public static ChromeDriverManager getManager() {
        if (driverManager == null) {
            driverManager = new ChromeDriverManager();
            return driverManager;
        }
        return driverManager;
    }
}
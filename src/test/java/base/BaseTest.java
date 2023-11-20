package base;

import driver.ChromeDriverManager;
import driver.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class BaseTest {

    private ChromeDriverManager driverManager;
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driverManager = DriverManagerFactory.getManager();
        driver = driverManager.getDriver();
        driver.get("https://the-internet.herokuapp.com/challenging_dom");
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver(driver);
    }
}
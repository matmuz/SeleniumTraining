package base;

import driver.Browsers;
import driver.ChromeDriverManager;
import driver.DriverManagerFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import static pages.base.BasePage.MAIN_PAGE;

public class BaseTest {

    private ChromeDriverManager driverManager;
    protected WebDriver driver;

    @BeforeMethod
    public void setUp() {
        driverManager = DriverManagerFactory.getManager(Browsers.CHROME);
        driver = driverManager.getDriver();
        driver.get(MAIN_PAGE);
    }

    @AfterMethod
    public void tearDown() {
        driverManager.quitDriver(driver);
    }
}
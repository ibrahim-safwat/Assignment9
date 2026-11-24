package driverfactory;

import assertions.hard.HardAssertions;
import actions.BrowserActions;
import actions.ElementActions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import utilities.PropertiesManager;

import java.io.File;

import static utilities.PropertiesManager.frameworkConfig;

public class Driver {

    private ThreadLocal<WebDriver> driver;

    public Driver(String browserName) {
        driver = new ThreadLocal<>();
        driver.set(getDriverFactory(browserName).StartDriver());
        System.out.println("Driver " + browserName + " is created");
        if (!frameworkConfig.getProperty("BaseUrl").isEmpty()) {
            driver.get().navigate().to(frameworkConfig.getProperty("BaseUrl"));
        }
    }

    public Driver() {
        if (frameworkConfig == null) {
            PropertiesManager.initializeProperties();
        }
        String browserType = frameworkConfig.getProperty("BrowserType");

        driver = new ThreadLocal<>();
        driver.set(getDriverFactory(browserType).StartDriver());
        System.out.println("Starting the execution via " + browserType + " driver");
        if (!frameworkConfig.getProperty("BaseUrl").isEmpty()) {
            driver.get().navigate().to(frameworkConfig.getProperty("BaseUrl"));
        }
    }


    private DriverAbstract getDriverFactory(String browserType) {
        switch (browserType.toLowerCase()) {
            case "chrome":
                return new ChromeDriverFactory();
            case "firefox":
                return new FirefoxDriverFactory();
            case "edge":
                return new EdgeDriverFactory();
            default:
                throw new IllegalArgumentException("Browser type not supported" + browserType);
        }
    }

    public WebDriver getDriver() {
        return driver.get();
    }

    public void quitDriver() {
        driver.get().quit();
    }

    public ElementActions element() {
        return new ElementActions(driver.get());
    }

    public BrowserActions browser() {
        return new BrowserActions(driver.get());
    }

    public HardAssertions hardassertions() {
        return new HardAssertions(driver.get());
    }
}

package my.test.calculator.driver;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;


public class DriverSingleton {
    private WebDriver driver;
    private static DriverSingleton instance;

    private DriverSingleton() {
        driver = new ChromeDriver();
    }

    public synchronized static DriverSingleton getInstance() {
        if (instance == null) {
            instance = new DriverSingleton();
        }
        return instance;
    }

    public WebDriver getDriver() {
        return driver;
    }
}

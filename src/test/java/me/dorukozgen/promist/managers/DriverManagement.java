package me.dorukozgen.promist.managers;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class DriverManagement {

    private ChromeDriver driver;
    private WebDriverWait wait;
    public static DriverManagement instance;

    public static DriverManagement getInstance() {
        if (instance == null) {
            synchronized (DriverManagement.class) {
                instance = new DriverManagement();
            }
        }
        return instance;
    }

    public ChromeDriver getDriver() {
        return driver;
    }

    public WebDriverWait getWait() {
        return wait;
    }

    public void setDriver() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-ssl-errors=yes");
        options.addArguments("--ignore-certificate-errors");

        ChromeDriver newDriver = new ChromeDriver(options);
        newDriver.manage().window().maximize();

        this.driver = newDriver;
    }

    public void setWait() {
        WebDriverWait newWait = new WebDriverWait(driver, Duration.ofSeconds(10));
        this.wait = newWait;
    }

    public void destroy() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
        if (wait != null) {
            wait = null;
        }
        instance = null;
    }

}

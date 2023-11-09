import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.nio.file.Paths;
import java.time.Duration;
import java.time.temporal.ChronoUnit;

public class Test {
    public static void main(String[] args) throws InterruptedException {
        System.setProperty(
                "webdriver.chrome.driver",
                Paths.get("").resolve("src/test/resources/chromedriver.exe").toAbsolutePath().toString()
        );

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--start-maximized");
        options.addArguments("--ignore-ssl-errors=yes");

        ChromeDriver driver = new ChromeDriver(options);
        driver.get("https://www.hepsiburada.com/");

        WebDriverWait wait = new WebDriverWait(driver, Duration.of(10, ChronoUnit.SECONDS));

        wait.until(webDriver -> ((JavascriptExecutor) webDriver).executeScript("return document.readyState").equals("complete"));

        WebElement el = wait.until(d -> d.findElement(By.xpath("/html/body/div[1]/div/div/div[4]/div[5]/div/div/div/div[1]/div[3]/div[2]/div/div/div/div/div/div/div[1]/div[2]")));

        el.click();
    }
}

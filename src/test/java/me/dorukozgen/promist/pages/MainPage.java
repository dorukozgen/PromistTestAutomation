package me.dorukozgen.promist.pages;

import me.dorukozgen.promist.utils.Util;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.interactions.PointerInput;
import org.openqa.selenium.interactions.Sequence;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.text.MessageFormat;
import java.time.Duration;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class MainPage {

    private ChromeDriver driver;
    private WebDriverWait wait;

    @FindBy(xpath = "/html/body/div[3]/form/div[2]/div/div[3]")
    WebElement navBar;

    @FindBy(xpath = "/html/body/div[3]/form/div[4]/div[1]/ul[2]")
    WebElement mediasMenu;

    String navLinkBy = "/html/body/div[3]/form/div[2]/div/div[3]/ul/li[{0}]/a";
    String mediaLinkBy = "/html/body/div[3]/form/div[4]/div[1]/ul[2]/li[{0}]/a";

    public MainPage(ChromeDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;

        PageFactory.initElements(driver, this);
    }

    public void scrollDown() {
        JavascriptExecutor js = driver;
        js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
    }

    public void checkNavbar() {
        wait.until(ExpectedConditions.visibilityOf(navBar));
    }

    public void checkMediasBar() {
        wait.until(ExpectedConditions.visibilityOf(mediasMenu));
    }

    public void goToMainPage(String url) {
        driver.get(url);

        wait.until((ExpectedCondition<Boolean>) wd ->
                ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

        Util.wait(1);
    }

    public void clickNavLinks() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                MessageFormat.format(navLinkBy, "1")
                        )
                )
        );

        Util.wait(1);

        String mainUrl = driver.getCurrentUrl();

        // for each with index
        for (int i = 0; i < 7; i++) {
            WebElement navLink = driver.findElement(By.xpath(MessageFormat.format(navLinkBy, (i + 1))));
            String href = navLink.getAttribute("href");

            navLink.click();

            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

            wait.until(ExpectedConditions.urlToBe(href));

            Util.wait(1);

            driver.navigate().back();

            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

            wait.until(ExpectedConditions.urlToBe(mainUrl));

            Util.wait(1);
        }

        Util.wait(1);
    }

    public void clickMediaLinks() {
        wait.until(
                ExpectedConditions.elementToBeClickable(
                        By.xpath(
                                MessageFormat.format(mediaLinkBy, "1")
                        )
                )
        );

        Util.wait(1);

        String mainUrl = driver.getCurrentUrl();

        // for each with index
        for (int i = 0; i < 7; i++) {
            WebElement mediaLink = driver.findElement(By.xpath(MessageFormat.format(mediaLinkBy, (i + 1))));
            String href = mediaLink.getAttribute("href");
            String mediaName = mediaLink.getText().toLowerCase();

            mediaLink.click();

            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

            // url contains media name
            wait.until(ExpectedConditions.urlContains(mediaName));

            Util.wait(1);

            driver.navigate().back();

            wait.until((ExpectedCondition<Boolean>) wd ->
                    ((JavascriptExecutor) wd).executeScript("return document.readyState").equals("complete"));

            wait.until(ExpectedConditions.urlToBe(mainUrl));

            scrollDown();

            Util.wait(1);
        }

        Util.wait(1);
    }

}

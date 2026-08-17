package labs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task3 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://anarabbas.com/labs/auto-lab.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            menyuAc(wait, "iFrame");

            wait.until(frameToBeAvailableAndSwitchToIt(By.id("demoFrame")));
            wait.until(visibilityOfElementLocated(By.id("frameInput"))).sendKeys("Frame daxilində mesaj");
            wait.until(elementToBeClickable(By.id("frameBtn"))).click();
            System.out.println("1) Xarici frame: input + düymə OK");

            wait.until(frameToBeAvailableAndSwitchToIt(By.id("innerFrame")));
            wait.until(elementToBeClickable(By.tagName("button"))).click();
            System.out.println("2) Nested frame: düymə OK");

            driver.switchTo().defaultContent();
            WebElement mainHeading = wait.until(visibilityOfElementLocated(By.tagName("h1")));
            System.out.println("3) Əsas səhifə: " + mainHeading.getText());

        } finally {
            driver.quit();
        }
    }

    private static void menyuAc(WebDriverWait wait, String ad) {
        wait.until(elementToBeClickable(By.xpath(
                "//button[contains(.,'" + ad + "')] | //a[contains(.,'" + ad + "')] | //li[contains(.,'" + ad + "')]"
        ))).click();
    }
}
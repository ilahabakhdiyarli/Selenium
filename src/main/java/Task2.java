package labs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task2 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://anarabbas.com/labs/auto-lab.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            menyuAc(wait, "Alerts & Modal");

            // 1) alert() — accept
            wait.until(elementToBeClickable(By.xpath("//button[normalize-space(.)='alert()']"))).click();
            Alert a1 = wait.until(alertIsPresent());
            System.out.println("alert()   : " + a1.getText());
            a1.accept();

            // 2) confirm() — dismiss
            wait.until(elementToBeClickable(By.xpath("//button[normalize-space(.)='confirm()']"))).click();
            Alert a2 = wait.until(alertIsPresent());
            System.out.println("confirm() : " + a2.getText());
            a2.dismiss();

            // 3) prompt() — ad yaz + accept
            wait.until(elementToBeClickable(By.xpath("//button[normalize-space(.)='prompt()']"))).click();
            Alert a3 = wait.until(alertIsPresent());
            System.out.println("prompt()  : " + a3.getText());
            a3.sendKeys("Ilaha");
            a3.accept();


            wait.until(elementToBeClickable(By.xpath("//button[contains(.,'Modal Aç')]"))).click();
            WebElement modalInput = wait.until(visibilityOfElementLocated(By.id("modalInput")));
            modalInput.sendKeys("Bu adi DOM elementidir — alert deyil");
            wait.until(elementToBeClickable(By.xpath("//button[contains(.,'Təsdiqlə')]"))).click();

            System.out.println("Modal SÜBUT: switchTo() olmadan findElement işlədi → əsl alert deyil.");

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
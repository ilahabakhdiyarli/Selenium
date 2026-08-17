package labs;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class Task1 {

    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();

        try {
            driver.manage().window().maximize();
            driver.get("https://anarabbas.com/labs/auto-lab.html");

            WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

            menyuAc(wait, "Dynamic Elements");

            WebElement gecikBtn = wait.until(
                    elementToBeClickable(By.xpath("//button[contains(.,'Göstər')]")));

            WebDriverWait olcuWait = new WebDriverWait(driver, Duration.ofSeconds(10));
            olcuWait.pollingEvery(Duration.ofMillis(50));

            long baslangic = System.currentTimeMillis();
            gecikBtn.click();

            WebElement gecElement = olcuWait.until(
                    visibilityOfElementLocated(
                            By.xpath("//*[contains(normalize-space(.),'2 saniyə gec göründüm')]")));

            long muddet = System.currentTimeMillis() - baslangic;

            System.out.println("Element mətni : " + gecElement.getText());
            System.out.println("Görünmə vaxtı : " + muddet + " ms");

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
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

public class Buttons {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String URL = "https://anarabbas.com/labs/auto-lab";
        driver.get(URL);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement btn = driver.findElement(By.cssSelector("button[data-p='btn']"));
        btn.click();

        WebElement clickBtn = driver.findElement(By.id("clickBtn"));
        js.executeScript("arguments[0].scrollIntoView({block:'center'})", clickBtn);
        clickBtn.click();

        Actions actions = new Actions(driver);
        WebElement doubleBtn = driver.findElement(By.id("dblBtn"));
        actions.doubleClick(doubleBtn).perform();

        WebElement rightBtn = driver.findElement(By.id("rcBtn"));
        actions.contextClick(rightBtn).perform();

        WebElement disabled = driver.findElement(By.cssSelector("button.pg-btn[disabled]"));
        System.out.println("Disabled aktivdir? " + disabled.isEnabled());

        driver.quit();
    }
}
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class TextBox {
    public static void main(String[] args) {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();

        String URL = "https://anarabbas.com/labs/auto-lab";
        driver.get(URL);

        JavascriptExecutor js = (JavascriptExecutor) driver;

        WebElement name = driver.findElement(By.id("fullName"));
        js.executeScript("arguments[0].scrollIntoView({block:'center'})", name);
        name.clear();
        name.sendKeys("Ilaha");

        WebElement email = driver.findElement(By.id("userEmail"));
        email.clear();
        email.sendKeys("user@test.az");

        WebElement password = driver.findElement(By.id("passField"));
        password.clear();
        password.sendKeys("1234");

        driver.quit();
    }
}
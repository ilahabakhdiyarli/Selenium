package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;
import pages.SlidersPage;

import java.time.Duration;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SliderTests {

    private WebDriver driver;
    private SlidersPage sliders;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://anarabbas.com/labs/auto-lab.html");

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(elementToBeClickable(By.xpath(
                "//button[contains(.,'Sliders')] | //a[contains(.,'Sliders')] | //li[contains(.,'Sliders')]"
        ))).click();

        sliders = new SlidersPage(driver);
    }

    @AfterMethod
    public void tearDown() {
        if (driver != null) driver.quit();
    }

    @Test
    public void defaultValues() {                     // Test 1
        Assert.assertEquals(sliders.getTempLabel(), "50°C", "Temperature default səhvdir");
        Assert.assertEquals(sliders.getVolumeLabel(), "75%", "Volume default səhvdir");
    }

    @Test
    public void changeTemperature() {                 // Test 2
        sliders.setTemp(25);
        Assert.assertEquals(sliders.getTempLabel(), "25°C");
    }

    @Test
    public void changeVolume() {                       // Test 3
        sliders.setVolume(40);
        Assert.assertEquals(sliders.getVolumeLabel(), "40%");
    }

    @Test
    public void temperatureBoundaries() {              // Test 4
        sliders.tempToMin();
        int min = sliders.getTempNumber();
        Assert.assertTrue(min >= 0 && min <= 100, "Min sərhəddən kənardır: " + min);

        sliders.tempToMax();
        int max = sliders.getTempNumber();
        Assert.assertTrue(max >= 0 && max <= 100, "Max sərhəddən kənardır: " + max);
    }

    @Test
    public void volumeBoundaries() {                   // Test 5
        sliders.volumeToMin();
        int min = sliders.getVolumeNumber();
        Assert.assertTrue(min >= 0 && min <= 100, "Min sərhəddən kənardır: " + min);

        sliders.volumeToMax();
        int max = sliders.getVolumeNumber();
        Assert.assertTrue(max >= 0 && max <= 100, "Max sərhəddən kənardır: " + max);
    }
}
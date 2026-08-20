package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class SlidersPage extends BasePage {


    private final By tempSlider = By.id("tempSlider");
    private final By volSlider  = By.id("volSlider");

    private final By tempLabel = By.id("tempVal");
    private final By volLabel  = By.id("volVal");

    public SlidersPage(WebDriver driver) {
        super(driver);
    }

    public String getTempLabel() {
        return wait.until(visibilityOfElementLocated(tempLabel)).getText().trim();
    }

    public String getVolumeLabel() {
        return wait.until(visibilityOfElementLocated(volLabel)).getText().trim();
    }

    public int getTempNumber()   { return parseNumber(getTempLabel()); }
    public int getVolumeNumber() { return parseNumber(getVolumeLabel()); }

    public void setTemp(int hedef)   { moveSlider(tempSlider, hedef); }
    public void setVolume(int hedef) { moveSlider(volSlider, hedef); }

    public void tempToMin()   { wait.until(elementToBeClickable(tempSlider)).sendKeys(Keys.HOME); }
    public void tempToMax()   { wait.until(elementToBeClickable(tempSlider)).sendKeys(Keys.END); }
    public void volumeToMin() { wait.until(elementToBeClickable(volSlider)).sendKeys(Keys.HOME); }
    public void volumeToMax() { wait.until(elementToBeClickable(volSlider)).sendKeys(Keys.END); }

    private void moveSlider(By slider, int hedef) {
        WebElement s = wait.until(elementToBeClickable(slider));
        s.sendKeys(Keys.HOME);                    // əvvəl minimuma (0)
        for (int i = 0; i < hedef; i++) {
            s.sendKeys(Keys.ARROW_RIGHT);         // sonra hədəf qədər +1 sağa
        }
    }

    private int parseNumber(String text) {
        return Integer.parseInt(text.replaceAll("[^0-9-]", ""));
    }
}
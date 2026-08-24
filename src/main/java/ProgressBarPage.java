import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProgressBarPage extends BasePage {

    private final By progressMenuItem = By.cssSelector("button[data-p='progress']");
    private final By startButton = By.id("pgStart");
    private final By stopButton = By.id("pgStop");
    private final By resetButton = By.id("pgReset");
    private final By progressBar = By.id("pgBar");

    public ProgressBarPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://anarabbas.com/labs/auto-lab");
        waitForClickable(progressMenuItem).click();
        scrollToElement(progressBar);
    }

    public void clickStart() {
        waitForClickable(startButton).click();
    }

    public void clickStop() {
        waitForClickable(stopButton).click();
    }

    public void clickReset() {
        waitForClickable(resetButton).click();
    }

    public int getProgressValue() {
        String value = waitForVisible(progressBar).getAttribute("aria-valuenow");
        return Integer.parseInt(value);
    }

    public void waitUntilValueReaches(int target) {
        wait.until(d ->
                Integer.parseInt(
                        d.findElement(progressBar).getAttribute("aria-valuenow")
                ) >= target);
    }
}
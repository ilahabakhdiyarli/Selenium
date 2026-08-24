import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class DatePickerPage extends BasePage {

    private final By dateMenuItem = By.cssSelector("button[data-p='date']");
    private final By dateInput = By.id("datePicker");
    private final By dateTimeInput = By.id("dateTimePicker");

    public DatePickerPage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://anarabbas.com/labs/auto-lab");
        waitForClickable(dateMenuItem).click();
        scrollToElement(dateInput);
    }

    public void setDate(String date) {
        WebElement input = waitForVisible(dateInput);
        input.sendKeys(date);
    }

    public void setDateTime(String dateTime) {
        WebElement input = waitForVisible(dateTimeInput);
        input.sendKeys(dateTime);
    }

    public String getDateValue() {
        return waitForVisible(dateInput).getAttribute("value");
    }

    public String getDateTimeValue() {
        return waitForVisible(dateTimeInput).getAttribute("value");
    }
}
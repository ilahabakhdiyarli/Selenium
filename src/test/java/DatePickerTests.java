import org.openqa.selenium.Keys;
import org.testng.Assert;
import org.testng.annotations.Test;
import java.lang.InterruptedException;

public class DatePickerTests extends BaseTests {

    @Test(description = "Tarix sahəsinə tarix daxil edilir və yoxlanılır")
    public void verifyDateSelection() {
        DatePickerPage page = new DatePickerPage(driver);
        page.open();

        page.setDate("24-08-2026");
        Assert.assertEquals(page.getDateValue(), "2026-08-24",
                "Tarix düzgün əks olunmadı!");
    }

    @Test(description = "Tarix+saat sahəsinə dəyər daxil edilir və yoxlanılır")
    public void verifyDateTimeSelection() {
        DatePickerPage page = new DatePickerPage(driver);
        page.open();

        page.setDateTime("24-08-2026" + Keys.TAB + "14:30");
        Assert.assertEquals(page.getDateTimeValue(), "2026-08-24T14:30",
                "Tarix və saat düzgün əks olunmadı!");
    }
}
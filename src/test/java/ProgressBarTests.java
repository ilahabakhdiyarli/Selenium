import org.testng.Assert;
import org.testng.annotations.Test;

public class ProgressBarTests extends BaseTests {

    @Test(description = "Səhifə yükləndikdə progress 0 olmalıdır")
    public void verifyInitialState() {
        ProgressBarPage page = new ProgressBarPage(driver);
        page.open();
        Assert.assertEquals(page.getProgressValue(), 0,
                "Başlanğıc dəyər 0 deyil!");
    }

    @Test(description = "Start basıldıqdan sonra dəyər artmalıdır")
    public void verifyProgressIncreases() {
        ProgressBarPage page = new ProgressBarPage(driver);
        page.open();
        page.clickStart();
        page.waitUntilValueReaches(30);
        Assert.assertTrue(page.getProgressValue() >= 30,
                "Dəyər artmadı!");
    }

    @Test(description = "Stop basıldıqda dəyər dəyişməməlidir")
    public void verifyProgressStops() throws InterruptedException {
        ProgressBarPage page = new ProgressBarPage(driver);
        page.open();
        page.clickStart();
        page.waitUntilValueReaches(20);
        page.clickStop();
        int valueAtStop = page.getProgressValue();
        Thread.sleep(2000);
        int valueAfterWait = page.getProgressValue();
        Assert.assertEquals(valueAfterWait, valueAtStop,
                "Dayandırmadan sonra dəyər dəyişdi!");
    }

    @Test(description = "Reset basıldıqda dəyər 0-a qayıtmalıdır")
    public void verifyProgressResets() {
        ProgressBarPage page = new ProgressBarPage(driver);
        page.open();
        page.clickStart();
        page.waitUntilValueReaches(20);
        page.clickReset();
        Assert.assertEquals(page.getProgressValue(), 0,
                "Reset-dən sonra dəyər 0 deyil!");
    }
}
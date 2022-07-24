package assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class VerifyDropdowns {
    WebDriver driver;

    @BeforeMethod

    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "disable-popup-blocking");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);





        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void WednesdayVerification() throws InterruptedException {

        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        Thread.sleep(2000);

        Select days = new Select(driver.findElement(By.id("select-demo")));
        days.selectByIndex(4);

        String actualResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/p[2]")).getText();
        String expectedResult = "Day selected :- Wednesday";

        Assert.assertEquals(expectedResult, actualResult);

    }

    @Test
    public void SaturdayVerification() throws InterruptedException {

        driver.get("https://demo.seleniumeasy.com/basic-select-dropdown-demo.html");
        Thread.sleep(2000);

        Select days = new Select(driver.findElement(By.id("select-demo")));
        days.selectByIndex(7);

        String actualResult = driver.findElement(By.xpath("/html/body/div[2]/div/div[2]/div[1]/div[2]/p[2]")).getText();
        String expectedResult = "Day selected :- Saturday";

        Assert.assertEquals(expectedResult, actualResult);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

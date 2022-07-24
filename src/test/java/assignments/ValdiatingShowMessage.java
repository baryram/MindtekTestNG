package assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.omg.PortableServer.THREAD_POLICY_ID;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class ValdiatingShowMessage {
    WebDriver driver;

    @BeforeMethod

    public void setUp() {

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void SingleInput() throws InterruptedException {

        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"at-cv-lightbox-button-holder\"]/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("user-message")).sendKeys("Hello Mindtek!");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"get-input\"]/button")).click();
        Thread.sleep(2000);
        String expectedStr = "Hello Mindtek!";
        String actualStr = driver.findElement(By.id("display")).getText();

        Assert.assertEquals(actualStr,expectedStr);
    }
    @Test
    public void TwoInputSumVerification() throws InterruptedException {

        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"at-cv-lightbox-button-holder\"]/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sum1")).sendKeys("5");
        Thread.sleep(2000);
        driver.findElement(By.id("sum2")).sendKeys("6");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
        Thread.sleep(2000);

        String expectedStr = "11";
        String actualStr = driver.findElement(By.id("displayvalue")).getText();

        Assert.assertEquals(actualStr,expectedStr);

    }
    @Test
    public void TwoInputTextVerification() throws InterruptedException {

        driver.get("https://demo.seleniumeasy.com/basic-first-form-demo.html");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"at-cv-lightbox-button-holder\"]/a[2]")).click();
        Thread.sleep(1000);
        driver.findElement(By.id("sum1")).sendKeys("Na");
        Thread.sleep(2000);
        driver.findElement(By.id("sum2")).sendKeys("N");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"gettotal\"]/button")).click();
        Thread.sleep(2000);

        String expectedStr = "NaN";
        String actualStr = driver.findElement(By.id("displayvalue")).getText();

        Assert.assertEquals(actualStr,expectedStr);

    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class HeadlessBrowser {

    WebDriver driver;

    @BeforeMethod

    public void setUp(){

        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless", "--disable-popup-blocking", "--incognito");

        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/");
    }
    @Test
    public void amazonHOmePageVerification(){

        String expectedResult = "Amazon.com. Spend less. Smile more.";
        Assert.assertEquals(driver.getTitle(), expectedResult);
    }

    @AfterMethod
    public void tearDown(){

        driver.quit();
    }





}

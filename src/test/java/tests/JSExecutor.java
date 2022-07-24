package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class JSExecutor {

    WebDriver driver;
    String item = "web camera";

    @BeforeMethod

    public void setUp(){

    WebDriverManager.chromedriver().setup();
//
//        ChromeOptions options = new ChromeOptions();
//        options.addArguments("--headless", "disable-popup-blocking");
//
      driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.get("https://www.amazon.com/");
    }
    @Test
    public void searchTest() {
        driver.get("https://www.amazon.com/");
        String item = "web camera";
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item+ Keys.ENTER);

        String searchItem = driver.findElement(By.xpath("//span[@class='a-color-state a-text-bold']")).getText();

        Assert.assertEquals(searchItem.replace("\"",""),item);
    }
    @Test
    public void outOfStockChekboxTest(){
        driver.get("https://www.amazon.com/");
        driver.findElement(By.id("twotabsearchtextbox")).sendKeys(item+ Keys.ENTER);
        JavascriptExecutor jse = ((JavascriptExecutor)driver);
      jse.executeScript("window.scrollBy(0,1500)");



        driver.findElement(By.xpath("//li[@id='p_n_availability/2661601011']//i")).click();
        jse.executeScript("window.scrollBy(0, document.body.scrollHeight)");

    }
    @Test

    public void jseTest(){

        JavascriptExecutor jse = ((JavascriptExecutor)driver);
        jse.executeScript("window.location='https://www.amazon.com/'");
        WebElement customerServiceTab = driver.findElement(By.xpath("//a[contains(text(), 'Customer Service')]"));
        jse.executeScript("arguments[0].click()", customerServiceTab);

        Assert.assertEquals(driver.getTitle(), "Amazon Customer Service Support - Amazon.com");


    }

    @AfterMethod
    public void tearDown(){

        driver.quit();
    }
}

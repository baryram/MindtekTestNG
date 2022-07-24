package assignments;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

public class Weatherbase {
    WebDriver driver;
    String url = "http://www.weatherbase.com/";

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();

        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-popup-blocking", "--headless");
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver(options);





        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        driver.manage().window().maximize();
    }

    @Test
    public void CheckZipCode() throws InterruptedException {
        String actualResult = "";
        String expectedResult = "Chicago";
        driver.get(url);
        Thread.sleep(2000);
        driver.findElement(By.id("query")).sendKeys("60656");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"header-block\"]/table/tbody/tr[2]/td[4]/form/button")).click();
        List<WebElement> cityList = driver.findElements(By.xpath("//*[@id=\"cell320left\"]/a"));
        for (WebElement c : cityList) {
            String cityNames = c.getText();
            if (cityNames.toLowerCase().startsWith("chicago")) ;
            actualResult = "Chicago";
        }
        Assert.assertEquals(expectedResult, actualResult, "Not Found");
    }

    @Test
    public void InvalidZipCode() throws InterruptedException {
        driver.get(url);
        Thread.sleep(2000);
        driver.findElement(By.id("query")).sendKeys("1234");
        Thread.sleep(2000);
        driver.findElement(By.xpath("//*[@id=\"header-block\"]/table/tbody/tr[2]/td[4]/form/button")).click();
        Thread.sleep(2000);
        String actualResult = driver.findElement(By.xpath("//*[@id=\"left-content\"]/p[1]")).getText().trim();
        String expectedResult = "We're sorry. Your search for 1234 returned no results. Please try the following:";
        Assert.assertEquals(expectedResult, actualResult, "Failed");
    }

    @Test
    public void ValidateAllStates() throws InterruptedException {

        driver.get(url);
        Thread.sleep(3000);

        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"left-170\"]/ul/li[11]/a")).click();
            Thread.sleep(8000);

        } catch (Exception noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
            driver.switchTo().frame(driver.findElement(By.id("ad_iframe")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]/div/span")).click();
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
        }

        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();
        List<WebElement> allStates = driver.findElements(By.xpath("//a[@class=\"redglow\"]"));
        Assert.assertTrue(allStates.size() >= 50);


    }

    @Test
    public void AverageTempLetterC() throws InterruptedException {
        driver.get(url);
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"left-170\"]/ul/li[11]/a")).click();
            Thread.sleep(3000);

        } catch (Exception noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
            driver.switchTo().frame(driver.findElement(By.id("ad_iframe")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]/div/span")).click();
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
        }

        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();
        Thread.sleep(5000);
        driver.findElement(By.xpath("//a[@href=\"/weather/city.php3?c=US&s=CA&statename=California-United-States-of-America\"]")).click();
        Thread.sleep(6000);
        driver.get("http://www.weatherbase.com/weather/weather.php3?s=160547&cityname=San-Jose-California-United-States-of-America");
        //driver.findElement(By.xpath("/html/body/div[3]/div[3]/div/span/div[3]/div[3]/div/div[1]/ul/li[1]"));
        Thread.sleep(2000);

        WebElement buttonC = driver.findElement(By.name("fc"));
        buttonC.click();
        String actualLetterC = driver.findElement(By.xpath("//*[@id=\"left-weather-content\"]/div/table[2]/tbody/tr[2]/td[1]")).getText();
        String expectedLetterC = "C";
        Assert.assertEquals(expectedLetterC, actualLetterC);

    }

    @Test
    public void ValidateFahrenheit() throws InterruptedException {
        driver.get(url);
        try {
            Thread.sleep(3000);
            driver.findElement(By.xpath("//*[@id=\"left-170\"]/ul/li[11]/a")).click();
            Thread.sleep(3000);

        } catch (Exception noSuchElementException) {
            System.out.println(noSuchElementException.getMessage());
            driver.switchTo().frame(driver.findElement(By.id("ad_iframe")));
            Thread.sleep(2000);
            driver.findElement(By.xpath("//*[@id=\"dismiss-button\"]/div/span")).click();
            Thread.sleep(2000);
            driver.switchTo().parentFrame();
        }

        driver.findElement(By.xpath("//a[@href=\"/weather/state.php3?c=US&name=United-States-of-America\"]")).click();
        Thread.sleep(2000);
        driver.findElement(By.xpath("//a[@href=\"/weather/city.php3?c=US&s=CA&statename=California-United-States-of-America\"]")).click();
        Thread.sleep(2000);
        driver.get("http://www.weatherbase.com/weather/weather.php3?s=160547&cityname=San-Jose-California-United-States-of-America");
        Thread.sleep(2000);
        String tempInF = driver.findElement(By.xpath("//*[@id=\"left-weather-content\"]/div/table[2]/tbody/tr[2]/td[2]")).getText();
        Thread.sleep(1000);
        double tempF = Double.parseDouble(tempInF);
        WebElement buttonC = driver.findElement(By.name("fc")); buttonC.click();

        String tempInC = driver.findElement(By.xpath("//*[@id=\"left-weather-content\"]/div/table[2]/tbody/tr[2]/td[2]")).getText();

        String convertedToC = String.format("%.1f", (tempF - 32) * 5 / 9);

        Assert.assertEquals(convertedToC, tempInC);
    }


  //  @AfterMethod

   // public void tearDown() {

      //  driver.quit();
   // }

}

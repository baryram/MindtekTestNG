package logoutFunctionality;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class TestLogoutFunc {

    WebDriver driver;

    @BeforeMethod
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.manage().window().maximize();


    }

    @Test

    public void visibleOrNot() {
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/");
        driver.findElement(By.name("username")).sendKeys("Mindtek");

        driver.findElement(By.name("password")).sendKeys("MindtekStudent");

        WebElement button = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));
        button.click();

        String actualLogoutButtonText = driver.findElement(By.xpath("//a[@href=\"/logout\"]")).getText();
        String expectedLogoutButtonText = "Logout";

        Assert.assertEquals(expectedLogoutButtonText, actualLogoutButtonText, "Failed");


    }

    @Test
    public void afterPressBack() throws InterruptedException {
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/");
        driver.findElement(By.name("username")).sendKeys("Mindtek");

        driver.findElement(By.name("password")).sendKeys("MindtekStudent");
        Thread.sleep(4000);
        WebElement button = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));
        button.click();

        Thread.sleep(4000);

        WebElement logoutButton = driver.findElement(By.xpath("//a[@href=\"/logout\"]"));
        logoutButton.click();

        Thread.sleep(4000);
        driver.navigate().back();

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://qeenv-webhr-arslan.herokuapp.com/login";

        Assert.assertEquals(actualUrl, expectedUrl);


    }

    @Test
    public void logoutPageDisplayed() throws InterruptedException {
        driver.get("https://qeenv-webhr-arslan.herokuapp.com/");
        driver.findElement(By.name("username")).sendKeys("Mindtek");

        driver.findElement(By.name("password")).sendKeys("MindtekStudent");
        Thread.sleep(4000);
        WebElement button = driver.findElement(By.xpath("//button[@class=\"btn btn-success\"]"));
        button.click();

        Thread.sleep(4000);

        WebElement logoutButton = driver.findElement(By.xpath("//a[@href=\"/logout\"]"));
        logoutButton.click();
        Thread.sleep(4000);

        String actualUrl = driver.getCurrentUrl();
        String expectedUrl = "https://qeenv-webhr-arslan.herokuapp.com/logout";

        Assert.assertEquals(actualUrl, expectedUrl, "Failed");


    }


    @AfterMethod
    public void tearDown() {
        driver.quit();

    }


}

package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import org.openqa.selenium.support.ui.Select;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

public class SmartBearTests {

    WebDriver driver;
    String username = "Tester";
    String password = "test";
    String expectedWelcomeText = "Welcome, Tester!";
    String expectedInvalidMessage = "Invalid Login or Password.";
    WebElement usernameInput;
    WebElement passwordInput;
    WebElement loginBtn;


    @BeforeMethod (groups = {"regression","smoke"})
    public void setUp() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        driver.get("http://secure.smartbearsoftware.com/samples/testcomplete11/WebOrders/login.aspx");

        usernameInput = driver.findElement(By.name("ctl00$MainContent$username"));
        passwordInput = driver.findElement(By.name("ctl00$MainContent$password"));
        loginBtn = driver.findElement(By.id("ctl00_MainContent_login_button"));


    }

    @Test(groups = {"regression", "smoke"})
    public void loginPositive() {


        usernameInput.sendKeys(username);
        passwordInput.sendKeys(password);
        loginBtn.click();
        String actualWelcomeMessage = driver.findElement(By.xpath("//form[@id=\"aspnetForm\"]//div[@class=\"login_info\"]"))
                .getText();
        Assert.assertEquals(actualWelcomeMessage.substring(0, actualWelcomeMessage.indexOf("!") + 1).trim(), expectedWelcomeText);

    }

    @Test(priority = 1, groups = {"regression"})
    public void loginNegative() {
        usernameInput.sendKeys("whatisit");
        passwordInput.sendKeys("1234");
        loginBtn.click();

        String actualInvalidMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMessage, expectedInvalidMessage);


    }

    @Test(priority = 2, groups = {"regression"})
    public void loginEmptyCredentials() {
        usernameInput.sendKeys("");
        passwordInput.sendKeys("");
        loginBtn.click();

        String actualInvalidMessage = driver.findElement(By.id("ctl00_MainContent_status")).getText();
        Assert.assertEquals(actualInvalidMessage, expectedInvalidMessage);


    }

    @Test(priority = 3, groups = {"regression"})
    public void totalAmountPositive() throws InterruptedException {
        loginPositive();
        WebElement orderTab = driver.findElement(By.xpath("//a[@href=\"Process.aspx\"]"));
        orderTab.click();
        Select product = new Select(driver.findElement(By.id("ctl00_MainContent_fmwOrder_ddlProduct")));
        WebElement quantityField = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtQuantity"));
        WebElement calculateBtn = driver.findElement(By.xpath("//input[@value=\"Calculate\"]"));


        Thread.sleep(1000);


        String quantity = "4";
        String price = "100";

        product.selectByVisibleText("MyMoney");
        quantityField.clear();
        quantityField.sendKeys(quantity);
        calculateBtn.click();
        Thread.sleep(2000);

        Integer expectedAmount = Integer.parseInt(quantity) * Integer.parseInt(price);
        WebElement totalAmount = driver.findElement(By.id("ctl00_MainContent_fmwOrder_txtTotal"));
        String realTotalAmount = totalAmount.getAttribute("value");
        System.out.println(realTotalAmount);

        Assert.assertEquals(realTotalAmount, expectedAmount.toString());


    }

    @AfterMethod(groups = {"regression","smoke"})

    public void tearDown() {

        driver.quit();
    }

}
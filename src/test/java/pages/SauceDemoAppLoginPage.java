package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SauceDemoAppLoginPage {

    WebDriver driver;

    public SauceDemoAppLoginPage(){

       driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id= "user-name")
    public WebElement usernameInput;

    @FindBy(id= "password")
    public WebElement passwordInput;

    @FindBy(id= "login-button")
    public WebElement loginBtn;

public void login() {
    driver.get(ConfigReader.getProperty("SauceDemoURL"));
    usernameInput.sendKeys(ConfigReader.getProperty("SauceDemoUsername"));
    passwordInput.sendKeys(ConfigReader.getProperty("SauceDemoPassword"));
    loginBtn.click();
}
}

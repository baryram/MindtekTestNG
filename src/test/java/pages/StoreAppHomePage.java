package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import tests.SauceDemoApp;
import utilities.Driver;

public class StoreAppHomePage {

    WebDriver driver;

    public StoreAppHomePage(){

        driver= Driver.getDriver();
        PageFactory.initElements(driver, this);
    }
    @FindBy(xpath = "//a[@title='Log in to your customer account']")
    public WebElement signUpBtn;
}

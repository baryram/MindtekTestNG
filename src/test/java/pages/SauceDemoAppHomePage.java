package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.ConfigReader;
import utilities.Driver;

public class SauceDemoAppHomePage {

    WebDriver driver;

    public SauceDemoAppHomePage(){

        driver = Driver.getDriver();
        PageFactory.initElements(driver,this);
    }

    @FindBy(id= "add-to-cart-sauce-labs-backpack")
    public WebElement backpackCartBtn;

    @FindBy(id= "add-to-cart-sauce-labs-bike-light")
    public WebElement bikeLightCartBtn;

    @FindBy(xpath= "//a[@class=\"shopping_cart_link\"]")
    public WebElement cartBtn;

    @FindBy(id= "checkout")
    public WebElement checkoutBtn;









}

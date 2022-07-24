package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppAccountPage {

    WebDriver driver;

    public StoreAppAccountPage(){

        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath ="//a[@title='Addresses']")
    public WebElement myAddressBtn;

    @FindBy(xpath ="//ul[@class='last_item item box']/li[2]/span[1]")
    public WebElement firstName;

    @FindBy(xpath ="//ul[@class='last_item item box']/li[2]/span[2]")
    public WebElement lastName;

    @FindBy(xpath ="//ul[@class='last_item item box']/li[4]")
    public WebElement address;

    @FindBy(xpath ="//ul[@class='last_item item box']/li[5]/span[1]")
    public WebElement city;

}

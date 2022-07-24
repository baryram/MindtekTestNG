package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

import java.util.List;

public class SauceDemoAppCheckoutPage {

    WebDriver driver;

    public SauceDemoAppCheckoutPage() {

        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id= "first-name")
    public WebElement firstName;

    @FindBy(id= "last-name")
    public WebElement lastName;

    @FindBy(id= "postal-code")
    public WebElement postalCode;

    @FindBy(id= "continue")
    public WebElement continueBtn;

    @FindBy(id= "finish")
    public WebElement finishBtn;

    @FindBy(xpath = "//div[@class='inventory_item_price']")
    public List<WebElement> itemPrices;

    @FindBy(xpath = "//div[@class='summary_subtotal_label']")
    public WebElement subtotal;

    public void checkoutWithValidInfo(){

        firstName.sendKeys("John");
        lastName.sendKeys("Doe");
        postalCode.sendKeys("12345");
        continueBtn.click();
    }

}

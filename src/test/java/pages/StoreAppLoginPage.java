package pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import utilities.Driver;

public class StoreAppLoginPage {

    WebDriver driver;

    public StoreAppLoginPage() {

        driver = Driver.getDriver();
        PageFactory.initElements(driver, this);
    }

    @FindBy(id = "email_create")
    public WebElement createEmailInput;

    @FindBy(id = "SubmitCreate")
    public WebElement createAccountBtn;

    @FindBy(id = "email")
    public WebElement emailInput;

    @FindBy(id = "passwd")
    public WebElement passwordInput;

    @FindBy(id = "SubmitLogin")
    public WebElement signInBtn;
}
package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import pages.SauceDemoAppCheckoutPage;
import pages.SauceDemoAppHomePage;
import pages.SauceDemoAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

import java.io.IOException;
import java.util.List;

public class SauceDemoApp extends TestBase {


    @Test(groups =  {"smoke", "regression", "login", "SDAT01"})
    public void verifyLoginPositive() {
        SauceDemoAppLoginPage sauceDemoAppLoginPage = new SauceDemoAppLoginPage();
        sauceDemoAppLoginPage.login();

        WebElement titleProduct = driver.findElement(By.xpath("//span[contains(text(), 'Products')]"));
        Assert.assertTrue(titleProduct.isDisplayed());
    }

    @Test(priority = 1, groups = {"smoke", "regression",""})
    public void verifyPricesHighToLow() throws IOException {


        SauceDemoAppLoginPage sauceDemoAppLoginPage = new SauceDemoAppLoginPage();
        sauceDemoAppLoginPage.login();
        BrowserUtils.takeScreenShot("SauceDemoApp-Login");

        Select filterDropDown = new Select(driver.findElement(By.xpath("//select[@class='product_sort_container']")));
        filterDropDown.selectByIndex(3);

        List<WebElement> itemPrices = driver.findElements(By.xpath("//div[@class='inventory_item_price']"));
        for (int i = 1; i < itemPrices.size(); i++) {

            double itemPrice1 = Double.parseDouble(itemPrices.get(i - 1).getText().substring(1));
            double itemPrice2 = Double.parseDouble(itemPrices.get(i).getText().substring(1));

            Assert.assertTrue(itemPrice1 >= itemPrice2);
            System.out.println(itemPrice1 + " is more than " + itemPrice2);
        }

    }

    @Test (priority = 2, groups = {"regression", "smoke", "healthcheck"})

    public void verifyTotalPrices() {

        SauceDemoAppLoginPage sauceDemoAppLoginPage = new SauceDemoAppLoginPage();
        sauceDemoAppLoginPage.login();

        SauceDemoAppHomePage sauceDemoAppHomePage = new SauceDemoAppHomePage();

        sauceDemoAppHomePage.backpackCartBtn.click();
        sauceDemoAppHomePage.bikeLightCartBtn.click();
        sauceDemoAppHomePage.cartBtn.click();
        sauceDemoAppHomePage.checkoutBtn.click();


        SauceDemoAppCheckoutPage sauceDemoAppCheckoutPage = new SauceDemoAppCheckoutPage();
        sauceDemoAppCheckoutPage.checkoutWithValidInfo();

        double expectedSubtotal = 0.0;

        for (int i = 0; i < sauceDemoAppCheckoutPage.itemPrices.size()-1; i++) {

                double price1 = Double.parseDouble(sauceDemoAppCheckoutPage.itemPrices.get(i).getText().substring(1));
                double price2 = Double.parseDouble(sauceDemoAppCheckoutPage.itemPrices.get(i+1).getText().substring(1));
                expectedSubtotal = price1+price2;
            }

            String subtotalText = sauceDemoAppCheckoutPage.subtotal.getText();
            double actualSubtotal = Double.parseDouble(subtotalText.substring(subtotalText.indexOf("$")+1));
            Assert.assertEquals(actualSubtotal,expectedSubtotal);

//POM: Design patter !!!
        //PageFactory command
                //@FindBy
                    // Comes from selenium
        }


    @Test

    public void sauceDemoAssignment() {
        SauceDemoAppLoginPage sauceDemoAppLoginPage = new SauceDemoAppLoginPage();
        sauceDemoAppLoginPage.login();


        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
        driver.findElement(By.xpath("//a[@class=\"shopping_cart_link\"]")).click();
        driver.findElement(By.id("checkout")).click();

        driver.findElement(By.id("first-name")).sendKeys("John");
        driver.findElement(By.id("last-name")).sendKeys("Doe");
        driver.findElement(By.id("postal-code")).sendKeys("60001");
        driver.findElement(By.id("continue")).click();
        driver.findElement(By.id("finish")).click();
        String actualMessage = driver.findElement(By.xpath("//h2[@class=\"complete-header\"]")).getText();
        Assert.assertEquals(actualMessage, "THANK YOU FOR YOUR ORDER");


    }


}

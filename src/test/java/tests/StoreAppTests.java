package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
import pages.StoreAppAccountPage;
import pages.StoreAppCreateAccountPage;
import pages.StoreAppHomePage;
import pages.StoreAppLoginPage;
import utilities.BrowserUtils;
import utilities.ConfigReader;
import utilities.TestBase;

public class StoreAppTests extends TestBase {


    String email;
    String password;
    String firstName;
    String lastName;
    String address;
    String city;

    @DataProvider(name = "signUpTestData")

    public static Object[][] testData() {
        return new Object[][]{
                {"Mehmet", "Parker", "123pass", "1", "6", "1990", "123 Buf st.", "Skokie", "13", "12345", "1234567899", " Parkito"}
//                {"Halo", "Balu", "123pass", "2", "7", "1992", "123 Muff st.", "Des", "13", "12345", "1234567899", " Parkitos"},
//
        };
    }

    @Test(groups = {"regression", "smoke"}, dataProvider = "signUpTestData")


    public void createAccountTest(
            String firstName,
            String lastName,
            String password,
            String day,
            String month,
            String year,
            String address,
            String city,
            String state,
            String postCode,
            String phoneNumber,
            String alias
    ) throws InterruptedException {
        StoreAppHomePage homePage = new StoreAppHomePage();
        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        StoreAppCreateAccountPage createAccountPage = new StoreAppCreateAccountPage();


        driver.get(ConfigReader.getProperty("StoreAppURL"));
        Thread.sleep(1000);
        homePage.signUpBtn.click();
        email = BrowserUtils.gertRandomEmail();

        loginPage.createEmailInput.sendKeys(email);
        loginPage.createAccountBtn.click();

        createAccountPage.firstNameinput.sendKeys(firstName);
        this.firstName = firstName;
        createAccountPage.lastNameInput.sendKeys(lastName);
        this.lastName = lastName;
        this.password = password;
        createAccountPage.passwordInput.sendKeys(password);

        BrowserUtils.selectDropDownByValue(createAccountPage.daysDropdown, day);
        BrowserUtils.selectDropDownByValue(createAccountPage.monthsDropdown, month);
        BrowserUtils.selectDropDownByValue(createAccountPage.yearsDropdown, year);

        this.address = address;
        createAccountPage.addressInput.sendKeys(address);
        this.city = city;
        createAccountPage.cityInput.sendKeys(city);
        BrowserUtils.selectDropDownByValue(createAccountPage.stateDropdown, state);


        createAccountPage.postcodeInput.sendKeys(postCode);
        createAccountPage.phoneInput.sendKeys(phoneNumber);
        createAccountPage.aliasInput.sendKeys(alias);
        createAccountPage.submitButton.click();


    }

    @Test(groups = {"regression", "mybestTest"}, dependsOnMethods = "createAccountTest")
    public void signInTest() throws InterruptedException {
        StoreAppHomePage homePage = new StoreAppHomePage();
        StoreAppLoginPage loginPage = new StoreAppLoginPage();
        StoreAppAccountPage accountPage = new StoreAppAccountPage();
        SoftAssert softAssert = new SoftAssert();

        driver.get(ConfigReader.getProperty("StoreAppURL"));
        Thread.sleep(1000);
        homePage.signUpBtn.click();
        loginPage.emailInput.sendKeys(email);
        loginPage.passwordInput.sendKeys(password);
        loginPage.signInBtn.click();
        String expectedTitle = "My account - My Store";
        String actualTitle = driver.getTitle();



        Assert.assertEquals(actualTitle, expectedTitle);
        accountPage.myAddressBtn.click();

        softAssert.assertEquals(accountPage.firstName.getText(), firstName);
        softAssert.assertEquals(accountPage.lastName.getText(), lastName);
        softAssert.assertEquals(accountPage.address.getText(), address);
        softAssert.assertEquals(accountPage.city.getText().replaceAll(",",""), city);

        softAssert.assertAll();

    }


}

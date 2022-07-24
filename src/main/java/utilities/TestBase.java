package utilities;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

public class TestBase {

    protected WebDriver driver;

    @BeforeMethod(groups =  {"smoke", "regression", "login", "SDAT01"})
    public void setUp() {
        driver = Driver.getDriver();
    }


    @AfterMethod(groups =  {"smoke", "regression", "login", "SDAT01"})
    public void tearDown() {

        driver.quit();
    }

}

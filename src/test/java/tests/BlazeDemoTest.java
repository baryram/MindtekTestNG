package tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;
import utilities.ConfigReader;
import utilities.TestBase;

public class BlazeDemoTest extends TestBase {

    @Test(groups = "regression")
    public void verifyFlightsDestination() {
        driver.get(ConfigReader.getProperty("BlazeDemoURL"));

        String myLocation = "Paris";
        String myDestination = "London";
        Select fromPort = new Select(driver.findElement(By.name("fromPort")));
        Select toPort = new Select(driver.findElement(By.name("toPort")));
        WebElement findFlightsButton = driver.findElement(By.xpath("//input[@value='Find Flights']"));

        fromPort.selectByValue(myLocation);
        toPort.selectByValue(myDestination);
        findFlightsButton.click();


        String expectedResult = "Flights from " + myLocation + " to " + myDestination + ":";

        String actualResult = driver.findElement(By.tagName("h3")).getText().trim();

        Assert.assertEquals(expectedResult, actualResult, "Passed");


    }

    @Test (groups = {"regression","smoke"})
    public void destinationOfTheWeek() {

        driver.get("https://blazedemo.com/");
        driver.findElement(By.xpath("//a[@href='vacation.html']")).click();
        String destination = driver.findElement(By.xpath("//div[contains(text(), 'Hawaii')]")).getText();

        Assert.assertEquals(destination.trim(), "Destination of the week: Hawaii !");

    }

}

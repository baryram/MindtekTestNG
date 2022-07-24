package utilities;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.io.File;
import java.io.IOException;
import java.util.Random;

public class BrowserUtils {
    /**
     * THis mehtod generates radnom emails
     */

    public static String gertRandomEmail() {
        String email = "Parkito";
        Random random = new Random();

        int number = random.nextInt(99_999);
        return email + number + "@gmail.com";
    }

    /**
     * THis method will accept WeBlement of drodown
     * and String value of dropdown and will select provided value
     * in dropdown
     */

    public static void selectDropDownByValue(WebElement element, String value) {
        Select select = new Select(element);
        select.selectByValue(value);

    }

    public static WebElement waitForElementToBeClickable(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        WebElement element1 = wait.until(ExpectedConditions.elementToBeClickable(element));
        return element1;
    }

    public static WebElement waitForElementToBeInvisible(WebElement element, int seconds) {
        WebDriverWait wait = new WebDriverWait(Driver.getDriver(), seconds);
        WebElement element1 = wait.until(ExpectedConditions.visibilityOf(element));
        return element1;
    }

    public static void takeScreenShot(String testName) throws IOException {
        WebDriver driver = Driver.getDriver();
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);

        String path = "src/main/resources/screenshots/" + testName + ".png";
        File file = new File(path);
        FileUtils.copyFile(screenshot, file);

    }

}

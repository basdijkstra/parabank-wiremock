package helpers;

import org.openqa.selenium.By;
import org.openqa.selenium.TimeoutException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.fail;

public class SeleniumHelpers {

    private WebDriver driver;
    private WebDriverWait wait;

    public SeleniumHelpers(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(this.driver, Duration.ofSeconds(10));
    }

    public void click(By by) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).click();
        }
        catch (TimeoutException te) {
            fail(String.format("Exception in click(): %s", te.getMessage()));
        }
    }

    public void sendKeys(By by, String textToType) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            driver.findElement(by).sendKeys(textToType);
        }
        catch (TimeoutException te) {
            fail(String.format("Exception in sendKeys(): %s", te.getMessage()));
        }
    }

    public void select(By by, String valueToSelect) {

        try {
            wait.until(ExpectedConditions.elementToBeClickable(by));
            new Select(driver.findElement(by)).selectByVisibleText(valueToSelect);
        }
        catch (TimeoutException te) {
            fail(String.format("Exception in select(): %s", by.toString()));
        }
    }

    public void selectWithWait(By by, String valueToSelect) {

        try {
            wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(by, By.xpath("//option[text()='"+ valueToSelect +"']")));
            new Select(driver.findElement(by)).selectByVisibleText(valueToSelect);
        }
        catch (TimeoutException te) {
            fail(String.format("Exception in selectWithWait(): %s", te.getMessage()));
        }
    }

    public boolean isDisplayed(By by) {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return true;
        }
        catch (TimeoutException te) {
            return false;
        }
    }

    public String getElementText(By by) {

        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(by));
            return driver.findElement(by).getText();
        }
        catch (TimeoutException te) {
            return "Element not found";
        }
    }
}

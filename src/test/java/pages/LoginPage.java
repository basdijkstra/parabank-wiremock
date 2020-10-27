package pages;

import helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class LoginPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldUsername = By.name("username");
    private By textfieldPassword = By.name("password");
    private By buttonDoLogin = By.xpath("//input[@value='Log In']");

    public LoginPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);

        driver.get("http://localhost:8080/parabank");
    }

    public LoginPage loginAs(String username, String password) {
        selenium.sendKeys(textfieldUsername, username);
        selenium.sendKeys(textfieldPassword, password);
        selenium.click(buttonDoLogin);
        return this;
    }
}

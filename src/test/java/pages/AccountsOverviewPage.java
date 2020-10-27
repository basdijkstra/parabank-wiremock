package pages;

import helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class AccountsOverviewPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    public AccountsOverviewPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public void selectMenuItem(String menuItem) {
        selenium.click(By.linkText(menuItem));
    }
}

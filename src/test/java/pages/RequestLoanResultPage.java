package pages;

import helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanResultPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldApplicationResult = By.id("loanStatus");
    private By textfieldLoanProviderName = By.id("loanProviderName");
    private By textfieldInternalErrorHasOccurred =
            By.xpath("//p[contains(text(),'An internal error has occurred and has been logged.')]");

    public RequestLoanResultPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public String getLoanProviderName() {
        return selenium.getElementText(textfieldLoanProviderName);
    }

    public String getLoanApplicationResult() {
        return selenium.getElementText(textfieldApplicationResult);
    }

    public boolean internalErrorMessageIsVisible() {
        return selenium.isDisplayed(textfieldInternalErrorHasOccurred);
    }
}

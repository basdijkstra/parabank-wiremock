package pages;

import helpers.SeleniumHelpers;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RequestLoanPage {

    private WebDriver driver;
    private SeleniumHelpers selenium;

    private By textfieldLoanAmount = By.id("amount");
    private By textfieldDownPayment = By.id("downPayment");
    private By dropdownFromAccountId = By.id("fromAccountId");
    private By buttonApplyForLoan = By.xpath("//input[@value='Apply Now']");

    public RequestLoanPage(WebDriver driver) {
        this.driver = driver;
        selenium = new SeleniumHelpers(driver);
    }

    public RequestLoanPage requestLoanFor(String loanAmount, String downPayment, String fromAccountId) {
        selenium.sendKeys(textfieldLoanAmount, loanAmount);
        selenium.sendKeys(textfieldDownPayment, downPayment);
        selenium.selectWithWait(dropdownFromAccountId, fromAccountId);
        selenium.click(buttonApplyForLoan);
        return this;
    }
}

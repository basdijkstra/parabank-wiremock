import com.github.tomakehurst.wiremock.junit5.WireMockTest;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@WireMockTest(httpPort = 9876)
public class RequestLoanTest {

    private WebDriver driver;

    @BeforeEach
    public void setupDriverAndLogInToParaBank() {

        driver = new ChromeDriver();
        driver.manage().window().maximize();

        new LoginPage(driver)
                .loginAs("john", "demo");
    }

    @Test
    public void createLoanFor10000_expectResultToBeApproved() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(driver)
                .requestLoanFor("10000", "100", "12345");

        assertEquals(
                "Lunch and Learn Loan Processor",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        assertEquals(
                "Approved",
                new RequestLoanResultPage(driver).getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor9000_expectResultToBeApproved_butSlower() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(driver)
                .requestLoanFor("9000", "100", "12345");

        assertEquals(
                "Sorry that took me so long...",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        assertEquals(
                "Approved",
                new RequestLoanResultPage(driver).getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor8000_expectResultToBeDenied() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(driver)
                .requestLoanFor("8000", "100", "12345");

        assertEquals(
                "Computer says NO",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        assertEquals(
                "Denied",
                new RequestLoanResultPage(driver).getLoanApplicationResult()
        );
    }

    @Test
    public void createLoanFor7000_expectInternalErrorToOccur() {

        new AccountsOverviewPage(driver)
                .selectMenuItem("Request Loan");

        new RequestLoanPage(driver)
                .requestLoanFor("7000", "100", "12345");

        assertTrue(
                new RequestLoanResultPage(driver).internalErrorMessageIsVisible()
        );
    }

    @AfterEach
    public void closeBrowser() {

        driver.quit();
    }
}

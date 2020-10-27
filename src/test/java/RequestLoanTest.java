import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import pages.*;

public class RequestLoanTest {

    private WebDriver driver;

    @Rule
    public WireMockRule wireMockRule = new WireMockRule(9876);

    @Before
    public void setupDriverAndLogInToParaBank() {

        System.setProperty("webdriver.chrome.driver", "src/test/resources/drivers/chromedriver.exe");

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

        Assert.assertEquals(
                "Sydney Testers Meetup Loan Processor",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        Assert.assertEquals(
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

        Assert.assertEquals(
                "Sorry that took me so long...",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        Assert.assertEquals(
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

        Assert.assertEquals(
                "Computer says NO",
                new RequestLoanResultPage(driver).getLoanProviderName()
        );

        Assert.assertEquals(
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

        Assert.assertTrue(
                new RequestLoanResultPage(driver).internalErrorMessageIsVisible()
        );
    }

    @After
    public void closeBrowser() {

        driver.quit();
    }
}

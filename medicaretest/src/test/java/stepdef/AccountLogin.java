package stepdef;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import properties.TestEnums.AccountTestType;
import properties.TestingProperties;
import testcases.AccountTests;

public class AccountLogin {
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest node;
	private AccountTests accountTest;

	@Given("^user attempts to login to site$")
	public void user_attempts_to_login_to_site() throws Throwable {
		accountTest = new AccountTests();
		accountTest.initiateAccountTests(AccountTestType.LOGIN);
		extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
	}

	@When("^credentials (.+) and (.+) are entered$")
	public void user_enters_and(String emailaddress, String pwd) throws Throwable {
		accountTest.initiateAccountLoginTest(emailaddress, pwd);
		logger = extent.startTest("Account Login Test");
		node = extent.startTest("Account Login test by user: " + emailaddress);
	}

	@Then("^navigates to home page if credentials are correct or displays login error for incorrect credentials$")
	public void navigates_to_home_page_if_credentials_are_correct_or_displays_login_error_for_incorrect_credentials()
			throws Throwable {
		accountTest.validateAccountLogin(extent, logger, node);
		extent.endTest(logger);
		extent.flush();
		extent.close();
		accountTest.exit();
	}
}

package stepdef;

import java.util.HashMap;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import properties.TestEnums.AccountTestType;
import properties.TestEnums.SignUpPage;
import properties.TestingProperties;
import testcases.AccountTests;

public class AccountSignup {
	
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest node;
	private AccountTests accountTest;
	private HashMap<String, String> userDetails = new HashMap<String, String>();
	
	@Given("^user accesses medicare site and clicks on Sign Up$")
    public void user_accesses_medicare_site_and_clicks_on_sign_up() throws Throwable {
        accountTest = new AccountTests();
        accountTest.initiateAccountTests(AccountTestType.SIGNUP);
        extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
		//throw new PendingException();
    }

    @When("^user adds personal details (.+) (.+) (.+) (.+) (.+)$")
    public void user_enters(String firstname, String lastname, String email, String contactnumber, String password) throws Throwable {
    	userDetails.put("First Name", firstname);
    	userDetails.put("Last Name", lastname);
    	userDetails.put("Email", email);
    	userDetails.put("Contact Number", contactnumber);
    	userDetails.put("Password", password);
    	logger = extent.startTest("Account Sign Up Tests");
    	accountTest.initiateAccountSignupTest(userDetails, SignUpPage.PAGEONE, extent, logger, node);
    }

    @And("^then enters additional addresses (.+) (.+) (.+) (.+) (.+) (.+)$")
    public void user_enters(String addressl1, String addressl2, String city, String postcode, String state, String country) throws Throwable {
    	System.out.println("Address L1: "+addressl1 + "Address L1: " + addressl2);
    	System.out.println("City: "+ city + "postcode: " + postcode);
    	System.out.println("State: "+ state + "country: " + country);
    	userDetails.put("AddressL1", addressl1);
    	userDetails.put("AddressL2", addressl2);
    	userDetails.put("City", city);
    	userDetails.put("Postal Code", postcode);
    	userDetails.put("State", state);
    	userDetails.put("Country", country);
    	accountTest.initiateAccountSignupTest(userDetails, SignUpPage.PAGETWO, extent, logger, node);
    }

    @Then("^the site should verify details and allow user to signup and login with created account$")
    public void allow_user_to_signup_and_login_with_created_account() throws Throwable {
    	accountTest.initiateAccountSignupTest(userDetails, SignUpPage.PAGETHREE, extent, logger, node);
    	accountTest.initiateAccountSignupTest(userDetails, SignUpPage.PAGEFOUR, extent, logger, node);
    	accountTest.exit();
    	extent.endTest(logger);
    	extent.flush();
		extent.close();
    	//throw new PendingException();
    }
}

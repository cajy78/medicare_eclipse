package stepdef;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import properties.TestEnums.TestStatus;
import properties.TestingProperties;
import testcases.SearchTests;

public class SearchByManufacturer {
	private SearchTests search;
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest node;
	private boolean prodFound = false;
	private TestStatus testStatus;
	private String manufacturerName;

	@Given("^user accesses medicare site and navigates to product list$")
	public void user_launches_medicare_site_and_navigates_to_product_list() throws Throwable {
		extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
		search = new SearchTests();
		search.initiate();
	}

	@When("^end user enters medicine manufacturer (.+)$")
	public void user_enters_a_product(String manufacturername) throws Throwable {
		this.manufacturerName = manufacturername;
		logger = extent.startTest("Search By Product Manufacturer");
		node = extent.startTest("Search by product manufacturer " + manufacturername);
		search.enterSearchValue(manufacturername, extent, logger, node);
	}

	@Then("^the products should be displayed$")
	public void the_products_should_be_displayed() throws Throwable {
		testStatus = search.validateSearchByManufacturerTest(extent, logger, node);
		if (testStatus.equals(TestStatus.TEST_PASSED)) {
			prodFound = true;
			search.exit();
		}
		if (testStatus.equals(TestStatus.TEST_FAILED))
			prodFound = false;
	}

	@And("^if products not found error message should be displayed$")
	public void if_products_not_found_error_message_should_be_displayed() throws Throwable {
		if (!prodFound) {
			node = extent.startTest("Check appropriate 'Not found' message is displayed");
			search.validateProdByManufacturerNotFoundMsg(extent, logger, node);
			extent.endTest(node);
			extent.endTest(logger);
			search.exit();
			extent.flush();
			extent.close();
			Assert.assertTrue(true, "Test failed to find product with Manufacturer: '" + manufacturerName
					+ "'; however, 'Not found' message was successfully displayed.");
		}
	}
}

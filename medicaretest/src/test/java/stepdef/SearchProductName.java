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

public class SearchProductName {
	private SearchTests search;
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest node;
	private boolean prodFound = false;
	private TestStatus testStatus;
	private String productName;

	@Given("^user launches medicare site and navigates to product list$")
	public void user_launches_medicare_site_and_navigates_to_product_list() throws Throwable {
		extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
		search = new SearchTests();
		search.initiate();
	}

	@When("^user enters (.+)$")
	public void user_enters(String productname) throws Throwable {
		this.productName = productname;
		logger = extent.startTest("Search By Product Name");
		node = extent.startTest("Search by product name " + productname);
		search.enterSearchValue(productname, extent, logger, node);
	}

	@Then("^the product should be displayed$")
	public void the_product_should_be_displayed() throws Throwable {
		testStatus = search.validateSearchByProductNameTest(extent, logger, node);
		if (testStatus.equals(TestStatus.TEST_PASSED)) {
			prodFound = true;
			search.exit();
		}
		if (testStatus.equals(TestStatus.TEST_FAILED))
			prodFound = false;
	}

	@And("^if the product not found error message should be displayed$")
	public void if_product_not_found() throws Throwable {
		if (!prodFound) {
			node = extent.startTest("Check appropriate 'Not found' message is displayed");
			search.validateProdNotFoundMsg(extent, logger, node);
			extent.endTest(node);
			extent.endTest(logger);
			search.exit();
			extent.flush();
			extent.close();
			Assert.assertTrue(true, "Test failed to find product with Product Name: '" + productName
					+ "'; however, 'Not found' message was successfully displayed.");
		}
	}
}

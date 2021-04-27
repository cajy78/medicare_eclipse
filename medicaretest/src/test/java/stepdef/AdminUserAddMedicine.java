package stepdef;

import java.util.HashMap;

import org.testng.Assert;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import properties.TestingProperties;
import properties.TestEnums.TestStatus;
import testcases.AdminUserTests;
import testcases.SearchTests;

public class AdminUserAddMedicine {

	private AdminUserTests adminUser;
	private ExtentReports extent;
	private ExtentTest logger, node;
	private String prodName;
	private SearchTests search;
	private TestStatus testStatus;
	private boolean prodFound = false;

	@Given("^admin user successfully signs in to the site with (.+) and (.+)$")
	public void admin_user_successfully_signs_in_to_the_site_with_and(String usid, String pswd) throws Throwable {
		extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
		logger = extent.startTest("Admin User Manage Products");
		adminUser = new AdminUserTests();
		adminUser.initiateAdminUserTests();
		adminUser.adminUserLogin(usid, pswd, extent, logger, node);
	}

	@When("^user navigates to manage product and enters (.+), (.+), (.+), (.+), (.+), (.+)$")
	public void user_navigates_to_manage_product_and_enters_(String prodname, String brandname, String desc,
			String price, String quantity, String category) throws Throwable {
		this.prodName = prodname;
		HashMap<String, String> prodDetails = new HashMap<String, String>();
		prodDetails.put("ProdName", prodname);
		prodDetails.put("BrandName", brandname);
		prodDetails.put("Description", desc);
		prodDetails.put("Price", price);
		prodDetails.put("Quantity", quantity);
		prodDetails.put("Category", category);
		adminUser.enterProdDetails(prodDetails, extent, logger, node);
	}

	@Then("^site should allow saving product to database$")
	public void site_should_allow_saving_product_to_database() throws Throwable {
		adminUser.submitProdDetails(prodName, extent, logger, node);
	}

	@And("^product should be visible in list of products$")
	public void product_should_be_visible_in_list_of_products() throws Throwable {
		node = extent.startTest("Find entered product '" + prodName + "'");
		search = new SearchTests();
		search.reinitiateDriver(adminUser.getDriver());
		search.enterSearchValue(prodName, extent, logger, node);
		testStatus = search.validateSearchByProductNameTest(extent, logger, node);
		if (testStatus.equals(TestStatus.TEST_PASSED)) {
			prodFound = true;
			search.exit();
		}
		if (testStatus.equals(TestStatus.TEST_FAILED))
			prodFound = false;

		if (!prodFound) {
			extent.endTest(logger);
			extent.flush();
			extent.close();
			Assert.assertTrue(false, "Test failed to find product with Product Name: '" + prodName + "'");
		}
	}
}

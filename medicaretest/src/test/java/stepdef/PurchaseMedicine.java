package stepdef;

import java.util.List;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import properties.TestingProperties;
import testcases.PurchaseTest;

public class PurchaseMedicine
{
	private PurchaseTest purchaseTest;
	private ExtentReports extent;
	private ExtentTest logger;
	private ExtentTest node;
	
	@Given("^registered users signs in to website with (.+) and (.+)$")
    public void registered_users_signs_in_to_website_with_and(String userid, String passwrd) throws Throwable {
		extent = new ExtentReports(TestingProperties.getExtentReportLocation(), false);
		purchaseTest = new PurchaseTest();
		purchaseTest.initiatePurchaseTest();
		logger = extent.startTest("Product Purchase Tests");
		purchaseTest.registedUserLogin(userid, passwrd, extent, logger, node);
    }

    @When("^user adds following products to cart$")
    public void user_adds_following_products_to_cart(List<String> prods) throws Throwable {
    	purchaseTest.addToCart(prods, extent, logger, node);
    }

    @Then("^site should allow user to purchase the products$")
    public void site_should_allow_user_to_purchase_the_products() throws Throwable {
    	System.out.println("Then executed");
    	purchaseTest.cartCheckOut(extent, logger, node);
    	extent.endTest(logger);
    	extent.flush();
    	extent.close();
    	purchaseTest.exit();
    }
}

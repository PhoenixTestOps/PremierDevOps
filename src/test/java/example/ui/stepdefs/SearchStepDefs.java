package example.ui.stepdefs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.ui.steplibs.CustomerSteps;
import net.thucydides.core.annotations.Steps;

public class SearchStepDefs {
	
	@Steps
	private CustomerSteps customerSteps;
	
	@Given("^I (?:want|would like) to buy (.*)$")
	public void givenCustomerWantsToBuy(String product) throws Throwable {
		
		customerSteps.openHomepage();
		
		String pageTitle = customerSteps.getHomePageTitle();
		assertThat(pageTitle, is("John Lewis | iPads, TVs, Furniture, Fashion & More"));		
	}

	@When("^I search for '(.*)'$")
	public void whenCustomerSearchesByKeyword(String keyword) throws Throwable {
		
		customerSteps.searchWithKeyword(keyword);
		
		String pageTitle = customerSteps.getSearchResultsPageTitle();
		assertThat(pageTitle, is("Buy \"" + keyword + "\" | John Lewis"));		
	}

	@Then("^I should see only products related to '(.*)'$")
	public void thenCustomersShouldSeeResultsRelatedTo(String keyword) throws Throwable {
		
		String breadcrumbText = customerSteps.getSearchPageBreadcrumb();
        assertThat(breadcrumbText, containsString(keyword));
	}
}

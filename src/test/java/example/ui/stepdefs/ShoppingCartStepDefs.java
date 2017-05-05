package example.ui.stepdefs;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.ui.steplibs.CustomerSteps;
import net.thucydides.core.annotations.Steps;

public class ShoppingCartStepDefs {
	
	@Steps
	private CustomerSteps customerSteps;	
	
	@Given("^I have searched for '(.*)'$")
	public void givenIHaveSearchedFor(String keyword) throws Throwable {
		
		customerSteps.openHomepage();
		customerSteps.searchWithKeyword(keyword);
		
		String pageTitle = customerSteps.getSearchResultsPageTitle();
		assertThat(pageTitle, is("Buy \"" + keyword + "\" | John Lewis"));
	}

	@When("^I add item (\\d+) to the basket$")
	public void whenIAddItemToBasket(int resultNum) throws Throwable {

		customerSteps.selectSearchResult(resultNum);
		customerSteps.addItemToBasket();
		
		String confirmation = customerSteps.getAddToBasketResult();
		assertThat(confirmation, containsString("Added to your basket"));
	}

	@Then("^the item should appear in the basket$")
	public void theItemShouldAppearInTheBasket() throws Throwable {
		
		customerSteps.viewBasket();
		
		String productCode = customerSteps.checkBasketContainsSelectedProduct();
		assertThat(productCode, containsString("58123923"));
	}

	@Then("^the delivery cost should be included in the total price$")
	public void the_delivery_cost_should_be_included_in_the_total_price() throws Throwable {

	}
}

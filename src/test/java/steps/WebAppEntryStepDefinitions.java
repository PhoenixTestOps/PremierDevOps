package steps;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import net.thucydides.core.annotations.Steps;
import pages.SearchPage;
import pages.WebAppHomePage;

import static org.junit.Assert.assertEquals;

/**
 * Created by
 */
public class WebAppEntryStepDefinitions {

    @Steps
    private WebAppHomePage webAppHomePage;

    @Steps
    private SearchPage searchPage;

    @Given("^I open the system home page$")
    public void iOpenTheSystemHomePage() throws Throwable {
        webAppHomePage.goToEntryPoint();
        assertEquals(webAppHomePage.returnHomePageTitle(),webAppHomePage.getTitle());
    }

    @When("^I provide the username \"([^\"]*)\"$")
    public void iProvideTheUsername(String username) throws Throwable {
        webAppHomePage.enterUsername(username);
    }

    @And("^I provide the password \"([^\"]*)\"$")
    public void iProvideThePassword(String pword) throws Throwable {
        webAppHomePage.enterPassword(pword);
    }

    @Then("^I can access the SearchPage \"([^\"]*)\"$")
    public void iCanAccessTheSearchPage(String searchpageurl) throws Throwable {
        webAppHomePage.clickLoginButton();
        searchPage.searchPageSimulation();
        assertEquals(searchPage.returnSearchPageTitle(),searchPage.getTitle());
        searchPage.verifySearchPageContent();
    }

    @Then("^I receive an error message \"([^\"]*)\"$")
    public void iReceiveAnErrorMessage(String error) throws Throwable {
        searchPage.searchPageError(error);
    }
}

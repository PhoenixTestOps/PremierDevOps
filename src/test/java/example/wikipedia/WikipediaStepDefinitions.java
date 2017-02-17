package example.wikipedia;

/**
 * @author : ABDUL SAIF
 * @date   : 07/12/2016
 */

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

public class WikipediaStepDefinitions {

    private WikipediaHomePage wikipediaHomePage;

    //ALL METHODS below are glued to FEATURES available within wikipedia.feature
    @Given("^the Wikipedia site is accessed$")
    public void theWikipediaSiteIsAccessed()
    {
        wikipediaHomePage.open();
    }

    @When("^I search for lists '(.*)'$")
    public void searchingFor(String actionName)
    {
        wikipediaHomePage.search(actionName);
    }

    @Then("I check results$")
    public void iCheckResults()
    {
        wikipediaHomePage.checkPage();
    }

}


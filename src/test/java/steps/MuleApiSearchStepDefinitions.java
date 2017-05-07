package steps;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import pages.MulePage;

import static org.junit.Assert.assertEquals;

/**
 * Created by
 */
public class MuleApiSearchStepDefinitions {

    private int RestResponseCode;

    @Given("^I am on the mule web page as user$")
    public void iAmOnTheMuleWebPageAsUser() throws Throwable {
        MulePage.setup();
    }

    @When("^I submit a search request based on \"([^\"]*)\"$")
    public void iSubmitASearchRequestBasedOn(String json) throws Throwable {
        String filepath = "src/test/resources/json/" + json;
        RestResponseCode = MulePage.postJSONFile("",filepath);
    }

    @Then("^I receive status code \"([^\"]*)\"$")
    public void iReceiveStatusCode(int rcode) throws Throwable {
        assertEquals(rcode, RestResponseCode);
    }
}

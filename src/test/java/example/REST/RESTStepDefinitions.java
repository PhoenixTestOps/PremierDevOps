package example.REST;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import java.util.HashMap;

import com.homeoffice.bdd.framework.utilities.DataMapper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


public class RESTStepDefinitions {

    private RESTPage restPage;
    private int jiraResponseCode;

    @Given("^I am on the JIRA web page as '(.*)'$")
    public void checkJIRA(String userName) throws Throwable {
    	
    	HashMap<String, String> usersMap = DataMapper.populate("src/test/resources/datamappings/users.csv");
        
        String password = usersMap.get(userName);
        
        assertFalse(password.isEmpty());
        
        RESTPage.setup(userName, password);
    }

    @Given("^I have permission to '(.*)'$")
    public void checkPermissions(String actionName) throws Throwable{ 
    	
    	assertTrue(restPage.havePermissions(actionName));
    }

    @When("^I create a new user story based on '(.*)'$")
    public void createStory(String actionName) throws Throwable{
        String filePath = "src/test/resources/test-data/JSON/"+actionName;
        jiraResponseCode = restPage.createStory(filePath);
    }

    @When("^I request to update the new story with details from '(.*)'$")
    public void requestUpdate(String actionName) throws Throwable{
        String filePath = "src/test/resources/test-data/JSON/"+actionName;
        jiraResponseCode = restPage.updateStory(filePath);
    }

    @Then("^I receive status code '(.*)$")
    public void checkUpdate(int responseCode) throws Throwable {
    	
    	assertEquals(responseCode, jiraResponseCode);
        //restPage.uResponseGET(responseCode);
    }

    @When("^I delete the new user story$")
    public void deleteStory() throws Throwable{
    	
    	jiraResponseCode = restPage.deleteStory();
    }

    @When("^I assign user '(.*)' to the new user story$")
    public void assignStory(String user) throws Throwable{
        String assignInput = "{\"name\":\""+user+"\"}";
        jiraResponseCode = restPage.assignStory(assignInput);
    }

    @When("^I request to transition the new story to '(.*)'$")
    public void closeStory(String actionName) throws Throwable{
    	HashMap<String, String> transitionsMap = DataMapper.populate("src/test/resources/datamappings/transitions.csv");
        String transitionID = transitionsMap.get(actionName);
        jiraResponseCode = restPage.transitionStory(transitionID);
    }
}

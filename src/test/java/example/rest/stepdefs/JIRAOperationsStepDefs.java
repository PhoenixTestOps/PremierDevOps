package example.rest.stepdefs;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.rest.model.JIRAOperations;

import java.io.IOException;
import java.util.HashMap;

import org.junit.BeforeClass;

import com.homeoffice.bdd.framework.utilities.DataMapper;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertEquals;


public class JIRAOperationsStepDefs {
	
	private JIRAOperations jiraOps;
	private int jiraResponseCode;
	
	@BeforeClass
	public void setup() throws IOException{
		
		jiraOps = new JIRAOperations();
	}
	
	@Given("^I am logged into JIRA as '(.*)'$")
	public void loginWith(String userName) throws Throwable {
		
		HashMap<String, String> usersMap = DataMapper.populate("src/test/resources/datamappings/users.csv");
		String password = usersMap.get(userName);        
		jiraOps.login(userName, password);
		
		assertFalse(password.isEmpty());
	}
	
	@Given("^I have permission to '(.*)'$")
	public void checkPermissions(String permission) throws Throwable{
		
		assertTrue(jiraOps.checkPermissions(permission));
	}
	
	@When("^I create a new user story$")
	public void createStory() throws Throwable{
		
		String filePath = "src/test/resources/json/create-issue.json";
		jiraResponseCode = jiraOps.createStory(filePath);
	}
	
	@When("^I assign the story to '(.*)'$")
	public void assignStory(String assignee) throws Throwable{
		
		String assignInput = "{\"name\":\""+assignee+"\"}";
		jiraResponseCode = jiraOps.assignStory(assignInput);
	}
	
	@When("^I delete the new user story$")
	public void deleteStory() throws Throwable{
		
		jiraResponseCode = jiraOps.deleteStory();
	}
	
	@When("^I request to transition the new story to '(.*)'$")
	public void closeStory(String state) throws Throwable{
		
		HashMap<String, String> transitionsMap = DataMapper.populate("src/test/resources/datamappings/transitions.csv");
		String transitionID = transitionsMap.get(state);
		jiraResponseCode = jiraOps.transitionStory(transitionID);
	}
	
	@When("^I request to update the new story with details from '(.*)'$")
	public void updateStory(String actionName) throws Throwable{
		
		String filePath = "src/test/resources/json/"+actionName;
		jiraResponseCode = jiraOps.updateStory(filePath);
	}
	
	@Then("^I receive the response code '(.*)$")
	public void checkResponseCode(int responseCode) throws Throwable {
		
		assertEquals(responseCode, jiraResponseCode);
	}
}
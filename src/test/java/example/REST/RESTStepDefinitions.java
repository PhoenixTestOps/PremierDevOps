package example.REST;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import example.utilities.DataMapping;

public class RESTStepDefinitions {

    private RESTPage restPage;


    @Given("^I am on the JIRA web page as '(.*)'$")
    public void checkJIRA(String userName) throws Throwable {
        DataMapping usersMap = new DataMapping();
        usersMap.fill_from_file("src/test/resources/test-data/mappings/users.csv");
        String password = usersMap.get(userName);
        if(password==null){
            System.out.println("This user is invalid.");
        }
        RESTPage.setup(userName, password);
    }

    @Given("^I have permission to '(.*)'$")
    public void checkPermissions(String actionName) throws Throwable{

        restPage.havePermissions(actionName);
    }

    @When("^I create a new user story based on '(.*)'$")
    public void createStory(String actionName) throws Throwable{
        String filePath = "src/test/resources/test-data/JSON/"+actionName;
        restPage.createStory(filePath);

    }

    @When("^I request to update the new story with details from '(.*)'$")
    public void requestUpdate(String actionName) throws Throwable{
        String filePath = "src/test/resources/test-data/JSON/"+actionName;
        restPage.updateStory(filePath);

    }

    @Then("^I receive status code '(.*)$")
    public void checkUpdate(int responseCode) throws Throwable {
        restPage.uResponseGET(responseCode);
    }

    @When("^I delete the new user story$")
    public void deleteStory() throws Throwable{
        restPage.deleteStory();
    }

    @When("^I assign user '(.*)' to the new user story$")
    public void assignStory(String user) throws Throwable{
        String assignInput = "{\"name\":\""+user+"\"}";
        restPage.assignStory(assignInput);
    }

    @When("^I request to transition the new story to '(.*)'$")
    public void closeStory(String actionName) throws Throwable{
        DataMapping transitionsMap = new DataMapping();
        transitionsMap.fill_from_file("src/test/resources/test-data/mappings/transitions.csv");
        String transitionID = transitionsMap.get(actionName);
        restPage.transitionStory(transitionID);
    }
}

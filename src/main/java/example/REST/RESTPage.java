package example.REST;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.homeoffice.bdd.framework.utilities.JSONDeserialiser;
import com.homeoffice.bdd.framework.utilities.PropertyReader;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import net.serenitybdd.core.pages.PageObject;

import java.io.File;
import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.*;

public class RESTPage extends PageObject {

    //ATTRIBUTES
    private static Response response;
    private static String storyID;
	private static String restBaseuri;
	private static String restBasePath;
	
	public RESTPage() throws IOException{
		
		String env = System.getProperty("env");
		File envFile = new File( "src/main/resources/" + env + ".properties" );
		
		PropertyReader.getProperties(envFile);		
		restBaseuri = PropertyReader.getValueOf("rest.baseuri");
		restBasePath = PropertyReader.getValueOf("rest.basePath");
	}
    
    //METHODS
    public static void setup(String username, String password){

        RestAssured.baseURI = restBaseuri;
        RestAssured.basePath = restBasePath;
        RestAssured.authentication = preemptive().basic(username, password);
    }

    public boolean havePermissions(String permissionRequired) {

        response = get("/mypermissions");
                      
        JsonElement jsonElement = new JsonParser().parse(response.asString());
        
        JsonObject permissions = jsonElement.getAsJsonObject().
        						 get("permissions").getAsJsonObject().
        						 get(permissionRequired).getAsJsonObject();
              
        Boolean hasPermission = permissions.get("havePermission").getAsBoolean();

        return hasPermission;
    }

    public int createStory(String filePath) {
    	
    	JsonObject fileObject = JSONDeserialiser.deserialise( new File(filePath));
    	
    	response = 	given().
    					contentType(ContentType.JSON).body(fileObject).
    				when().
    					post("/issue/").
    				then().
    					extract().response();
        
        JsonElement jsonElement = new JsonParser().parse(response.asString());

        storyID = jsonElement.getAsJsonObject().get("id").getAsString();
        //finalResponseCode = response.getStatusCode();
    	
    	return response.getStatusCode();
    }

    public int updateStory(String filePath) {

        JsonObject fileObject = JSONDeserialiser.deserialise( new File(filePath));
        
        response =	given().
        				contentType(ContentType.JSON).body(fileObject).
        			when().
        				put("/issue/" + storyID).
        			then().
        				extract().response();

        //finalResponseCode = response.getStatusCode();
        
        return response.getStatusCode();
    }

    public int deleteStory() {
    	
    	response =	when().
    					delete("/issue/" + storyID).
    				then().
    					extract().response();

        //finalResponseCode = response.getStatusCode();
    	
    	return response.getStatusCode();
    }

    public int assignStory(String user) {
    	
    	response =	given().
    					contentType(ContentType.JSON).body(user).
    				when().
    					put("/issue/" + storyID + "/assignee").
    				then().
    					extract().response();

        //finalResponseCode = response.getStatusCode();
    	
    	return response.getStatusCode();
    }

    public int transitionStory(String transitionID) {

        JsonElement jsonElement = new JsonParser().parse("{\"transition\": {\"id\": \""+transitionID+"\"}}");

        JsonObject fileObject = jsonElement.getAsJsonObject();
        
        response =	given().
        				contentType(ContentType.JSON).body(fileObject).
        			when().
        				post("/issue/" + storyID +"/transitions").
        			then().
        				extract().response();

        //finalResponseCode = response.getStatusCode();
        
        return response.getStatusCode();
    }
}
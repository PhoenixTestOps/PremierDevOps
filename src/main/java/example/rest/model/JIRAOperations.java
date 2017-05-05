package example.rest.model;

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
import java.io.FileNotFoundException;
import java.io.IOException;

import static net.serenitybdd.rest.SerenityRest.*;

public class JIRAOperations extends PageObject {
	
	private static Response response;
	private static String storyID;
	private static String restBaseuri;
	private static String restBasePath;
	
	public JIRAOperations() throws IOException{
		
		System.out.println("comstructir******************");
		
		//TODO - Update this such that the user specifies the entire path in mvn.
		//This will also mean that the property need not be passed in at teh cmd line
		//Also add a useful message incase the file is missing
		String env = System.getProperty("env");
		File envFile = new File( "src/main/resources/" + env + ".properties" );
		
		PropertyReader.getProperties(envFile);		
		restBaseuri = PropertyReader.getValueOf("rest.baseuri");
		restBasePath = PropertyReader.getValueOf("rest.basePath");
	}
	
	public void login(String username, String password){
		
		RestAssured.baseURI = restBaseuri;
		RestAssured.basePath = restBasePath;
		RestAssured.authentication = preemptive().basic(username, password);
	}
	
	public boolean checkPermissions(String permission) {
		
		response = get("/mypermissions");
		
		JsonElement jsonElement = new JsonParser().parse(response.asString());
		
		JsonObject permissions = jsonElement.getAsJsonObject().
								 get("permissions").getAsJsonObject().
								 get(permission).getAsJsonObject();
		
		Boolean hasPermission = permissions.get("havePermission").getAsBoolean();
		
		return hasPermission;
	}
	
	public int createStory(String filePath) throws FileNotFoundException {
		
		JsonObject fileObject = JSONDeserialiser.deserialiseToObject(new File(filePath));
		
		response = 	given().
						contentType(ContentType.JSON).body(fileObject).
					when().
						post("/issue/").
					then().
						extract().response();
		
		JsonElement jsonElement = new JsonParser().parse(response.asString());
		
		storyID = jsonElement.getAsJsonObject().get("id").getAsString();
		
		return response.getStatusCode();
	}
	
	public int updateStory(String filePath) throws FileNotFoundException {
		
		JsonObject fileObject = JSONDeserialiser.deserialiseToObject(new File(filePath));
		
		response =	given().
						contentType(ContentType.JSON).body(fileObject).
					when().
						put("/issue/" + storyID).
					then().
						extract().response();
		
		return response.getStatusCode();
	}
	
	public int deleteStory() {
		
		response =	when().
						delete("/issue/" + storyID).
					then().
						extract().response();
		
		return response.getStatusCode();
	}
	
	public int assignStory(String user) {
		
		response =	given().
						contentType(ContentType.JSON).body(user).
					when().
						put("/issue/" + storyID + "/assignee").
					then().
						extract().response();
		
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
		return response.getStatusCode();
	}
}
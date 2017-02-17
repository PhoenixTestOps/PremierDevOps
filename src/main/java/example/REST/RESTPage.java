package example.REST;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;

import example.utilities.Environment;
import net.serenitybdd.core.pages.PageObject;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

import static net.serenitybdd.rest.SerenityRest.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class RESTPage extends PageObject {

    //ATTRIBUTES
    private static Response response;
    private static int finalResponseCode;
    private static String storyID;
	private static String restBaseuri;
	private static String restBasePath;
	
	public RESTPage() throws IOException{
		Properties properties = Environment.getProperties();
		restBaseuri = properties.getProperty("rest.baseuri");
		restBasePath = properties.getProperty("rest.basePath");
	}
    
    //METHODS
    public static void setup(String username, String password){

        RestAssured.baseURI = restBaseuri;
        RestAssured.basePath = restBasePath;
        RestAssured.authentication = preemptive().basic(username, password);
    }

    public void havePermissions(String permissionRequired) {

        response = when().
                get("/mypermissions").
                then().
                statusCode(200).
                extract().response();

        JsonElement jsonElement = new JsonParser().parse(response.asString());

        JsonObject permissions =
                jsonElement.getAsJsonObject().get("permissions").getAsJsonObject().
                        get(permissionRequired).getAsJsonObject();

        Boolean hasPermission = permissions.get("havePermission").getAsBoolean();

        assertTrue(hasPermission);
    }

    public void createStory(String filePath) {
        JsonObject fileObject = serialiseJsonObject(filePath);

        response = given().
                contentType("application/json").
                body(fileObject).
                when().
                post("/issue/").
                then().
                extract().response();

        finalResponseCode = response.getStatusCode();

        JsonElement jsonElement = new JsonParser().parse(response.asString());

        storyID = jsonElement.getAsJsonObject().get("id").getAsString();
    }

    public void updateStory(String filePath) {

        JsonObject fileObject = serialiseJsonObject(filePath);

        response = given().
                contentType("application/json").
                body(fileObject).
                when().
                put("/issue/" + storyID).
                //post("/issue").
                        then().
                        extract().response();

        finalResponseCode = response.getStatusCode();
    }

    public void deleteStory() {
        response = when().
                delete("/issue/" + storyID).
                then().
                extract().response();

        finalResponseCode = response.getStatusCode();
    }

    public void assignStory(String user) {
        response = given().
                contentType(ContentType.JSON).
                body(user).
                when().
                put("/issue/" + storyID + "/assignee").
                then().
                extract().response();

        finalResponseCode = response.getStatusCode();
    }

    public void transitionStory(String transitionID) {

        JsonElement jsonElement = new JsonParser().parse("{\"transition\": {\"id\": \""+transitionID+"\"}}");

        JsonObject fileObject = jsonElement.getAsJsonObject();

        response = given().
                contentType("application/json").
                body(fileObject).
                when().
                post("/issue/" + storyID +"/transitions").
                //post("/issue").
                        then().
                        extract().response();

        finalResponseCode = response.getStatusCode();

    }

    public void uResponseGET(int responseCode) {
        assertEquals(responseCode, finalResponseCode);
    }

    public static JsonObject serialiseJsonObject(String jsonFile) {

        JsonObject jsonObject;

        JsonParser parser = new JsonParser();
        JsonElement jsonElement = null;

        try {
            jsonElement = parser.parse(new FileReader(jsonFile));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }

        jsonObject = jsonElement.getAsJsonObject();

        return jsonObject;

    }
}
package pages;

import com.google.gson.JsonObject;
import com.homeoffice.bdd.framework.utilities.JSONDeserialiser;
import com.jayway.restassured.RestAssured;
import com.jayway.restassured.http.ContentType;
import com.jayway.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

import java.io.File;
import java.io.FileNotFoundException;

/**
 * Created by
 */
public class MulePage {

    private static String muleBaseURI = "http://127.0.0.1:8081";
    private static String muleBasePath = "api/search";
    private static Response response;

    public static void setup(){
        RestAssured.baseURI = muleBaseURI;
        RestAssured.basePath = muleBasePath;
        //RestAssured.authentication = SerenityRest.preemptive().basic("steve", "steve");
        // System.out.println(RestAssured.ge);
    }

    public static int postJSONFile(String place, String filePath) throws FileNotFoundException {

        JsonObject fileObject = JSONDeserialiser.deserialiseToObject(new File(filePath));

        response = SerenityRest.given()
                                    .contentType(ContentType.JSON).body(fileObject)
                                .when()
                                    .post()
                                .then()
                                    .extract().response();
        return response.getStatusCode();
    }

    public static int getSomething(String place, String Thing) {

        response = SerenityRest.given()
                                .when()
                                    .get("/" +place +"/"+Thing)
                                .then()
                                    .extract().response();
        return response.getStatusCode();
    }
}
package infoins.api.admin.userProfile;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author : Eranda Kodagoda
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class UserProfileController extends BaseClass {

    String baseURL;
    String modifyEndPoint = "/user-profiles";
    String getOneEndPoint = "/user-profiles/{id}";


    @Test(priority = 1)
    public void modifyUserProfileValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int RandomInt = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"otherName\": \"Lucky"+RandomInt+"\",\n" +
                        "  \"photo\": \"photo"+RandomInt+"\",\n" +
                        "  \"signature\": \"signature"+RandomInt+"\",\n" +
                        "  \"userId\": 40,\n" +
                        "  \"userPrimaryEmail\": \"lucky"+RandomInt+"@gmail.com\",\n" +
                        "  \"userPrimaryPhone\": \"07285615"+RandomInt+"\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .body("message", equalTo("Data updated successfully"));

    }
    //Verify invalid email
    @Test
    public void modifyUserProfileInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int RandomInt = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"otherName\": \"Lucky"+RandomInt+"\",\n" +
                        "  \"photo\": \"photo"+RandomInt+"\",\n" +
                        "  \"signature\": \"signature"+RandomInt+"\",\n" +
                        "  \"userId\": 40,\n" +
                        "  \"userPrimaryEmail\": \"lucky"+RandomInt+"gmail.com\",\n" +
                        "  \"userPrimaryPhone\": \"07285615"+RandomInt+"\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .body("error_description", equalTo("Email must be a well-formed email address"));

    }
    //Verify invalid contact
    @Test
    public void modifyUserProfileInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int RandomInt = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"otherName\": \"Lucky"+RandomInt+"\",\n" +
                        "  \"photo\": \"photo"+RandomInt+"\",\n" +
                        "  \"signature\": \"signature"+RandomInt+"\",\n" +
                        "  \"userId\": 40,\n" +
                        "  \"userPrimaryEmail\": \"lucky"+RandomInt+"@gmail.com\",\n" +
                        "  \"userPrimaryPhone\": \"07285615\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .body("error_description", equalTo("Invalid Phone Number"));

    }

    @Test(priority = 2)
    public void getOneUserProfileValidTest() throws IOException {
        int id = 40;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Data List: " + jsonStr);
    }
    @Test
    public void getOneUserProfileInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
}

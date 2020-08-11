package infoins.api.admin.userProfile;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

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
    public void modifyValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-user-profile-valid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(201);

    }

    @Test
    public void modifyInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-user-profile-invalid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void getOneValidTest() throws IOException {

        int id = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void getOneInvalidTest() throws IOException {

        String id = "id";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
}

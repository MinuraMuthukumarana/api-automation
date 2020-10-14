package infoins.api.admin.userAdditionalInfo;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author : Minura Muthukumarana
 *  * @date : September 21, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class UserAdditionalInfoController  extends BaseClass {

    String baseURL;
    String getAllInfoEndPoint ="/userAdditionalInfo/{userId}";
    String deleteEndPoint ="/userAdditionalInfo/{id}";

    @Test(priority = 1)
    public void getAllInfoUserAdditionalValidTest() throws IOException{
        int Id = 3;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllInfoEndPoint, Id)
                .then()
                .assertThat().statusCode(200);

    }

    @Test(priority = 2)
    public void deleteUserAdditionalValidTest() throws IOException{

        int Id = 3;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(getAllInfoEndPoint, Id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message",equalTo("Data deleted successfully"));

    }
    @Test
    public void deleteUserAdditionalInvalidTest() throws IOException {
        String id = "0";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
}

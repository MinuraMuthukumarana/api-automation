package infoins.api.client.blackListed;

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
public class blackListedController extends BaseClass {
    String baseURL;
    String createEndPoint = "/cm/blacklisted";
    String updatedEndPoint = "/cm/blacklisted";
    String getAllWithPaginationEndPoint = "/cm/blacklisted/all/pagination";
    String getBulkEndPoint = "/cm/blacklisted/bulk";
    String getViewOneEndPoint = "/cm/blacklisted/view/{clientId}";
    String getViewAllEndPoint ="/cm/blacklisted/view/all";
    String deleteEndPoint="/cm/blacklisted/{id}";
    String deleteAllEndPoint = "/cm/blacklisted/all/{ids}";

    @Test(priority = 1)
    public void createBlackListedValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"blacklisted-create-black-listed-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    @Test
    public void createBlackListedInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"blacklisted-create-black-listed-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void updateBlackListedValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"blacklisted-update-black-listed-valid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void updateBlackListedInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"blacklisted-update-black-listed-valid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 3)
    public void getAllWithPaginationBlackListedValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 5)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithPaginationBlackListedInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 5)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 4)
    public void getBulkBlackListedInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getBulkEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].name", equalTo("email-template1"));
    }

    @Test(priority = 5)
    public void viewOneBlackListedValidTest() throws IOException {
        int clientId = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void viewOneBlackListedInvalidTest() throws IOException {
        String clientId = "clientId";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 6)
    public void viewAllBlackListedInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].name", equalTo("email-template1"));
    }

    @Test(priority = 7)
    public void deleteBlackListedValidTest() throws IOException{
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
    @Test
    public void deleteBlackListedInvalidTest() throws IOException{
        String id = "id";
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

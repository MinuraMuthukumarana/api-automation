package infoins.api.admin.genericRest;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeTest;
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

public class GenericRestController extends BaseClass {

    String baseURL;
    String saveEndPoint = "/microller/generic";
    String updateEndPoint = "/microller/generic";
    String deleteEndPoint = "/microller/generic";
    String findAllEndPoint = "/microller/generic/findAll";
    String findAllPaginationEndPoint = "/microller/generic/findAll/paginate";
    String findByEndPoint = "/microller/generic/findBy/reference";
    String findByIdEndPoint = "/microller/generic/findById";



    @Test(priority = 1)
    public void saveValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", "AD")
                .body(getGeneratedString("\\admin\\"+"generic-rest-save-valid.json"))
                .when()
                .post(saveEndPoint)
                .then()
                .assertThat().statusCode(201);

    }
    @Test
    public void saveInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", -1)
                .body(getGeneratedString("\\admin\\"+"generic-rest-save-valid.json"))
                .when()
                .post(saveEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void updateValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", "AD")
                .body(getGeneratedString("\\admin\\"+"generic-rest-update-valid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200);

    }
    @Test
    public void updateInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", -1)
                .body(getGeneratedString("\\admin\\"+"generic-rest-update-valid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 3)
    public void deleteValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", "AD")
                .body(getGeneratedString("\\admin\\"+"generic-rest-delete-valid.json"))
                .when()
                .delete(deleteEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void deleteInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", -1)
                .body(getGeneratedString("\\admin\\"+"generic-rest-delete-valid.json"))
                .when()
                .delete(deleteEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void findAllValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", "AD")
                .when()
                .get(findAllEndPoint)
                .then()
                .assertThat().statusCode(200);

    }
    @Test
    public void findAllInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", -1)
                .when()
                .get(findAllEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 5)
    public void findAllPaginationValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("model", "AD")
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(findAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);

    }
    @Test
    public void findAllPaginationInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("id", 1)
                .queryParam("model", "AD")
                .queryParam("reference", "1")
                .when()
                .get(findAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 6)
    public void findByValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("id", 1)
                .queryParam("model", "AD")
                .queryParam("reference", "1")
                .when()
                .get(findByEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void findByInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("id", "id")
                .queryParam("model", -1)
                .queryParam("reference", -1)
                .when()
                .get(findByEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 7)
    public void findByIdValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("id", 1)
                .queryParam("model", "AD")
                .when()
                .get(findByIdEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void findByIdInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("id", "id")
                .queryParam("model", -1)
                .when()
                .get(findByIdEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }
}

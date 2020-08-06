package infoins.api.admin.language;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LanguageController extends BaseClass {

    String baseURL;
    String createEndPoint = "/app-languages";
    String modifyEndPoint = "/app-languages";
    String getOneEndPoint = "/app-languages/{id}";
    String deleteEndPoint = "/app-languages/{id}";
    String deleteAllEndPoint = "/app-languages/all/{ids}";
    String getBulkEndPoint = "/app-languages/bulk";
    String createMultipleEndPoint = "/app-languages/multiple";


    @Test(priority = 1)
    public void createValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-language-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }

    @Test
    public void createInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-language-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void modifyValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-language-valid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void modifyInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-language-invalid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 3)
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
                .assertThat().statusCode(200)
                .and()
                .body("languageId", equalTo(1));

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

    @Test(priority = 4)
    public void deleteValidTest() throws IOException {

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
    public void deleteInvalidTest() throws IOException {

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

    @Test(priority = 5)
    public void deleteAllValidTest() throws IOException {

        String ids = "1,2";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteAllEndPoint, ids)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));

    }

    @Test
    public void deleteAllInvalidTest() throws IOException {

        String ids = "";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteAllEndPoint, ids)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 6)
    public void getBulkValidTest() throws IOException {

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
                .body("[0].languageId", equalTo(1))
                .and()
                .body("[1].languageId", equalTo(2));

    }

    @Test(priority = 7)
    public void createMultipleValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-language-multiple-valid.json"))
                .when()
                .post(createMultipleEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }

    @Test
    public void createMultipleInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-language-multiple-invalid.json"))
                .when()
                .post(createMultipleEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
}

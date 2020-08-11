package infoins.api.admin.email;

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

public class EmailController extends BaseClass {

    String baseURL;
    String createEndPoint = "/app-email-configs";
    String modifyEndPoint = "/app-email-configs";
    String getOneEndPoint = "/app-email-configs/{id}";
    String deleteEndPoint = "/app-email-configs/{id}";
    String getOneTemplateWithChildrenEndPoint = "/app-email-configs/template/{id}/children";
    String getOneTemplateParentEndPoint = "/app-email-configs/template/{id}/parent";
    String getAllTemplatesWithChildrenEndPoint = "/app-email-configs/template/all/children";
    String getAllTemplateParentsEndPoint = "/app-email-configs/template/all/parent";

    @Test(priority = 1)
    public void createValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-email-valid.json"))
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
                .body(getGeneratedString("create-email-invalid.json"))
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
                .body(getGeneratedString("modify-email-valid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }

    @Test
    public void modifyInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-email-invalid.json"))
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
    public void getOneTemplateWithChildrenValidTest() throws IOException {

        int id = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateWithChildrenEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("code", equalTo("template1"));

    }

    @Test
    public void getOneTemplateWithChildrenInvalidTest() throws IOException {

        String id = "id";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateWithChildrenEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 6)
    public void getOneTemplateParentValidTest() throws IOException {

        int id = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateParentEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("name", equalTo("email-template1"));

    }

    @Test
    public void getOneTemplateParentInvalidTest() throws IOException {

        String id = "id";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateParentEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }


    @Test(priority = 7)
    public void getAllTemplatesWithChildrenValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllTemplatesWithChildrenEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].code", equalTo("template1"))
                .and()
                .body("[1].code", equalTo("template2"));

    }


    @Test(priority = 8)
    public void getAllTemplateParentsEndPointValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllTemplateParentsEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].name", equalTo("email-template1"))
                .and()
                .body("[1].name", equalTo("email-template2"));

    }
}

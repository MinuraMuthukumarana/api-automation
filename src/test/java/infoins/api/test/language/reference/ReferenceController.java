package infoins.api.test.language.reference;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
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

public class ReferenceController extends BaseClass {
    private int x;
    String baseURL;
    String saveReferenceDataEndPoint = "/microller/references/{entityName}";
    String updateReferenceDataEndPoint = "/microller/references/{entityName}";
    String deleteReferenceDataEndPoint = "/microller/references/{entityName}";
    String findReferenceDataByEntityAndFkEndPoint = "/microller/references/{entityName}/{reference}/{id}";
    String findAllDataByEntityNameEndPoint = "/microller/references/data/find-all/{entityName}";
    String findDetailedDataByEntityAndIdEndPoint = "/microller/references/data/find-one/{entityName}/{id}";
    String findAllModuleReferencesEndPoint = "/microller/references/module-references/{moduleName}";



    @Test(priority = 1)
    public void createReferenceValidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-save-reference-data-valid.json"))
                .when()
                .post(saveReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(201)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Created ID: " + jsonStr);

    }
    @Test
    public void createReferenceInValidTest() throws IOException {

        int entityName = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-save-reference-data-valid.json"))
                .when()
                .post(saveReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void createPayLoadInvalidTest() throws IOException {
        String entityName = "entity1";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-save-reference-data-invalid.json"))
                .when()
                .post(saveReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void updateReferenceDataValidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-update-reference-data-valid.json"))
                .when()
                .put(updateReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Operation Success"));

    }
    @Test
    public void updateReferenceDataEntityNameInvalidTest() throws IOException {
        int entityName = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-update-reference-data-valid.json"))
                .when()
                .put(updateReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void updateReferenceDataPayLoadInvalidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-update-reference-data-invalid.json"))
                .when()
                .put(updateReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 3)
    public void findAllDataByEntityNameValidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("requestType", "")
                        .contentType(ContentType.JSON)
                        .when()
                        .get(findAllDataByEntityNameEndPoint, entityName)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void findAllDataByEntityNameENameInvalidTest() throws IOException {
        int entityName = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 100)
                .queryParam("requestType", "bypass")
                .contentType(ContentType.JSON)
                .when()
                .get(findAllDataByEntityNameEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findAllDataByEntityNamePageNoInvalidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .queryParam("pageNo", "pNo")
                .queryParam("pageSize", 100)
                .queryParam("requestType", "bypass")
                .contentType(ContentType.JSON)
                .when()
                .get(findAllDataByEntityNameEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findAllDataByEntityNamePageSizeInvalidTest() throws IOException {
        String entityName = "SyDrClass";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", "pSize")
                .queryParam("requestType", "bypass")
                .contentType(ContentType.JSON)
                .when()
                .get(findAllDataByEntityNameEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
//    @Test
//    public void findAllDataByEntityNameRequestTypeInvalidTest() throws IOException {
//        String entityName = "SyDrClass";
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .queryParam("pageNo", 1)
//                .queryParam("pageSize", 100)
//                .queryParam("requestType", 1)
//                .contentType(ContentType.JSON)
//                .when()
//                .get(findAllDataByEntityNameEndPoint, entityName)
//                .then()
//                .assertThat().statusCode(400)
//                .and()
//                .body("error", equalTo("Bad Request"));
//
//    }

    @Test(priority = 4)
    public void findDetailedDataByEntityAndIdValidTest() throws IOException {
        String entityName = "SyDrClass";
        int id = 2;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(findDetailedDataByEntityAndIdEndPoint, entityName, id)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data "+x+" List: " + jsonStr);

    }
    @Test
    public void findDetailedDataByEntityAndIdEntityNameInvalidTest() throws IOException {
        int entityName = 1;
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findDetailedDataByEntityAndIdEndPoint, entityName, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findDetailedDataByEntityAndIdIDInvalidTest() throws IOException {
        String entityName = "entity1";
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findDetailedDataByEntityAndIdEndPoint, entityName, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 5)
    public void deleteReferenceDataValidTest() throws IOException {
        String entityName = "SyDrDateConfig";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-delete-reference-data-valid.json"))
                .when()
                .delete(deleteReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Operation Success"));
    }
    @Test
    public void deleteReferenceDataEntityNameInvalidTest() throws IOException {
        int entityName = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-delete-reference-data-valid.json"))
                .when()
                .delete(deleteReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void deleteReferenceDataPayLoadInvalidTest() throws IOException {
        String entityName = "entity1";
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"reference-delete-reference-data-invalid.json"))
                .when()
                .delete(deleteReferenceDataEndPoint, entityName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 6)
    public void findAllModuleReferencesValidTest() throws IOException {
        String moduleName = "AD";
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .when()
                .get(findAllModuleReferencesEndPoint, moduleName)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void findAllModuleReferencesModuleNameInvalidTest() throws IOException {
        int moduleName = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 10)
                .when()
                .get(findAllModuleReferencesEndPoint, moduleName)
                .then()
                .assertThat().statusCode(204);

    }
    @Test
    public void findAllModuleReferencesPageNoInvalidTest() throws IOException {
        String moduleName = "AD";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", "pNo")
                .queryParam("pageSize", 10)
                .when()
                .get(findAllModuleReferencesEndPoint, moduleName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findAllModuleReferencesPageSizeInvalidTest() throws IOException {
        String moduleName = "AD";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", "pSize")
                .when()
                .get(findAllModuleReferencesEndPoint, moduleName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 7)
    public void findReferenceDataByEntityAndFkValidTest() throws IOException {
        String entityName = "entity1";
        int id = 1;
        String reference = "1";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findReferenceDataByEntityAndFkEndPoint, entityName, reference, id)
                .then()
                .assertThat().statusCode(200);

    }
    @Test
    public void findReferenceDataByEntityAndFkEntityNameInvalidTest() throws IOException {
        int entityName = 1;
        int id = 1;
        String reference = "1";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findReferenceDataByEntityAndFkEndPoint, entityName, reference, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findReferenceDataByEntityAndFkIdInvalidTest() throws IOException {
        String entityName = "entity1";
        String id = "id";
        int reference = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findReferenceDataByEntityAndFkEndPoint, entityName, reference, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    @Test
    public void findReferenceDataByEntityAndFkReferenceInvalidTest() throws IOException {
        String entityName = "entity1";
        int id = 1;
        String reference = "reference";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(findReferenceDataByEntityAndFkEndPoint, entityName, reference, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

}

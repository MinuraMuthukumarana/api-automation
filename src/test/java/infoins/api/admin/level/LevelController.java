package infoins.api.admin.level;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * @author : Eranda Kodagoda
 *  * @date : August 11, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class LevelController extends BaseClass {
    String baseURL;

    @Test (priority = 1)
    public void createLevelConfig() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-level-success.json"))
                .when()
                .post(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }

    @Test (priority = 2)
    public void getBulkLevelConfigs() throws IOException {
        baseURL = getURL();
        String getBulkLevelConfigEndpoint = "/level-configs/bulk";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .get(getBulkLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("[0].levelConfigId",equalTo(1));
    }
    @Test(priority = 3)
    public void modifyInvalidLevelConfig() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-level-invalid.json"))
                .when()
                .put(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message",equalTo("Bad Request"));
    }
    @Test(priority = 4)
    public void modifyLevelConfig() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-level-modify.json"))
                .when()
                .put(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data Updated Successfully"));
    }
    @Test(priority = 5)
    public void getOneLevelConfig() throws IOException {
        baseURL = getURL();
        String levelId = "1";
        String getLevelConfigEndpoint = "/level-configs/{id}";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .get(getLevelConfigEndpoint,levelId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("levelName",equalTo("Level 0001"));
    }
    @Test(priority = 6)
    public void deleteLevelConfig() throws IOException {
        baseURL = getURL();
        String levelId = "1";
        String deleteLevelConfigEndpoint = "/level-configs/{id}";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .delete(deleteLevelConfigEndpoint,levelId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data Deleted Successfully"));
    }
    @Test(priority = 7)
    public void deleteBulkLevelConfig() throws IOException {
        baseURL = getURL();
        String levelIds = "2,3";
        String deleteBulkLevelConfigEndpoint = "/level-configs/all/{ids}";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .delete(deleteBulkLevelConfigEndpoint,levelIds)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("data deleted successfully"));
    }
}

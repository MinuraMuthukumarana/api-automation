package infoins.api.admin.level;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

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
    private int x;
    String baseURL;
    String createLevelConfigEndpoint = "/level-configs";
    String getAllPaginationEndPoint = "/level-configs/all/pagination";
    String getLevelConfigEndpoint = "/level-configs/{id}";
    String deleteLevelConfigEndpoint = "/level-configs/{id}";
    String getBulkLevelConfigEndpoint = "/level-configs/bulk";



    @Test (priority = 1)
    public void createLevelConfig() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
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
    public void getAllWithPagination() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "levelConfigId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.levelConfigId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.levelConfigId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void modifyLevelConfig() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"isCurrency\": \"Y\",\n" +
                        "  \"isFiscalYear\": \"Y\",\n" +
                        "  \"isLanguage\": \"Y\",\n" +
                        "  \"isLocationSetup\": \"Y\",\n" +
                        "  \"isLogo\": \"Y\",\n" +
                        "  \"isParent\": \"Y\",\n" +
                        "  \"isTax\": \"Y\",\n" +
                        "  \"isUpr\": \"Y\",\n" +
                        "  \"levelCode\": \"L01\",\n" +
                        "  \"levelConfigId\" :"+x+",\n" +
                        "  \"levelDesc\": \"Level Desc Update\",\n" +
                        "  \"levelName\": \"Level Name Update\"\n" +
                        "}")
                .when()
                .put(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data updated successfully"));
    }
    @Test
    public void modifyInvalidLevelConfig() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
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
    public void getOneLevelConfig() throws IOException {
        int levelId = x;
        baseURL = getURL();
        baseURI = baseURL;

        Response response=
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getLevelConfigEndpoint,levelId)
                .then()
                .assertThat()
                .statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data response: " + jsonStr);
    }

    @Test (priority = 5)
    public void getBulkLevelConfigs() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getBulkLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data Bulk: " + jsonStr);

    }

    @Test(priority = 6)
    public void deleteLevelConfig() throws IOException {
        int levelId = x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .delete(deleteLevelConfigEndpoint,levelId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data deleted successfully"));
    }

}

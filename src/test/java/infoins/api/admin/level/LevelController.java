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
    private int x,z,w;
    String baseURL;
    String createLevelConfigEndpoint = "/level-configs";
    String getAllPaginationEndPoint = "/level-configs/all/pagination";
    String getLevelConfigEndpoint = "/level-configs/{id}";
    String deleteLevelConfigEndpoint = "/level-configs/{id}";

    @Test (priority = 1)
    public void getLastCreatedLevelNo() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "levelNo")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.levelNo").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.levelNo");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test (priority = 2)
    public void createLevelConfigValidTest() throws IOException {
        int y =x+1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"isCurrency\": true,\n" +
                        "  \"isFiscalYear\": true,\n" +
                        "  \"isLanguage\": true,\n" +
                        "  \"isLocationSetup\": true,\n" +
                        "  \"isLogo\": true,\n" +
                        "  \"isParent\": true,\n" +
                        "  \"isTax\": true,\n" +
                        "  \"isUpr\": true,\n" +
                        "  \"levelCode\": \"L"+y+"\",\n" +
                        "  \"levelDesc\": \"LevelDesc_"+y+"\",\n" +
                        "  \"levelName\": \"LevelName_"+y+"\"\n" +
                        "}")
                .when()
                .post(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }

    //Get last created levelConfigId and LevelNo
    @Test (priority = 3)
    public void getAllWithPagination() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
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

        //Get Last created LevelConfigId
        int size1 = response.jsonPath().getList("data.levelConfigId").size();
        System.out.println("Data Size: " + size1);
        List<Integer> ids = response.jsonPath().getList("data.levelConfigId");
        z= ids.get(size1-1);
        System.out.println("Last levelConfigId index:" +z);
        for (Integer i : ids) {
            System.out.print(i);
        }

        //Get Last created LevelNo
        int size2 = response.jsonPath().getList("data.levelNo").size();
        System.out.println("Data Size: " + size2);
        List<Integer> ids2 = response.jsonPath().getList("data.levelNo");
        w= ids2.get(size2-1);
        System.out.println("Last levelNo index:" +w);
        for (Integer i : ids2) {
            System.out.print(i);
        }
    }

    @Test(priority = 4)
    public void modifyLevelConfigValidTest() throws IOException {
        baseURL = getURL();
        String createLevelConfigEndpoint = "/level-configs";
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"isCurrency\": true,\n" +
                        "  \"isFiscalYear\": true,\n" +
                        "  \"isLanguage\": true,\n" +
                        "  \"isLocationSetup\": true,\n" +
                        "  \"isLogo\": true,\n" +
                        "  \"isParent\": true,\n" +
                        "  \"isTax\": true,\n" +
                        "  \"isUpr\": true,\n" +
                        "  \"levelCode\": \"LevelCodeUp_"+w+"\",\n" +
                        "  \"levelConfigId\": "+z+",\n" +
                        "  \"levelDesc\": \"LevelDescUp_"+w+"\",\n" +
                        "  \"levelName\": \"LevelName_"+w+"\"\n" +
                        "}")
                .when()
                .put(createLevelConfigEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data updated successfully"));
    }

    @Test(priority = 5)
    public void getOneLevelConfig() throws IOException {
        int levelConfigId = z;
        baseURL = getURL();
        baseURI = baseURL;

        Response response=
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getLevelConfigEndpoint,levelConfigId)
                .then()
                .assertThat()
                .statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data response: " + jsonStr);
    }

    @Test(priority = 6)
    public void deleteLevelConfig() throws IOException {
        int levelConfigId = z;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .delete(deleteLevelConfigEndpoint,levelConfigId)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .body("message",equalTo("Data deleted successfully"));
    }

}

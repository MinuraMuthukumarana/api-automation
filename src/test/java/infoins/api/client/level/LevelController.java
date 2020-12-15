package infoins.api.client.level;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class LevelController extends BaseClass {

    private int x,z,w;
    String baseURL;
    String createEndPoint ="/sales/levels";
    String updatedEndPoint= "/sales/levels";
    String getLevelIdEndPoint = "/sales/levels/{id}";
    String deleteLevelIdEndPoint="/sales/levels/{id}";
    String getAllWithPaginationEndPoint= "/sales/levels/all/pagination";
    String getBulkEndPoint="/sales/levels/bulk";

    //Get Last created LevelNo
    @Test(priority = 1)
    public void getLastCreatedLevelNo() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "levelNo")
                .when()
                .get(getAllWithPaginationEndPoint)
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

    //API to create a Level
    @Test(priority = 2)
    public void createLevelValidTest() throws IOException {
        int y =x+1;
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"levelDesc\": \"LevelDesc_"+y+"\",\n" +
                        "  \"levelName\": \"LevelName_"+y+"\",\n" +
                        "  \"levelNo\":"+y+"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Level "+y+" successfully created."));

    }
    //Verify with unique levelNo
    @Test
    public void createLevelInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"levelDesc\": \"LevelDesc_1\",\n" +
                        "  \"levelName\": \"LevelName_1\",\n" +
                        "  \"levelNo\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Level No should be unique"));
    }
    //Level No can be skip
    @Test
    public void createLevelInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"levelDesc\": \"LevelDesc_99\",\n" +
                        "  \"levelName\": \"LevelName_99\",\n" +
                        "  \"levelNo\": 99\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Level No cannot be skipped"));
    }

    //Get last created LevelId and LevelNo
    @Test(priority = 3)
    public void getLastCreatedLevelId() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "levelId")
                        .when()
                        .get(getAllWithPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        //Get last created LevelId
        int size1 = response.jsonPath().getList("data.levelId").size();
        System.out.println("Data Size: " + size1);
        List<Integer> ids1 = response.jsonPath().getList("data.levelId");
        z= ids1.get(size1-1);
        System.out.println("Last levelId index:" +z);
        for (Integer i : ids1) {
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
    public void updateLevelValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"levelDesc\": \"LevelDescUp_"+w+"\",\n" +
                        "  \"levelId\": "+z+",\n" +
                        "  \"levelName\": \"LevelNameUp_"+w+"\",\n" +
                        "  \"levelNo\": "+w+"\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    //Verify with invalid levelId and levelNo
    @Test
    public void updateLevelInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"levelDesc\": \"LevelDescUp_99\",\n" +
                        "  \"levelId\": -9 ,\n" +
                        "  \"levelName\": \"LevelNameUp_99\",\n" +
                        "  \"levelNo\": 99\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    //API to retrieve a Level by LevelId
    @Test(priority = 5)
    public void getLevelIdLevelValidTest() throws IOException {
        int id = z;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getLevelIdEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void getLevelIdLevelInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getLevelIdEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 6)
    public void getLevelBySearchKey() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("searchKey ", "levelName")
                        .queryParam("sortBy", "levelName")
                        .when()
                        .get(getAllWithPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }

    //API to delete a Level by LevelId
    @Test(priority = 7)
    public void deleteLevelValidTest() throws IOException{
        int id = z;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteLevelIdEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
    @Test
    public void deleteLevelInvalidTest() throws IOException{
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteLevelIdEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

}

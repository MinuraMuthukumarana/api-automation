package infoins.api.admin.language;

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
import static org.hamcrest.Matchers.equalTo;

/**
 * @author : Eranda Kodagoda
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class LanguageController extends BaseClass {

    private int x,y,z;
    String baseURL;
    String createEndPoint = "/app-languages";
    String modifyEndPoint = "/app-languages";
    String getOneEndPoint = "/app-languages/{id}";
    String deleteEndPoint = "/app-languages/{id}";
    String getBulkEndPoint = "/app-languages/bulk";
    String createMultipleEndPoint = "/app-languages/multiple";
    String getAllPaginationEndPoint = "/app-languages/all/pagination";
    String deleteAllEndpoint ="/app-languages/all/{ids}";

    @Test(priority = 1)
    public void createLanguageValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-language-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    //Verify duplicate records Id = 1
    @Test
    public void createLanguageInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-language-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Duplicate records found for the reference."));

    }

    @Test(priority = 2)
    public void getAllWithPaginationLanguageValidTest() throws IOException {
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
                        .queryParam("sortBy", "appLanguageId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.appLanguageId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.appLanguageId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }
    //Minus Value
    @Test
    public void getAllWithPaginationLanguageInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", -100)
                .queryParam("sortBy", "appLanguageId")
                .when()
                .get(getAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));

    }

    @Test(priority = 3)
    public void modifyLanguageValidLanguageTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appLanguageId\":"+x+",\n" +
                        "  \"languageId\": 3\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }
    @Test
    public void modifyLanguageInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appLanguageId\":162,\n" +
                        "  \"languageId\": 7\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Unable to find Language with id 7"));

    }

    @Test(priority = 4)
    public void getOneLanguageValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data response: " + jsonStr);

    }
    @Test
    public void getOneLanguageInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 5)
    public void getBulkLanguageValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getBulkEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data response: " + jsonStr);

    }

    @Test(priority = 6)
    public void deleteLanguageValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));

    }
    @Test
    public void deleteLanguageInvalidTest() throws IOException {
        int id = 25;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 7)
    public void createMultipleLanguageValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-language-multiple-valid.json"))
                .when()
                .post(createMultipleEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Reference Ids 4,6 saved successfully."));

    }
    @Test
    public void createMultipleLanguageInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-language-multiple-invalid.json"))
                .when()
                .post(createMultipleEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("System cannot find result for (SyDrLanguage)"));

    }

    @Test(priority = 8)
    public void getAllLanguageAgain() throws IOException {
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
                        .queryParam("sortBy", "appLanguageId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.appLanguageId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.appLanguageId");
        y= ids.get(size-1);
        System.out.println("Last n index:" +y);
        z= ids.get(size-2);
        System.out.println("Last n-1 index:" +z);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 9)
    public void deleteMultipleValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        String ids = ""+y+","+z+"";
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .when()
                .delete(deleteAllEndpoint, ids)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Ids "+y+","+z+" deleted successfully."));
    }

}

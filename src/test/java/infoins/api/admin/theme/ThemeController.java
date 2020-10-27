package infoins.api.admin.theme;

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
 *  * @date : August 11, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class ThemeController extends BaseClass {
    private int x;
    String baseURL;
    String createThemeEndpoint = "/app-themes";
    String getBulkEndpoint = "/app-themes/bulk";
    String modifyThemeEndpoint = "/app-themes";
    String getOneEndpoint = "/app-themes/{id}";
    String getAllPaginationEndPoint = "/app-themes/all/pagination";
    String deleteOneEndpoint = "/app-themes/{id}";
    String createMultipleThemeEndpoint = "/app-themes/multiple";
    String createThemesEndpoint = "/app-themes";
    String getAllConfigThemesWithChildren="/app-themes/config-theme/all/children";
    String getAllConfigThemeParents="/app-themes/config-theme/all/parent";
    String getOneConfigThemeWithChildren="/app-themes/config-theme/{id}/children";
    String getOneConfigThemeParent="/app-themes/config-theme/{id}/parent";
    String deleteBulkEndpoint = "/app-themes/all/{ids}";

    @BeforeTest
    void setUp() throws Exception {
        getBearerToken("admin-service","a7eb9158-9fa3-4e00-8958-6e4660154027");
    }
    @Test(priority = 1)
    public void createThemeConfig() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-success.json"))
                .when()
                .post(createThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }
    @Test
    public void createInvalidTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-invalid.json"))
                .when()
                .post(createThemesEndpoint)
                .then()
                .assertThat().statusCode(400);
//                .and()
//                .body("message",equalTo("Data added successfully"));
    }

    @Test(priority = 2)
    public void getAllWithPagination() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "appThemeId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.appThemeId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.appThemeId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void modifyTheme() throws IOException {
        baseURL = getURL();
        String modifyThemeEndpoint = "/app-themes";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appThemeId\": "+x+",\n" +
                        "  \"branchCode\": \"BC04\",\n" +
                        "  \"endDate\": \"2022-09-10\",\n" +
                        "  \"stDate\": \"2020-09-10\",\n" +
                        "  \"themeId\": 4\n" +
                        "\n" +
                        "}")
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message",equalTo("Data updated successfully"));
    }
    @Test
    public void modifyInvalidTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-theme-invalid.json"))
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(400);
    }

    @Test(priority = 4)
    public void getOneTheme() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getOneEndpoint,id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOneData Set: "+ jsonStr);

    }

    @Test(priority = 5)
    public void getBulk() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getBulkEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetBulkData List: " + jsonStr);
    }

    @Test(priority = 6)
    public void deleteOneTheme() throws IOException {
        int id =x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .delete(deleteOneEndpoint,id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
    @Test
    public void deleteInvalidTheme() throws IOException {
        String id ="-190";
        String deleteOneEndpoint = "/app-themes/{id}";
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .delete(deleteOneEndpoint,id)
                .then()
                .assertThat()
                .statusCode(400);
//                .and()
//                .contentType(ContentType.JSON)
//                .and()
//                .body("message", equalTo("Data deleted successfully"));
    }

    @Test(priority = 7)
    public void getOneConfigThemeWithChildren() throws IOException {
        int Id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneConfigThemeWithChildren, Id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("getOneConfigThemeWithChildren Data List: " + jsonStr);

    }

    @Test(priority = 8)
    public void getOneConfigThemeParent() throws IOException {
        int Id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneConfigThemeParent, Id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("getOneConfigThemeParent Data List: " + jsonStr);
    }

    @Test(priority = 9)
    public void getAllConfigThemesWithChildrenTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getAllConfigThemesWithChildren)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Child Theme Data List: " + jsonStr);
    }

    @Test(priority = 10)
    public void getAllConfigThemeParentsTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getAllConfigThemeParents)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Parent Theme Data List: " + jsonStr);
    }

    @Test(priority = 11)
    public void createMultipleTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-theme-success.json"))
                .when()
                .post(createMultipleThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }

//    @Test(priority = 8)
//    public void deleteBulkTheme() throws IOException {
//        String idList = "1,2,3";
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept","*/*")
//                .header("authorization",getBearerToken())
//                .when()
//                .delete(deleteBulkEndpoint,idList)
//                .then()
//                .assertThat()
//                .statusCode(200)
//                .and()
//                .contentType(ContentType.JSON)
//                .and()
//                .body("message", equalTo("Data deleted successfully"));
//    }


}

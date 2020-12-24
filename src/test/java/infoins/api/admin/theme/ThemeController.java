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
    private int x,w,y,z;
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


    @Test(priority = 1)
    public void createThemeValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-success.json"))
                .when()
                .post(createThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }
    //Verify with null
    @Test
    public void createThemeConfigInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-invalid1.json"))
                .when()
                .post(createThemesEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description",equalTo("The given id must not be null!"));
    }
    //Verify with Start Date can be greater than End Date
    @Test
    public void createThemeConfigInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-invalid2.json"))
                .when()
                .post(createThemesEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description",equalTo("Start Date cannot be greater than End Date"));
    }

    @Test(priority = 2)
    public void getAllWithPaginationTheme() throws IOException {
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
    public void modifyThemeValidTest() throws IOException {
        baseURL = getURL();
        String modifyThemeEndpoint = "/app-themes";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appThemeId\": "+x+",\n" +
                        "  \"endDate\": \"2020-12-31\",\n" +
                        "  \"stDate\": \"2020-01-01\",\n" +
                        "  \"themeId\": 1\n" +
                        "}")
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message",equalTo("Data updated successfully"));
    }
    //Verify with Start Date can be greater than End Date
    @Test
    public void modifyThemeInvalidTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appThemeId\": "+x+",\n" +
                        "  \"endDate\": \"2020-10-31\",\n" +
                        "  \"stDate\": \"2020-12-01\",\n" +
                        "  \"themeId\": 1\n" +
                        "}")
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description",equalTo("Start Date cannot be greater than End Date"));
    }

    @Test(priority = 4)
    public void getOneThemeValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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
    @Test
    public void getOneThemeInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndpoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 5)
    public void getBulkTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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
    public void deleteOneThemeValidTest() throws IOException {
        int id =x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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
    public void deleteInvalidThemeInvalidTest() throws IOException {
        int id =-1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .when()
                .delete(deleteOneEndpoint,id)
                .then()
                .assertThat()
                .statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

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
                .header("CountryId", 1)
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
                .header("CountryId", 1)
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
                .header("CountryId", 1)
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
                        .header("CountryId", 1)
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
    public void createMultipleThemeValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-theme-success.json"))
                .when()
                .post(createMultipleThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }
    @Test
    public void createMultipleThemeInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-theme-invalid1.json"))
                .when()
                .post(createMultipleThemeEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description",equalTo("Start Date cannot be greater than End Date"));
    }
    @Test
    public void createMultipleThemeInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-theme-invalid2.json"))
                .when()
                .post(createMultipleThemeEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description",equalTo("System cannot find result for (SyDcTheme)"));
    }

    @Test(priority = 12)
    public void getAllWithPaginationAgainTheme() throws IOException {
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
        w= ids.get(size-1);
        System.out.println("Last n index:" +w);
        y= ids.get(size-2);
        System.out.println("Last n-1 index:" +y);
        z= ids.get(size-3);
        System.out.println("Last n-2 index:" +z);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 13)
    public void deleteBulkThemeValid() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        String ids = ""+w+","+y+","+z+"";
        given()
                .header("accept","*/*")
                .header("authorization",AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .when()
                .delete(deleteBulkEndpoint,ids)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Ids "+w+","+y+","+z+" deleted successfully."));
    }


}

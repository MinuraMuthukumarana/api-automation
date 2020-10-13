package infoins.api.admin.theme;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

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
    String baseURL;
    String createThemeEndpoint = "/app-themes";
    String getBulkEndpoint = "/app-themes/bulk";
    String modifyThemeEndpoint = "/app-themes";
    String getOneEndpoint = "/app-themes/{id}";
    String deleteOneEndpoint = "/app-themes/{id}";
    String createMultipleThemeEndpoint = "/app-themes/multiple";
    String deleteBulkEndpoint = "/app-themes/all/{ids}";
    String createThemesEndpoint = "/app-themes";

    @Test(priority = 1)
    public void createThemeConfig() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-success.json"))
                .when()
                .post(createThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }

    @Test(priority = 2)
    public void getBulk() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .get(getBulkEndpoint)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("[0].appThemeId", equalTo(1));
    }

    @Test(priority = 3)
    public void modifyInvalidTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-theme-invalid.json"))
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(400);
//                .and()
//                .body("message",equalTo("Data updated successfully"));
    }

    @Test(priority = 4)
    public void modifyTheme() throws IOException {
        baseURL = getURL();
        String modifyThemeEndpoint = "/app-themes";
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-theme-success.json"))
                .when()
                .put(modifyThemeEndpoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message",equalTo("Data updated successfully"));
    }

    @Test(priority = 5)
    public void getOneTheme() throws IOException {
        String id = "1";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .get(getOneEndpoint,id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("taxAmount", equalTo(60));
    }

    @Test(priority = 6)
    public void deleteOneTheme() throws IOException {
        String id ="1";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
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

    @Test(priority = 7)
    public void createMultipleTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-theme-success.json"))
                .when()
                .post(createMultipleThemeEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message",equalTo("Data added successfully"));
    }

    @Test(priority = 8)
    public void deleteBulkTheme() throws IOException {
        String idList = "1,2,3";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .when()
                .delete(deleteBulkEndpoint,idList)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }

    @Test(priority = 9)
    public void createInvalidTheme() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-theme-invalid.json"))
                .when()
                .post(createThemesEndpoint)
                .then()
                .assertThat().statusCode(400);
//                .and()
//                .body("message",equalTo("Data added successfully"));
    }

    @Test(priority = 10)
    public void deleteInvalidTheme() throws IOException {
        String id ="-190";
        String deleteOneEndpoint = "/app-themes/{id}";
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept","*/*")
                .header("authorization",getBearerToken())
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
}

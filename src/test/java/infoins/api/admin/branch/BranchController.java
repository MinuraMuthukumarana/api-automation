package infoins.api.admin.branch;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * @author : Eranda Kodagoda
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class BranchController extends BaseClass {

    String baseURL;
    String addNewBranchEndPoint = "/branches";
    String updateBranchEndPoint = "/branches";
    String getAllBranchTaxesByBranchIdEndPoint = "branches/{branchId}";
    String deleteBranchEndPoint = "/branches/{branchId}";
    String setActiveStatusEndPoint = "/branches/active-status/{status}/{branchId}";
    String getAllEndPoint = "/branches/all";
    String deleteAllEndPoint = "/branches/all";

    @Test(priority = 1)
    public void addNewBranchValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("add-new-branch-valid.json"))
                .when()
                .post(addNewBranchEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].branchCode", equalTo("br16"));

    }

    @Test
    public void addNewBranchInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("add-new-branch-invalid.json"))
                .when()
                .post(addNewBranchEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void updateBranchValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("update-branch-valid.json"))
                .when()
                .put(updateBranchEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].abrvName", equalTo("abName16"));

    }

    @Test
    public void updateBranchInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("update-branch-invalid.json"))
                .when()
                .put(updateBranchEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 3)
    public void getAllBranchTaxesByBranchIdValidTest() throws IOException {

        int branchId = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllBranchTaxesByBranchIdEndPoint, branchId)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("taxAmount", equalTo("12"));

    }

    @Test
    public void getAllBranchTaxesByBranchIdInvalidTest() throws IOException {

        String branchId = "id";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllBranchTaxesByBranchIdEndPoint, branchId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void deleteBranchValidTest() throws IOException {

        int branchId = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteBranchEndPoint, branchId)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void deleteBranchInvalidTest() throws IOException {

        String branchId = "id";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteBranchEndPoint, branchId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 5)
    public void setActiveStatusValidTest() throws IOException {

        int branchId = 1;
        String status = "y";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .post(setActiveStatusEndPoint, status, branchId)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void setActiveStatusInvalidBranchIdTest() throws IOException {

        String branchId = "id";
        String status = "y";
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .post(setActiveStatusEndPoint, status, branchId)
                .then()
                .assertThat().statusCode(400)
                .body("error", equalTo("Bad Request"));

    }

    @Test
    public void setActiveStatusInvalidStatusTest() throws IOException {

        int branchId = 1;
        int status = 1;
        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .post(setActiveStatusEndPoint, status, branchId)
                .then()
                .assertThat().statusCode(400)
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 6)
    public void getAllValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getAllEndPoint)
                .then()
                .assertThat().statusCode(200);

    }

    @Test(priority = 7)
    public void deleteAllValidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("delete-all-branch-valid.json"))
                .when()
                .delete(deleteAllEndPoint)
                .then()
                .assertThat().statusCode(200);

    }

    @Test
    public void deleteAllInvalidTest() throws IOException {

        baseURL = getURL();

        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("delete-all-branch-invalid.json"))
                .when()
                .delete(deleteAllEndPoint)
                .then()
                .assertThat().statusCode(400)
                .body("error", equalTo("Bad Request"));

    }
}

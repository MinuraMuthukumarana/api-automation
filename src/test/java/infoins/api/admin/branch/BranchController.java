package infoins.api.admin.branch;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

/**
 * @author : Eranda Kodagoda, Minura Muthukumarana
 * * Added new meths for new json input
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class
BranchController extends BaseClass {

    private int x;
    String baseURL;
    String addNewBranchEndPoint = "/branches";
    String updateBranchEndPoint = "/branches";
    String getAllWithPaginationEndPoint = "/branches/all/pagination";
    String getOneEndPoint = "/branches/{id}";
    String getCountryByBranchIdEndPoint ="/branches/branchId/{branchId}";
    String getChangeActiveStatusEndPoint="/branches/changeActiveStatus/{branchId}";
    String getAllBranchMappersEndPoint = "/branches/getAllBranchMappers";
    String deleteBranchEndPoint = "/branches/{branchId}";

    @BeforeTest
    void setUp() throws Exception {
        getBearerToken("admin-service","a7eb9158-9fa3-4e00-8958-6e4660154027");
    }

    @Test(priority = 1)
    public void addNewBranchValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"add-new-branch-valid.json"))
                .when()
                .post(addNewBranchEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    @Test
    public void addNewBranchInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"add-new-branch-invalid.json"))
                .when()
                .post(addNewBranchEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void getAllWithPaginationValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.branchId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.branchId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

//    @Test(priority = 3)
//    public void updateBranchValidTest() throws IOException {
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .body(getGeneratedString("\\admin\\"+"update-branch-valid.json"))
//                .when()
//                .put(updateBranchEndPoint)
//                .then()
//                .assertThat().statusCode(200)
//                .and()
//                .body("message", equalTo("Data updated successfully"));
//
//    }
//    @Test
//    public void updateBranchInvalidTest() throws IOException {
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .body(getGeneratedString("\\admin\\"+"update-branch-invalid.json"))
//                .when()
//                .put(updateBranchEndPoint)
//                .then()
//                .assertThat().statusCode(400)
//                .and()
//                .body("error", equalTo("Bad Request"));
//
//    }

    @Test(priority = 4)
    public void getOneValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getOneEndPoint, id)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Data List: " + jsonStr);
    }

    @Test(priority = 5)
    public void getCountryByBranchIdValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getCountryByBranchIdEndPoint, id)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetCountryByBranchId Data List: " + jsonStr);
    }

    @Test(priority = 6)
    public void getChangeActiveStatusValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getChangeActiveStatusEndPoint, id)
                        .then()
                        .assertThat().statusCode(200)
                        .and()
                        .body("message", equalTo("Data updated successfully"));

    }

    @Test(priority = 7)
    public void getAllBranchMappersValidTest() throws IOException {
        int branchId = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getAllBranchMappersEndPoint, branchId)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("getAllBranchMappers Data List: " + jsonStr);

    }

    @Test(priority = 8)
    public void deleteBranchValidTest() throws IOException {
        int branchId = x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteBranchEndPoint, branchId)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));

    }
    @Test
    public void deleteBranchInvalidTest() throws IOException {
        String branchId = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteBranchEndPoint, branchId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

}

package infoins.api.admin.globaltax;

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
 * @author : Eranda Kodagoda, Minura Muthukumarana
 * * Modify
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class GlobalTaxController extends BaseClass {
    public int x,y,z;
    String baseURL;
    String createGlobalTaxEndpoint = "/global-taxes";
    String getBulkEndpoint = "/global-taxes/bulk";
    String modifyGlobalTaxEndpoint = "/global-taxes";
    String getOneEndpoint = "/global-taxes/{id}";
    String deleteOneEndpoint = "/global-taxes/{id}";
    String getAllPaginationEndPoint="/global-taxes/all/pagination";
    String createMultipleGlobalTaxEndpoint = "/global-taxes/multiple";
    String deleteAllEndpoint = "/global-taxes/all/{ids}";

    @Test(priority = 1)
    public void createGlobalTaxValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-global-tax-success.json"))
                .when()
                .post(createGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    //StartDate grater than EndDate
    @Test
    public void createGlobalTaxInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-global-tax-invalid1.json"))
                .when()
                .post(createGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }
    //TaxAmount and TaxRate
    @Test
    public void createGlobalTaxInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-global-tax-invalid2.json"))
                .when()
                .post(createGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }
    //Invalid Date format
    @Test
    public void createGlobalTaxInvalidTest3() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-global-tax-invalid3.json"))
                .when()
                .post(createGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
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
                        .queryParam("sortBy", "globalTaxId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
        int size = response.jsonPath().getList("data.globalTaxId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.globalTaxId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void modifyGlobalTax() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"endDate\": \"2022-09-14\",\n" +
                        "  \"globalTaxId\": "+x+",\n" +
                        "  \"stDate\": \"2022-09-14\",\n" +
                        "  \"taxAmount\": 0,\n" +
                        "  \"taxRate\": 50,\n" +
                        "  \"taxTypeId\":1\n" +
                        "}")
                .when()
                .put(modifyGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    @Test(priority = 4)
    public void getOneGlobalTax() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .get(getOneEndpoint, id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Data List: " + jsonStr);
    }

    @Test(priority = 5)
    public void deleteOneGlobalTax() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .when()
                .delete(deleteOneEndpoint, id)
                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }

    @Test(priority = 6)
    public void getBulk() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
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
        System.out.println("Data List: " + jsonStr);

    }

    @Test(priority = 7)
    public void createMultipleGlobalTax() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-multiple-global-tax-success.json"))
                .when()
                .post(createMultipleGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }

    @Test(priority = 8)
    public void getAllWithPaginationAgain() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "globalTaxId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.globalTaxId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.globalTaxId");
        y= ids.get(size-1);
        System.out.println("Last n index:" +y);
        z= ids.get(size-2);
        System.out.println("Last n-1 index:" +z);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 9)
    public void deleteBulkGlobalTax() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        String ids = ""+y+","+z+"";
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
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

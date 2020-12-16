package infoins.api.client.commissionStructure;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommissionStructureController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint = "/sales/structure";
    String updatedEndPoint= "/sales/structure";
    String getViewOneEndPoint = "/sales/structure/{id}";
    String deleteEndPoint="/sales/structure/{id}";
    String getPaginationEndPoint= "/sales/structure/all/pagination";
    String getViewHistoryEndPoint="/sales/structure/history/{commStrucId}";
    String getCommStrucIdEndPoint = "/sales/structure/view/{commStrucId}";
    String getViewAllEndPoint = "/sales/structure/view/all";
    String getAllWithPaginationEndPoint = "/sales/structure/view/viewAllWithPagination";

    //API to create a Commission Structure
    @Test(priority = 1)
    public void createCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                //SalesChannelId 2,3,5 //LeveId 1,7,41
                .body("{\n" +
                        "  \"chanlId\": 2,\n" +
                        "  \"commStrucDtls\": [\n" +
                        "    {\n" +
                        "      \"chargeTypeId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"commAmount\": 1000,\n" +
                        "      \"commPct\": 25.0,\n" +
                        "      \"commRate\": 0,\n" +
                        "      \"commTypeId\": 1,\n" +
                        "      \"fromDate\": \"2020-09-21\",\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"othChrgId\": 1,\n" +
                        "      \"prodId\": 2,\n" +
                        "      \"taxTypeId\": 1,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"isSourceDebtor\": true,\n" +
                        "  \"levelId\": 7,\n" +
                        "  \"strucCode\": \"COMSTRUC01\",\n" +
                        "  \"strucName\": \"CommStructure channelId2 and levelId7\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Structure COMSTRUC01 successfully created.Data added successfully"));
    }
    //Duplicate commission structure
    @Test
    public void createCommissionStructureInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                //SalesChannelId 2 //LeveId 1
                .body("{\n" +
                        "  \"chanlId\": 2,\n" +
                        "  \"commStrucDtls\": [\n" +
                        "    {\n" +
                        "      \"chargeTypeId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"commAmount\": 1000,\n" +
                        "      \"commPct\": 25.0,\n" +
                        "      \"commRate\": 0,\n" +
                        "      \"commTypeId\": 1,\n" +
                        "      \"fromDate\": \"2020-09-21\",\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"othChrgId\": 1,\n" +
                        "      \"prodId\": 2,\n" +
                        "      \"taxTypeId\": 1,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"isSourceDebtor\": true,\n" +
                        "  \"levelId\": 1,\n" +
                        "  \"strucCode\": \"COMSTRUC01\",\n" +
                        "  \"strucName\": \"CommStruc1\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Duplicate commission structure"));
    }
    //Either Commission Percentage or Rate
    @Test
    public void createCommissionStructureInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                //SalesChannelId 2 //LeveId 7
                .body("{\n" +
                        "  \"chanlId\": 2,\n" +
                        "  \"commStrucDtls\": [\n" +
                        "    {\n" +
                        "      \"chargeTypeId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"commAmount\": 1000,\n" +
                        "      \"commPct\": 25.0,\n" +
                        "      \"commRate\": 10,\n" +
                        "      \"commTypeId\": 1,\n" +
                        "      \"fromDate\": \"2020-09-21\",\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"othChrgId\": 1,\n" +
                        "      \"prodId\": 2,\n" +
                        "      \"taxTypeId\": 1,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"isSourceDebtor\": true,\n" +
                        "  \"levelId\": 1,\n" +
                        "  \"strucCode\": \"COMSTRUC01\",\n" +
                        "  \"strucName\": \"CommStruc1\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Please enter either Commission Percentage or Rate"));
    }

    //API to retrieve all Commission Structures with Pagination
    @Test(priority = 2)
    public void getPaginationCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "commStrucId")
                        .when()
                        .get(getPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.commStrucId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.commStrucId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    //API to update an existing Commission Structure
    @Test(priority = 3)
    public void updateCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"commStrucDtls\": [\n" +
                        "    {\n" +
                        "      \"chargeTypeId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"commAmount\": 10000,\n" +
                        "      \"commPct\": 45.0,\n" +
                        "      \"commRate\": 0,\n" +
                        "      \"commStrucDtlId\": 1,\n" +
                        "      \"commTypeId\": 1,\n" +
                        "      \"fromDate\": \"2020-08-21\",\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"othChrgId\": 1,\n" +
                        "      \"prodId\": 2,\n" +
                        "      \"taxTypeId\": 1,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"commStrucId\": "+x+",\n" +
                        "  \"isSourceDebtor\": false,\n" +
                        "  \"isUsed\": false\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void updateCommissionStructureInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"commStrucDtls\": [\n" +
                        "    {\n" +
                        "      \"chargeTypeId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"commAmount\": 10000,\n" +
                        "      \"commPct\": 45.0,\n" +
                        "      \"commRate\": 10,\n" +
                        "      \"commStrucDtlId\": 1,\n" +
                        "      \"commTypeId\": 1,\n" +
                        "      \"fromDate\": \"2020-08-21\",\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"othChrgId\": 1,\n" +
                        "      \"prodId\": 2,\n" +
                        "      \"taxTypeId\": 1,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"commStrucId\": 33,\n" +
                        "  \"isSourceDebtor\": false,\n" +
                        "  \"isUsed\": false\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Please enter either Commission Percentage or Rate"));
    }

    //API to retrieve a Commission Structure by CommissionStructureId
    @Test(priority = 4)
    public void getOneIdCommissionStructureValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void getOneIdCommissionStructureInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    //viewHistory
    @Test(priority = 5)
    public void getViewHistoryCommissionStructureValidTest() throws IOException {
        int commStrucId = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewHistoryEndPoint,commStrucId)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void getViewHistoryCommissionStructureInvalidTest() throws IOException {
        int commStrucId = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewHistoryEndPoint, commStrucId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    //API to view a Commission Structure by CommissionStructureId
    @Test(priority = 6)
    public void getCommStrucIdCommissionStructureValidTest() throws IOException {
        int commStrucId = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getCommStrucIdEndPoint, commStrucId)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void getCommStrucIdCommissionStructureInvalidTest() throws IOException {
        int commStrucId = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getCommStrucIdEndPoint, commStrucId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    //API to view all Commission Structures
    @Test(priority = 8)
    public void viewAllCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }

    //API to view all Commission Structures with pagination
    @Test(priority = 9)
    public void getAllWithPaginationCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "commStrucId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void getAllWithPaginationCommissionStructureInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "commStrucId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    //API to delete a Commission Structure by CommissionStructureId
    @Test(priority = 10)
    public void deleteCommissionStructureValidTest() throws IOException{
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
    @Test
    public void deleteCommissionStructureInvalidTest() throws IOException{
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }
}

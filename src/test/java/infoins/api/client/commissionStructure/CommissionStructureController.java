package infoins.api.client.commissionStructure;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class CommissionStructureController extends BaseClass {
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
                .body(getGeneratedString("\\umbrella\\"+"commission-create-commission-structure-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    @Test
    public void createCommissionStructureInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"commission-create-commission-structure-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to update an existing Commission Structure
    @Test(priority = 2)
    public void updateCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\umbrella\\"+"commission-modify-commission-structure-valid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void updateCommissionStructureInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\umbrella\\"+"commission-modify-commission-structure-valid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to retrieve a Commission Structure by CommissionStructureId
    @Test(priority = 3)
    public void getOneIdCommissionStructureValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, id)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getOneIdCommissionStructureInvalidTest() throws IOException {
        String clientId = "Id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    //API to delete a Commission Structure by CommissionStructureId
    @Test(priority = 4)
    public void deleteCommissionStructureValidTest() throws IOException{
        int id = 1;
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
        String id = "id";
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
                .body("error", equalTo("Bad Request"));
    }

    //API to retrieve all Commission Structures with Pagination
    @Test(priority = 5)
    public void getPaginationCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getPaginationCommissionStructureInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 5)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //viewHistory
    @Test(priority = 6)
    public void getViewHistoryCommissionStructureValidTest() throws IOException {
        int commStrucId = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewHistoryEndPoint, commStrucId)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getViewHistoryCommissionStructureInvalidTest() throws IOException {
        String commStrucId = "Id";
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
                .body("error", equalTo("Bad Request"));
    }

    //API to view a Commission Structure by CommissionStructureId
    @Test(priority = 7)
    public void getCommStrucIdCommissionStructureValidTest() throws IOException {
        int commStrucId = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getCommStrucIdEndPoint, commStrucId)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getCommStrucIdCommissionStructureInvalidTest() throws IOException {
        String commStrucId = "Id";
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
                .body("error", equalTo("Bad Request"));
    }

    //API to view all Commission Structures
    @Test(priority = 8)
    public void viewAllCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(200);
    }

    //API to view all Commission Structures with pagination
    @Test(priority = 9)
    public void getAllWithPaginationCommissionStructureValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithPaginationCommissionStructureInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 5)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }
}

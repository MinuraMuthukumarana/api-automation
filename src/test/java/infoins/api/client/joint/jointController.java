package infoins.api.client.joint;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author : Minura Muthukumarana
 *  * @date : September 22, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class jointController extends BaseClass {
    String baseURL;
    String createEndPoint ="/cm/joint";
    String updateEndPoint ="/cm/joint";
    String getOneEndPoint ="/cm/joint/{id}";
    String getAllPaginationEndPoint= "/cm/joint/all/pagination";
    String getFilterAllPaginationEndPoint ="/cm/joint/all/pagination/filter";
    String getViewOneEndPoint = "/cm/joint/view/{clientId}";
    String getViewAllEndPoint ="/cm/joint/view/all";
    String deleteEndPoint = "";

    @Test(priority = 1)
    public void  createJointValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"joint-create-joint-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    @Test
    public void  createJointInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"joint-create-joint-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void  updateJointValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"joint-update-joint-valid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void  updateJointInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"joint-update-joint-invalid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    @Test(priority = 3)
    public void getOneJointValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getOneJointInvalidTest() throws IOException {
        String id = "Id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 4)
    public void getAllWithPaginationJointValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithPaginationJointInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "InvalidbranchId")
                .when()
                .get(getAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 5)
    public void getFilterAllWithPaginationJointValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("filter", 1)
                .queryParam("filterBy", 10)
                .queryParam("pageNo", 1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getFilterAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getFilterAllWithPaginationJointInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("filter", 1)
                .queryParam("filterBy", 10)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "InvalidbranchId")
                .when()
                .get(getFilterAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 6)
    public void getViewOneJointValidTest() throws IOException{
        int clientId = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(200);

    }
    @Test
    public void getViewOneJointInvalidTest() throws IOException{
        int clientId = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 7)
    public void getViewAllJointValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("[0].name", equalTo("email-template1"));
    }

    @Test(priority = 8)
    public void deleteJointValidTest() throws IOException{
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));

    }
    @Test
    public void deleteJointInvalidTest() throws IOException{
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
}

package infoins.api.client.globalClient;

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
public class globalClientController extends BaseClass {
    String baseURL;
    String getOneEndPoint ="/cm/global/{id}";
    String getAllWithPaginationEndPoint="/cm/global/all/pagination";
    String getViewOneEndPoint ="/cm/global/view/{clientId}";
    String getViewAllEndPoint = "/cm/global/view/all";
    String deleteEndPoint ="/cm/global/{id}";

    @Test(priority = 1)
    public void getOneGlobalClientControllerValidTest() throws IOException{
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
    public void getOneGlobalClientControllerInvalidTest() throws IOException{
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

    @Test(priority = 2)
    public void getAllWithPaginationGlobalClientControllerValidTest() throws IOException{
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
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithPaginationGlobalClientControllerInvalidTest() throws IOException{
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
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 3)
    public void viewOneGlobalClientControllerValidTest() throws IOException{
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
    public void viewOneGlobalClientControllerInvalidTest() throws IOException{
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

    @Test(priority = 4)
    public void viewAllGlobalClientControllerValidTest() throws IOException{
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

    @Test(priority = 5)
    public void deleteGlobalClientControllerValidTest() throws IOException{
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
    public void deleteGlobalClientControllerInvalidTest() throws IOException{
        String id = "Id";
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

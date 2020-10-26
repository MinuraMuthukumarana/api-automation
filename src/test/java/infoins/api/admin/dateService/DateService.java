package infoins.api.admin.dateService;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author : Minura Muthukumarana
 *  * @date : October 26, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class DateService extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint= "/";
    String getAllPaginationEndPoint="/all/pagination";
    String modifyEndPoint="/";
    String getOneEndPoint= "/{id}";
    String getBulkEndPoint ="/bulk";
    String deleteEndPoint = "/{id}";

    @Test(priority = 1)
    public void createDataServiceValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-date-service-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    @Test
    public void createDateServiceInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-date-service-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void getAllPaginationDataServiceValidInput() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", getBearerToken())
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "dateConfigId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.dateConfigId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.dateConfigId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void modifyDateServiceValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"dateConfigId\": "+x+",\n" +
                        "  \"dateValue\": \"dd-mm-yyyy\",\n" +
                        "  \"keyArea\": \"Global date format Simple Update\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }
    @Test
    public void modifyDateServiceInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-date-invalid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void getOneDateServiceValidTest() throws IOException {
        int Id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", getBearerToken())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getOneEndPoint, Id)
                        .then()
                        .assertThat().statusCode(200)
                        .and()
                        .extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Data set:" + jsonStr);


    }
    @Test
    public void getOneInvalidTest() throws IOException {
        int Id = 0;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, Id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 5)
    public void getBulkDateServiceTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", getBearerToken())
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getBulkEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetBulk Data List: " + jsonStr);
    }

    @Test(priority = 6)
    public void deleteDataServiceValidTest() throws IOException {
        int id = x;
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


}


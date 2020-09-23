package infoins.api.admin.date;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author : Minura Muthukumarana
 *  * @date : September 18, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class DateController extends BaseClass {
    String baseURL;
    String updateEndPoint="/date-configs";
    String getOneEndPoint="/date-configs/{id}";
    String getAllWithPaginationEndPoint="/date-configs/all/pagination";
    String getBulkEndPoint="date-configs/bulk";
  //String findByDynamicColumnsAndMultipleSort = "/date-configs/findByDynamicColumnsAndMultipleSort";

    @Test(priority = 1)
    public void updateDateControllerValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-date-valid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void updateDateControllerInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-date-invalid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 2)
    public void getOneValidTest() throws IOException {
        int Id = 3;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, Id)
                .then()
                .assertThat().statusCode(200);


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

    @Test(priority = 3)
    public void getAllWithPaginationValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "dateConfigId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithPaginationInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "invalidTestId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 4)
    public void getBulkValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getBulkEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("data[1].dateConfigId", equalTo(2));
    }
    @Test
    public void getBulkInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                 .header("accept", "*/*")
                 .header("authorization", getBearerToken())
                 .contentType(ContentType.JSON)
                 .when()
                 .get(getBulkEndPoint)
                 .then()
                 .assertThat().statusCode(200)
                 .and()
                 .body("data[1].dateConfigId", equalTo("Invalid"));

    }

//    @Test(priority = 5)
//    public void findByDynamicColumnsAndMultipleSortValidTest() throws IOException{
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept", "*/*")
//                .header("authorization", getBearerToken())
//                .contentType(ContentType.JSON)
//                .queryParam("pageNo", 0)
//                .queryParam("pageSize", 10)
//                .body(getGeneratedString("common_null_field.json"))
//                .queryParam("sortBy", "dateConfigId")
//                .queryParam("sortType", "DESC")
//                .when()
//                .get(findByDynamicColumnsAndMultipleSort)
//                .then()
//                .assertThat().statusCode(200);;
//
//    }
}

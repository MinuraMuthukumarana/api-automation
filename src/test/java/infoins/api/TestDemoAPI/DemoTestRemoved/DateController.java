package infoins.api.TestDemoAPI.DemoTestRemoved;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import infoins.ExcelDataReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

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
    private int x;
    String baseURL;
    String updateEndPoint="/date-configs";
    String getOneEndPoint="/date-configs/{id}";
    String getAllWithPaginationEndPoint="/date-configs/all/pagination";
    String getBulkEndPoint="date-configs/bulk";

    @Test(priority = 1)
    public void getAllWithPaginationDateValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "dateConfigId")
                        .when()
                        .get(getAllWithPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and()
                        .extract().response();

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

    @Test(priority = 2)
    public void updateDateValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"dateConfigId\": "+x+",\n" +
                        "  \"dateValue\": \"DD-MM-YYYY\",\n" +
                        "  \"keyArea\": \"Global date format Capital Update\"\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    @Test(priority = 3)
    public void getOneDateValidTest() throws IOException {
        int Id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, Id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Data set:" + jsonStr);

    }
    //Minus value
    @Test
    public void getOneDateInvalidTest1() throws IOException {
        int Id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, Id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 4)
    public void getBulkDateValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getBulkEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetBulk Data List: " + jsonStr);
    }

    @DataProvider(name = "UpdateDataControllerInvalid")
    @Parameters({"DataController"})
    public Iterator<Object[]> getApiTestData(ITestContext context) throws IOException {

        String dataFile = context.getCurrentXmlTest().getParameter("DataController");
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(0,"\\AdminExcel\\DateController.xlsx");
        return iterator;
    }
    @Test(dataProvider = "UpdateDataControllerInvalid")
    public void UpdateDateControllerInvalid(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(schema)
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("error_description", equalTo(message));
    }

}

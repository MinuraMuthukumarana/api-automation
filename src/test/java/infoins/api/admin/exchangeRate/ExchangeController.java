package infoins.api.admin.exchangeRate;

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
import static org.hamcrest.Matchers.equalTo;
/**
 * @author : Minura Muthukumarana
 *  * @date : September 18, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class ExchangeController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint ="/exchange-rates";
    String updateEndPoint="/exchange-rates";
    String getOneEndPoint ="/exchange-rates/{id}";
    String getAllWithPaginationEndPoint ="/exchange-rates/all/pagination";
    String getBulkEndPoint ="/exchange-rates/bulk";

    @Test(priority = 1)
    public void createExchangeRateValidTest() throws IOException    {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-exchange-rate-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    //Verify with null ID
    @Test
    public void createExchangeRateInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-exchange-rate-invalid1.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("The given id must not be null!"));

    }
    //Verify with invalid Id
    @Test
    public void createExchangeRateInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-exchange-rate-invalid2.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("The given id must not be null!"));

    }
    //Verify with incorrect date format
    @Test
    public void createExchangeRateInvalidTest3() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-exchange-rate-invalid3.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad request"));

    }

    @Test(priority = 2)
    public void getAllWithPaginationExchangeRateValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "exchangeRateId")
                        .when()
                        .get(getAllWithPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.exchangeRateId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.exchangeRateId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }
    @Test
    public void getAllWithPaginationExchangeRateInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "exchangeRateId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    @Test(priority = 3)
    public void updateExchangeRateValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"branchId\": 41,\n" +
                        "  \"exchangeRate\": 45,\n" +
                        "  \"exchangeRateDate\": \"2020-12-14\",\n" +
                        "  \"exchangeRateId\": "+x+"\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    //Null Id
    @Test
    public void updateExchangeRateInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"branchId\": \"\",\n" +
                        "  \"exchangeRate\": \"\",\n" +
                        "  \"exchangeRateDate\": \"2020-09-17\",\n" +
                        "  \"exchangeRateId\": \"\"\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Id can not be null"));

    }
    //Date format
    @Test
    public void updateExchangeRateInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"branchId\": 41,\n" +
                        "  \"exchangeRate\": 45,\n" +
                        "  \"exchangeRateDate\": \"2020- -14\",\n" +
                        "  \"exchangeRateId\": "+x+"\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    //Invalid branch Id
    @Test
    public void updateExchangeRateInvalidTest3() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"branchId\": 35,\n" +
                        "  \"exchangeRate\": 45,\n" +
                        "  \"exchangeRateDate\": \"2020-08-14\",\n" +
                        "  \"exchangeRateId\": "+x+"\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }
    //incorrect exchangeRateId
    @Test
    public void updateExchangeRateInvalidTest4() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"branchId\": 41,\n" +
                        "  \"exchangeRate\": 45,\n" +
                        "  \"exchangeRateDate\": \"2020-09-14\",\n" +
                        "  \"exchangeRateId\":5 \n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void getOneExchangeRateValidTest() throws IOException {
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
        System.out.println("GetOne Data List: " + jsonStr);


    }
    @Test
    public void getOneExchangeRateInvalidTest() throws IOException {

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
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 5)
    public void getBulkExchangeRateValidTest() throws IOException{
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
        System.out.println("GetBulk Data List:" + jsonStr);

    }

}

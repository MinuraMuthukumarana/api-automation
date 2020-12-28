package infoins.api.client.account;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class AccountController extends BaseClass {
    String baseURL;
    String createEndPoint ="/sales/account";
    String updatedEndPoint ="/sales/account";
    String getViewOneEndPoint="/sales/account/{id}";
    String deleteEndPoint ="/sales/account/{id}";
    String getPaginationEndPoint ="/sales/account/all/pagination";
    String getFilterAccountEndPoint ="/sales/account/filterAccounts";
    String getSearchAccountEndPoint ="/sales/account/searchAccounts";
    String getAccountIdEndPoint="/sales/account/view/{accountId}";
    String getViewAllEndPoint="/sales/account/view/all";
    String getAllWithPaginationEndPoint= "/sales/account/view/viewAllWithPagination";


    @Test(priority = 1)
    public void createAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\umbrella\\"+"account-create-account-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    @Test
    public void createAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"account-create-account-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void updateAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\umbrella\\"+"account-modify-account-invalid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    @Test
    public void updateAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\client\\"+"account-modify-account-invalid.json"))
                .when()
                .post(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to retrieve a Salesforce Account by AccountId
    @Test(priority = 3)
    public void getOneAccountValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, id)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getOneAccountInvalidTest() throws IOException {
        String clientId = "Id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void deleteAccountValidTest() throws IOException{
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
    @Test
    public void deleteAccountInvalidTest() throws IOException{
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .delete(deleteEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to retrieve all Salesforce Accounts with Pagination
    @Test(priority = 5)
    public void getPaginationAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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
    public void getPaginationAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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

    //API to filter a Salesforce Account by SalesChannelCode and Salesforce Account Status
    @Test(priority = 6)
    public void getAllWithFilterAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("chanlCode", 5)
                .queryParam("pageNo", 10)
                .queryParam("pageSize", "branchId")
                .queryParam("sfStatus", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getFilterAccountEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithFilterAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("chanlCode", 5)
                .queryParam("pageNo", 10)
                .queryParam("pageSize", "branchId")
                .queryParam("sfStatus", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getFilterAccountEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to search a Salesforce Account by a SearchKey
    @Test(priority = 7)
    public void getAllWithSearchAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 10)
                .queryParam("pageSize", "branchId")
                .queryParam("searchKey ", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getSearchAccountEndPoint)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAllWithSearchAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 10)
                .queryParam("pageSize", "branchId")
                .queryParam("searchKey", 10)
                .queryParam("sortBy", "branchId")
                .when()
                .get(getSearchAccountEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    //API to Activate / InActivate Account Status by AccountCode

    //API to view a Salesforce Account by AccountId
    @Test(priority = 8)
    public void getAccountIdValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getAccountIdEndPoint, id)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getAccountIdInvalidTest() throws IOException {
        String clientId = "Id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getAccountIdEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    //API to view all Salesforce Accounts
    @Test(priority = 9)
    public void viewAllAccountValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(200);
    }

    //API to view all Salesforce Accounts with pagination
    @Test(priority = 10)
    public void getAllWithPaginationAccountValidTest() throws IOException {
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
    public void getAllWithPaginationAccountInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
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

package infoins.api.admin.globaltax;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GlobalTaxController extends BaseClass {
    String baseURL;

    @Test(priority = 1)
    public void createGlobalTax() throws IOException {
        baseURL = getURL();
        String createGlobalTaxEndpoint = "/global-taxes";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the create request and success response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-global-tax-success.json"))

                .when()
                .post(createGlobalTaxEndpoint)

                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }

    @Test(priority = 2)
    public void getBulk() throws IOException {
        baseURL = getURL();
        String getBulkEndpoint = "/global-taxes/bulk";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the Status code & response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())

                .when()
                .get(getBulkEndpoint)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("[0].globalTaxId", equalTo(1));
    }

    @Test(priority = 3)
    public void modifyGlobalTax() throws IOException {
        baseURL = getURL();
        String modifyGlobalTaxEndpoint = "/global-taxes";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the create request and success response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("modify-global-tax-valid.json"))

                .when()
                .put(modifyGlobalTaxEndpoint)

                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    @Test(priority = 4)
    public void getOneGlobalTax() throws IOException {
        baseURL = getURL();
        String id = "1";
        String getOneEndpoint = "/global-taxes/{id}";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the Status code & response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())

                .when()
                .get(getOneEndpoint, id)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("taxAmount", equalTo(60));
    }

    @Test(priority = 5)
    public void deleteOneGlobalTax() throws IOException {
        baseURL = getURL();
        String id = "1";
        String deleteOneEndpoint = "/global-taxes/{id}";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the Status code & response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())

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
    public void createMultipleGlobalTax() throws IOException {
        baseURL = getURL();
        String createMultipleGlobalTaxEndpoint = "/global-taxes/multiple";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the create request and success response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("create-multiple-global-tax-success.json"))

                .when()
                .post(createMultipleGlobalTaxEndpoint)

                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }

    @Test(priority = 7)
    public void deleteBulkGlobalTax() throws IOException {
        baseURL = getURL();
        String idList = "1,2,3";
        String deleteOneEndpoint = "/global-taxes/all/{ids}";

        //Setting up Base URL
        baseURI = baseURL;

        //Verifying the Status code & response
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())

                .when()
                .delete(deleteOneEndpoint, idList)

                .then()
                .assertThat()
                .statusCode(200)
                .and()
                .contentType(ContentType.JSON)
                .and()
                .body("message", equalTo("Data deleted successfully"));
    }
}

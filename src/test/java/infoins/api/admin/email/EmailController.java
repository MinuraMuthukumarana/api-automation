package infoins.api.admin.email;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import infoins.ExcelDataReader;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.ITestContext;
import org.testng.annotations.BeforeTest;
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
 * @author : Eranda Kodagoda, Minura Muthukumarana
 * * Updated new methods
 *  * @date : August 10, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */

public class EmailController extends BaseClass {

    private int x;
    String baseURL;
    String createEndPoint = "/app-email-configs";
    String modifyEndPoint = "/app-email-configs";
    String getOneEndPoint = "/app-email-configs/{id}";
    String deleteEndPoint = "/app-email-configs/{id}";
    String getAllPaginationEndPoint="/app-email-configs/all/pagination";
    String getOneTemplateWithChildrenEndPoint = "/app-email-configs/template/{id}/children";
    String getOneTemplateParentEndPoint = "/app-email-configs/template/{id}/parent";
    String getAllTemplatesWithChildrenEndPoint = "/app-email-configs/template/all/children";
    String getAllTemplateParentsEndPoint = "/app-email-configs/template/all/parent";



    @Test(priority = 1)
    public void createEmailValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-email-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }

    @Test(priority = 2)
    public void getAllPaginationEmailValidTest() throws IOException{
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
                        .queryParam("sortBy", "appEmailConfId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.appEmailConfId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.appEmailConfId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }
    //minus Value
    @Test
    public void getAllPaginationEmailInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", -1)
                        .queryParam("pageSize", -100)
                        .queryParam("sortBy", "appEmailConfId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(400)
                        .and()
                        .body("error_description", equalTo("Page index must not be less than zero!"));

    }

    @Test(priority = 3)
    public void modifyEmailValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appEmailConfId\": "+x+",\n" +
                        "  \"emlTmpltId\": 1,\n" +
                        "  \"isAutoBccToRtnEml\": true,\n" +
                        "  \"nameAppear\": \"Andrew Update\",\n" +
                        "  \"outEmlSignature\": \"Andrew signature update\",\n" +
                        "  \"rtnEmlAdrs\": \"andrew.update@gmail.com\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }
    //Modify the already deleted appEmailConfId
    @Test
    public void modifyEmailInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appEmailConfId\": 10,\n" +
                        "  \"emlTmpltId\": 1,\n" +
                        "  \"isAutoBccToRtnEml\": true,\n" +
                        "  \"nameAppear\": \"Andrew Update\",\n" +
                        "  \"outEmlSignature\": \"Andrew signature update\",\n" +
                        "  \"rtnEmlAdrs\": \"andrew.update@gmail.com\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    @Test(priority = 4)
    public void getOneEmailValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne List: " + jsonStr);
    }
    //minus Value
    @Test
    public void getOneEmailInvalidTest1() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }
    //check with already deleted id
    @Test
    public void getOneEmailInvalidTest2() throws IOException {
        int id = 10;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    @Test(priority = 5)
    public void getOneTemplateWithChildrenValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateWithChildrenEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Child Data List: " + jsonStr);

    }
    @Test
    public void getOneTemplateWithChildrenInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateWithChildrenEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    @Test(priority = 6)
    public void getOneTemplateParentEmailValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateParentEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne Parent Data List: " + jsonStr);
    }
    @Test
    public void getOneTemplateParentEmailInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateParentEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    @Test(priority = 7)
    public void getAllTemplatesWithChildrenValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getAllTemplatesWithChildrenEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Child Data List: " + jsonStr);

    }

    @Test(priority = 8)
    public void getAllTemplateParentsValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getAllTemplateParentsEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Parent Data List: " + jsonStr);

    }

    @Test(priority = 9)
    public void deleteEmailValidTest() throws IOException {
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
    //minus value
    @Test
    public void deleteEmailInvalidTest() throws IOException {
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

    //Invalid scenarios for CreateEmail
    @DataProvider(name = "createEmailInvalidTest")
    @Parameters({"EmailController"})
    public Iterator<Object[]> getEmailCreateTestData(ITestContext context) throws IOException {

        String dataFile = context.getCurrentXmlTest().getParameter("EmailController");
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(0,"\\AdminExcel\\EmailController.xlsx");
        return iterator;
    }
    @Test(dataProvider = "createEmailInvalidTest")
    public void CreateEmailControllerInvalid(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(schema)
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("error_description", equalTo(message));
    }

    //Invalid scenarios for UpdateEmail
    @DataProvider(name = "UpdateEmailInvalidTest")
    @Parameters({"EmailController"})
    public Iterator<Object[]> getEmailUpdateTestData(ITestContext context) throws IOException {

        String dataFile = context.getCurrentXmlTest().getParameter("EmailController");
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(1,"\\AdminExcel\\EmailController.xlsx");
        return iterator;
    }
    @Test(dataProvider = "UpdateEmailInvalidTest")
    public void UpdateEmailControllerInvalid(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(schema)
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("error_description", equalTo(message));
    }

}

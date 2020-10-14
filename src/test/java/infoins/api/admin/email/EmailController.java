package infoins.api.admin.email;

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
 * @author : Eranda Kodagoda
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
    public void createValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-email-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    @Test
    public void createInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-email-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void getAllPagination() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", getBearerToken())
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

    @Test(priority = 3)
    public void modifyValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"appEmailConfId\": "+x+",\n" +
                        "  \"emlTmpltId\": 1,\n" +
                        "  \"isAutoBccToRtnEml\": \"y\",\n" +
                        "  \"nameAppear\": \"AndrewUPDATE\",\n" +
                        "  \"outEmlSignature\": \"sig16UPDATE\",\n" +
                        "  \"rtnEmlAdrs\": \"testUPDATE.test16@infoins.com\"\n" +
                        "}")
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }
    @Test
    public void modifyInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"modify-email-invalid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 4)
    public void getOneValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetOne List: " + jsonStr);
    }
    @Test
    public void getOneInvalidTest() throws IOException {
        String id = "id";
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

    @Test(priority = 5)
    public void getOneTemplateWithChildrenValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
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
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateWithChildrenEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 6)
    public void getOneTemplateParentValidTest() throws IOException {
        int id = 1;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
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
    public void getOneTemplateParentInvalidTest() throws IOException {
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
                .contentType(ContentType.JSON)
                .when()
                .get(getOneTemplateParentEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 7)
    public void getAllTemplatesWithChildrenValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", getBearerToken())
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
                .header("authorization", getBearerToken())
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
    public void deleteValidTest() throws IOException {
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
    @Test
    public void deleteInvalidTest() throws IOException {
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

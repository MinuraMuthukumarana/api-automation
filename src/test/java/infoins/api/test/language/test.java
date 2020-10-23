package infoins.api.test.language;

import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


public class test extends BaseClass {
    private int x;
    String baseURL;

    @Test(priority = 1)
    public void createValidTest() throws IOException {
        String createEndPoint="/app-email-configs";
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

    @Test(priority = 2)
    public void getAllWithPagination() throws IOException {
            String getAllPaginationEndPoint = "/app-email-configs/all/pagination";
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
        String modifyEndPoint="/app-email-configs";
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

    @Test(priority = 4)
    public void deleteValidTest() throws IOException {
        String deleteEndPoint="/app-email-configs/{id}";
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

    @Test(priority = 5)
    public void deleteValidTest1() throws IOException {
        String deleteEndPoint = "/app-email-configs/{id}";
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

    @Test(priority = 6)
    public void deleteValidTest2() throws IOException {
            String deleteEndPoint = "/app-email-configs/{id}";
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

    @Test(priority = 7)
    public void deleteValidTest3() throws IOException {
                String deleteEndPoint = "/app-email-configs/{id}";
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

package infoins.api.client.salesChannel;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class SalesChannelController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint= "/sales/channel";
    String updatedEndPoint = "/sales/channel";
    String getSalesChannelIdEndPoint="/sales/channel/{id}";
    String deleteEndPoint ="/sales/channel/{id}";
    String getAllWithPaginationEndPoint = "/sales/channel/all/pagination";

    //API to create a Sales Channel
    @Test(priority = 1)
    public void createSalesChannelValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomNo = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CA_"+randomNo+"\",\n" +
                        "  \"chanlCode\": \"CC_"+randomNo+"\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CN_"+randomNo+"\",\n" +
                        "  \"chanlType\": \"D\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("You have successfully added the sales channel CN_"+randomNo+""));

    }
    //Unique ChannelCode
    @Test
    public void createSalesChannelInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CA_2\",\n" +
                        "  \"chanlCode\": \"CC_2\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CN_2\",\n" +
                        "  \"chanlType\": \"D\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Channel Code should be unique"));
    }
    //Unique ChannelName
    @Test
    public void createSalesChannelInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CA_2\",\n" +
                        "  \"chanlCode\": \"CC_99\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CN_2\",\n" +
                        "  \"chanlType\": \"D\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Channel Name should be unique"));
    }
    //Unique ChannelName
    @Test
    public void createSalesChannelInvalidTest3() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CA_2\",\n" +
                        "  \"chanlCode\": \"CC_99\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CN_99\",\n" +
                        "  \"chanlType\": \"D\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Channel Abbreviation should be unique"));
    }

    //API to retrieve all Sales Channels with Pagination
    @Test(priority = 2)
    public void getAllWithPaginationSalesChannelValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "salesChanlId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.salesChanlId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.salesChanlId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }
    //minus Value
    @Test
    public void getAllWithPaginationSalesChannelInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", -100)
                .queryParam("sortBy", "salesChanlId")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));

    }

    //API to update an existing Sales Channel
    @Test(priority = 3)
    public void updateSalesChannelValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomNo2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CAU"+randomNo2+"\",\n" +
                        "  \"chanlCode\": \"CCU"+randomNo2+"\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CNU_"+randomNo2+"\",\n" +
                        "  \"chanlType\": \"D\",\n" +
                        "  \"salesChanlId\": "+x+"\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("You have successfully updated the sales channel."));
    }
    @Test
    public void updateSalesChannelInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"chanlAbbr\": \"CAU98\",\n" +
                        "  \"chanlCode\": \"CCU99\",\n" +
                        "  \"chanlDesc\": \"DIRECTER\",\n" +
                        "  \"chanlName\": \"CNU_99\",\n" +
                        "  \"chanlType\": \"D\",\n" +
                        "  \"salesChanlId\": -1\n" +
                        "}")
                .when()
                .put(updatedEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    //API to retrieve a Sales Channel by SalesChannelId
    @Test(priority = 4)
    public void getOneSalesChannelValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getSalesChannelIdEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void getOneSalesChannelInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getSalesChannelIdEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    //API to delete a Sales Channel by SalesChannelId
    @Test(priority = 5)
    public void deleteSalesChannelValidTest() throws IOException{
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
    @Test
    public void deleteSalesChannelInvalidTest() throws IOException{
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

}

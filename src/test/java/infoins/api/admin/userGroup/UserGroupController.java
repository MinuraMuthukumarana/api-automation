package infoins.api.admin.userGroup;

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
 *  * @date : September 21, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class UserGroupController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint ="/user-groups";
    String updateEndPoint ="/user-groups";
    String getOneEndPoint = "/user-groups/{id}";
    String getAllWithPaginationEndPoint = "/user-groups/all/pagination";
    String getAllGroupBranchesByGroupIdNameOrReferenceEndPoint="/user-groups/branches/{search-criteria}";
    String getAllGroupLimitsByGroupIdNameOrReferenceEndPoint = "/user-groups/limits/{search-criteria}";



    @Test(priority = 1)
    public void createUserGroupValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-user-group-valid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    @Test
    public void createUserGroupInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"create-user-group-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void getAllWithPaginationUserGroupValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("sortBy", "countryCode")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.groupId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.groupId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }
    @Test
    public void getAllWithPaginationUserGroupInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "InvalidcountryCode")
                .when()
                .get(getAllWithPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 3)
    public void updateUserGroupValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"countryCode\": \"SL\",\n" +
                        "  \"groupId\": "+x+",\n" +
                        "  \"groupLimits\": [\n" +
                        "    {\n" +
                        "      \"branchId\": 1,\n" +
                        "      \"classId\": 1,\n" +
                        "      \"functionId\": 1,\n" +
                        "      \"groupLimitId\": 1,\n" +
                        "      \"levelConfigId\": 1,\n" +
                        "      \"maxLimit\": 100,\n" +
                        "      \"minLimit\": 15,\n" +
                        "      \"productId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"groupRefId\": \"1\",\n" +
                        "  \"grpBranchIds\": [\n" +
                        "    1\n" +
                        "  ]\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));

    }
    @Test
    public void updateUserGroupInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\admin\\"+"update-user-group-invalid.json"))
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));

    }

    @Test(priority = 4)
    public void getOneUserGroupValidTest() throws IOException{

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
        System.out.println("GetOne Data List: " + jsonStr);
    }
    @Test
    public void getOneUserGroupInvalidTest() throws IOException {

        int Id = 0;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, Id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));


    }


//    @Test(priority = 5)
//    public void getAllGroupBranchesByGroupIdNameOrReferenceUserGroupValidTest() throws IOException{
//       // String search-criteria ="";
//        baseURL = getURL();
//        baseURI = baseURL;
//        Response response=
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .when()
//                .get(getAllGroupBranchesByGroupIdNameOrReferenceEndPoint)
//                .then()
//                .assertThat().statusCode(200)
//                .and().extract().response();
//
//        String jsonStr = response.getBody().asString();
//        System.out.println("search-criteria Data List: " + jsonStr);
//    }
//    @Test
//    public void getAllGroupBranchesByGroupIdNameOrReferenceUserGroupInvalidTest() throws IOException{
//        baseURL = getURL();
//        baseURI = baseURL;
//
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .queryParam("search-criteria", "Invalid")
//                .when()
//                .get(getAllGroupBranchesByGroupIdNameOrReferenceEndPoint)
//                .then()
//                .assertThat().statusCode(400)
//                .and()
//                .body("error", equalTo("Bad Request"));
//
//    }
//
//    @Test(priority = 6)
//    public void  getAllGroupLimitsByGroupIdNameOrReferenceEndPointValidTest() throws IOException{
//        baseURL = getURL();
//        baseURI = baseURL;
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .queryParam("search-criteria", "")
//                .when()
//                .get(getAllGroupLimitsByGroupIdNameOrReferenceEndPoint)
//                .then()
//                .assertThat().statusCode(200);
//
//    }
//    @Test
//    public void  getAllGroupLimitsByGroupIdNameOrReferenceEndPointInvalidTest() throws IOException{
//        baseURL = getURL();
//        baseURI = baseURL;
//
//        given()
//                .header("accept", "*/*")
//                .header("authorization", AccessTokenHolder.access_token)
//                .contentType(ContentType.JSON)
//                .queryParam("search-criteria", "Invalid")
//                .when()
//                .get(getAllGroupBranchesByGroupIdNameOrReferenceEndPoint)
//                .then()
//                .assertThat().statusCode(400)
//                .and()
//                .body("error", equalTo("Bad Request"));
//
//    }
}

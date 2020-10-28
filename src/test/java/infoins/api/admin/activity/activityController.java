package infoins.api.admin.activity;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
/**
 * @author : Minura Muthukumarana
 *  * @date : September 21, 2020
 *  *Updated )ctober 27,2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class activityController extends BaseClass {
    String baseURL;
    String findAllActivityByDateEndPoint="/activity/find-by-date";
    String findAllActivityByUserEndPoint= "/activity/find-by-user/{userName}";


    @Test(priority = 1)
    public void getFindAllActivityByDateActivityValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("endDate", "2020-12-31")
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 100)
                .queryParam("startDate", "2020-01-01")
                .when()
                .get(findAllActivityByDateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("findAllActivityByDate Data List: " + jsonStr);

    }
    @Test
    public void getFindAllActivityByDateActivityInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("endXDate", "2020-13-32")
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .queryParam("startDate", "2020-01-01")
                .when()
                .get(findAllActivityByDateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void getFindAllActivityByUserActivityValidTest() throws IOException{
        String userName = "qaUser";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .when()
                .get(findAllActivityByUserEndPoint,userName)
                .then()
                .assertThat().statusCode(200);
    }
    @Test
    public void getFindAllActivityByUserActivityInvalidTest() throws IOException{
        String userName = "Invalidqauser";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", 0)
                .queryParam("pageSize", 10)
                .when()
                .get(findAllActivityByUserEndPoint,userName)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

}

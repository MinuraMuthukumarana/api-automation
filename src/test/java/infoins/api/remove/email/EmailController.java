package infoins.api.remove.email;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

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

    Properties property = new Properties();
    FileInputStream propertyFile;

    {
        try {
            propertyFile = new FileInputStream("C:\\Users\\outsource.minura\\API_Testing\\api-automation\\src\\test\\java\\infoins\\testData\\propfileEmail.properties");
            property.load(propertyFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    //String NoemlTmpltId = property.getProperty("emlTmpltId");
    //String[] NoemlTmpltId = property.getProperty("emlTmpltId").split(",");
    String[] NoemlTmpltId = property.getProperty("emlTmpltId").split(",");

    @Test(priority = 1)
    public void createEmailValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"emlTmpltId\": "+NoemlTmpltId+",\n" +
                        "  \"isAutoBccToRtnEml\": true,\n" +
                        "  \"nameAppear\": \"gayan\",\n" +
                        "  \"outEmlSignature\": \"gayan\",\n" +
                        "  \"rtnEmlAdrs\": \"gayan@gmail.com\"\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }

}

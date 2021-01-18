package infoins.api.TestDemoAPI.TestDemoXLReade;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import infoins.ExcelDataReader;
import io.restassured.http.ContentType;
import org.testng.ITestContext;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Iterator;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GlobalTaxControllerXLreader extends BaseClass {

    String baseURL;
    String createGlobalTaxEndpoint = "/global-taxes";

    @DataProvider(name = "createGlobalTaxInvalid")
    @Parameters({"filePath"})
    public Iterator<Object[]> getApiTestData(ITestContext context) throws IOException {

        String dataFile = context.getCurrentXmlTest().getParameter("filePath");
        Iterator<Object[]> iterator = ExcelDataReader.excelDataReader(0, "\\Excel\\ADMIN_APIDataFile.xlsx");
        return iterator;
    }

    @Test(dataProvider = "createGlobalTaxInvalid")
    public void createGlobalTaxInvalid(Integer statusCode, String schema, String message) throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body(schema)
                .when()
                .post(createGlobalTaxEndpoint)
                .then()
                .assertThat().statusCode(statusCode)
                .and()
                .body("error_description", equalTo(message));
    }
}

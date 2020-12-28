package infoins.api.client.corporate;

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
/**
 * @author : Minura Muthukumarana
 *  * @date : September 22, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class corporateController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint ="/client/corporate";
    String updateEndPoint="/client/corporate";
    String getOneEndPoint="/client/corporate/{id}";
    String findAllPaginationEndPoint="/client/corporate/all/pagination";
    String getViewOneEndPoint="/client/corporate/view/{clientId}";
    String getViewAllEndPoint="/client/corporate/view/viewAllWithPagination";
    String deleteEndPoint = "/client/corporate/{id}";

    //Create inactive CorporateClient  A=3, I=4
    @Test(priority = 1)
    public void  createCorporateValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"businessName\": \"Abans\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientCode\": \"CA"+randomInt1+"\",\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"cmDtClientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"CBL LTD\",\n" +
                        "      \"acctNumber\": \"AC123456\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"receiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientEmail\": [\n" +
                        "    {\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"test@123.com\",\n" +
                        "      \"receiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"AD1\",\n" +
                        "      \"addressLine2\": \"AD2\",\n" +
                        "      \"addressLine3\": \"AD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"210000\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"contactNumber\": \"0719160090\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"email\": \"testmail@gmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt1+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX02\",\n" +
                        "  \"taxTypeId\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    //Invalid email format
    @Test
    public void  createCorporateInvalidTest1() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"businessName\": \"Abans\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientCode\": \"CA"+randomInt1+"\",\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"cmDtClientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"CBL LTD\",\n" +
                        "      \"acctNumber\": \"AC123456\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"receiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientEmail\": [\n" +
                        "    {\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"testgmail.com\",\n" +
                        "      \"receiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"AD1\",\n" +
                        "      \"addressLine2\": \"AD2\",\n" +
                        "      \"addressLine3\": \"AD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"210000\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"contactNumber\": \"0719160090\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"email\": \"testmail@gmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG0123\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX02\",\n" +
                        "  \"taxTypeId\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Email validation Error"));
    }
    //Invalid contact number
    @Test
    public void  createCorporateInvalidTest2() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"businessName\": \"Abans\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientCode\": \"CA"+randomInt1+"\",\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"cmDtClientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"CBL LTD\",\n" +
                        "      \"acctNumber\": \"AC123456\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"receiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientEmail\": [\n" +
                        "    {\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"testgmail.com\",\n" +
                        "      \"receiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"cmDtClientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"AD1\",\n" +
                        "      \"addressLine2\": \"AD2\",\n" +
                        "      \"addressLine3\": \"AD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"210000\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"contactNumber\": \"0719160\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"email\": \"testmail@gmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG0123\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX02\",\n" +
                        "  \"taxTypeId\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Invalid Phone Number"));
    }

    @Test(priority = 2)
    public void getLastCreatedClientId() throws IOException{
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
                .queryParam("sortBy", "clientId")
                .when()
                .get(findAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.clientId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.clientId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void  updateCorporateValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"businessName\": \"CBL\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"clientCode\": \"CC"+randomInt2+"\",\n" +
                        "  \"clientId\": "+x+",\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"deletedClientBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientLocalIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"email\": \"testup@sample.ccc\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"phone\": \"0719160090\",\n" +
                        "  \"statusId\": 1,\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"contactNumber\": \"0719160111\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt2+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX01\",\n" +
                        "  \"taxTypeId\": 1,\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC654321\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientStatusId\": 4\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    //Invalid client Id
    @Test
    public void  updateCorporateInvalidTest1() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"businessName\": \"CBL\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"clientCode\": \"CC"+randomInt2+"\",\n" +
                        "  \"clientId\":10,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"deletedClientBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientLocalIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"email\": \"testup@gmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"phone\": \"0719160090\",\n" +
                        "  \"statusId\": 1,\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"contactNumber\": \"0719160009\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"10290\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt2+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX01\",\n" +
                        "  \"taxTypeId\": 1,\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC654321\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientStatusId\": 3\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }
    //invalid email format
    @Test
    public void  updateCorporateInvalidTest2() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"businessName\": \"CBL\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"clientCode\": \"CC"+randomInt2+"\",\n" +
                        "  \"clientId\":23,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"deletedClientBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientLocalIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"email\": \"testupgmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"phone\": \"0719160090\",\n" +
                        "  \"statusId\": 1,\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"contactNumber\": \"0719160090\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"10290\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt2+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX01\",\n" +
                        "  \"taxTypeId\": 1,\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC654321\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientStatusId\": 3\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Email must be a well-formed email address"));
    }
    //Invalid contact number
    @Test
    public void  updateCorporateInvalidTest3() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"businessName\": \"CBL\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"clientCode\": \"CC"+randomInt2+"\",\n" +
                        "  \"clientId\":23,\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"deletedClientBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientLocalIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"email\": \"testup@gmail.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"phone\": \"0719160090\",\n" +
                        "  \"statusId\": 1,\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"contactNumber\": \"07191600\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"10290\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt2+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX01\",\n" +
                        "  \"taxTypeId\": 1,\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC654321\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientStatusId\": 3\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Invalid Phone Number"));
    }

    @Test(priority = 4)
    public void getOneCorporateValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void getOneCorporateInvalidTest() throws IOException {
        int id = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 5)
    public void getAllWithPaginationCorporateValidTest() throws IOException{
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
                        .queryParam("sortBy", "clientId")
                        .when()
                        .get(findAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void getAllWithPaginationCorporateInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "clientId")
                .when()
                .get(findAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    @Test(priority = 6)
    public void viewOneCorporateValidTest() throws IOException {
        int clientId = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .header("CountryId", 1)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getViewOneEndPoint, clientId)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void viewOneCorporateInvalidTest() throws IOException {
        int clientId = -1;
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
                .body("message", equalTo("Data not found"));

    }

    @Test(priority = 7)
    public void viewAllWithPaginationCorporateValidTest() throws IOException{
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
                        .queryParam("sortBy", "clientId")
                        .when()
                        .get(getViewAllEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void viewAllWithPaginationCorporateInvalidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .queryParam("pageNo", -1)
                .queryParam("pageSize", 10)
                .queryParam("sortBy", "clientId")
                .when()
                .get(getViewAllEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    @Test(priority = 8)
    public void  updateAsAInactiveValidTest() throws IOException{
        baseURL = getURL();
        baseURI = baseURL;
        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"businessName\": \"CBL\",\n" +
                        "  \"businessTypeId\": 1,\n" +
                        "  \"clientCode\": \"CC"+randomInt2+"\",\n" +
                        "  \"clientId\": "+x+",\n" +
                        "  \"clientTypeId\": 2,\n" +
                        "  \"deletedClientBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedClientLocalIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"email\": \"testup@sample.ccc\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"phone\": \"0719160090\",\n" +
                        "  \"statusId\": 1,\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"contactNumber\": \"0719160111\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"isBlacklisted\": false,\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"primConPerson\": \"ABC\",\n" +
                        "  \"profileImage\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"regdNo\": \"REG"+randomInt2+"\",\n" +
                        "  \"remarks\": \"test\",\n" +
                        "  \"signature\": \"/9j/4AAQSkZJRgABAQAAAQABAAD/2wBDABALDA4MChAODQ4SERATGCgaGBYWGDEjJR0oOjM9PDkzODdASFxOQERXRTc4UG1RV19iZ2hnPk1xeXBkeFxlZ2P/2wBDARESEhgVGC8aGi9jQjhCY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2NjY2P/wAARCAA6ADYDASIAAhEBAxEB/8QAGgAAAgMBAQAAAAAAAAAAAAAAAAUBAgQDB//EACsQAAICAQIEBAYDAAAAAAAAAAABAgMRBCESEzFRIlJhkRQ0QUKBkmJxcv/EABQBAQAAAAAAAAAAAAAAAAAAAAD/xAAUEQEAAAAAAAAAAAAAAAAAAAAA/9oADAMBAAIRAxEAPwD0AAACG0llvCKK+pvHMj7mDUXO2b38K6I5ANyTDorWp8tvZ9DcAAAABD6MkrJcUXHusAKQLWVyrlwyW5UDrpfmIDMX6SpzsU/tixgAAAAAFLLIVrMngyWayT2rXCu76gW1+PB33MZMpOTzJtv1IAY6THw8fydxVXZOt5hJo1Va1PaxY9UBrAiMlJZi00AGCenvsm5Sju/U5umS6uKx/JDQ5uqt9a4v8AL+TPvH9kCom3hcLf8ApDDlV5zwR9gVcI7qEV/SAXct+aH7omNM5vEXGT9JJjDlV+SPsTGEVLKik+6QGWmvUVN4js/pkDaAH//Z\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"taxRegdNo\": \"TX01\",\n" +
                        "  \"taxTypeId\": 1,\n" +
                        "  \"acctHolderName\": \"Abans Finance PLC\",\n" +
                        "  \"acctNumber\": \"AC654321\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientStatusId\": 4\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    @Test(priority = 9)
    public void deleteCorporateValidTest() throws IOException{
        int id = x;
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
    public void deleteCorporateInvalidTest() throws IOException{
        int id = -1;
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
                .body("message", equalTo("Data not found"));

    }

}

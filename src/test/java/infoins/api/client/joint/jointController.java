package infoins.api.client.joint;

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
public class jointController extends BaseClass {
    private int x;
    String baseURL;
    String createEndPoint ="/client/joint";
    String updateEndPoint ="/client/joint";
    String getOneEndPoint ="/client/joint/{id}";
    String getAllPaginationEndPoint= "/client/joint/all/pagination";
    String viewOneEndPoint = "/client/joint/view/{clientId}";
    String ViewAllEndPoint ="/client/joint/view/viewAllWithPagination";
    String deleteEndPoint = "/client/joint/{id}";

    // Corporate client Active (joinClientId 29)
    @Test(priority = 1)
    public void  createJointValidTest() throws IOException {
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
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo\",\n" +
                        "      \"acctNumber\": \"AC12345\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"CA"+randomInt1+"\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"anjalo@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientLocale\": [\n" +
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
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"0719160090\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"AWS Anjalo Kamal Silva\",\n" +
                        "  \"email\": \"anjalo@sample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 1,\n" +
                        "      \"joinClientId\": 29\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));
    }
    //Invalid email type
    @Test
    public void  createJointInvalidTest1() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo\",\n" +
                        "      \"acctNumber\": \"AC12345\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"AC12345\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"anjalo@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientLocale\": [\n" +
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
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"0719160090\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"AWS Anjalo Kamal Silva\",\n" +
                        "  \"email\": \"anjalosample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 1,\n" +
                        "      \"joinClientId\": 29\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Email must be a well-formed email address"));
    }
    //Invalid Phone Number
    @Test
    public void  createJointInvalidTest2() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"AC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo\",\n" +
                        "      \"acctNumber\": \"AC12345\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"AC12345\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0719160090\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"anjalo@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientLocale\": [\n" +
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
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"07191600\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"AWS Anjalo Kamal Silva\",\n" +
                        "  \"email\": \"anjalo@sample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 1,\n" +
                        "      \"joinClientId\": 29\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"21000\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
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
                .get(getAllPaginationEndPoint)
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
    public void  updateJointValidTest() throws IOException{
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
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"JAC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo Kamal update\",\n" +
                        "      \"acctNumber\": \"JAC123456789\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1,\n" +
                        "      \"clientBankId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"JCC"+randomInt2+"\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"clientContactId\": 1,\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0778866111\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"clientEmailId\": 1,\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"kamalupdate@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientId\": "+x+",\n" +
                        "  \"clientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"ADD1\",\n" +
                        "      \"addressLine2\": \"ADD2\",\n" +
                        "      \"addressLine3\": \"ADD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"clientLocaleId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"10280\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"0758866111\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"deletedBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedJointAcctDtlIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedLocaleIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"D M Anjalo Kamal silva\",\n" +
                        "  \"email\": \"anjalokamalupdate@sample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 0,\n" +
                        "      \"joinClientId\": 29,\n" +
                        "      \"jointAcctContactId\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"10280\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }
    //Invalid email type
    @Test
    public void  updateJointInvalidTest1() throws IOException{
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
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"JAC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo Kamal update\",\n" +
                        "      \"acctNumber\": \"JAC123456789\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1,\n" +
                        "      \"clientBankId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"JCC"+randomInt2+"\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"clientContactId\": 1,\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0778866111\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"clientEmailId\": 1,\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"kamalupdate@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientId\": 24,\n" +
                        "  \"clientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"ADD1\",\n" +
                        "      \"addressLine2\": \"ADD2\",\n" +
                        "      \"addressLine3\": \"ADD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"clientLocaleId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"10280\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"0758866111\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"deletedBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedJointAcctDtlIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedLocaleIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"D M Anjalo Kamal silva\",\n" +
                        "  \"email\": \"anjalokamalupdatesample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 0,\n" +
                        "      \"joinClientId\": 29,\n" +
                        "      \"jointAcctContactId\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"10280\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Email must be a well-formed email address"));
    }
    //Invalid Phone Number
    @Test
    public void  updateJointInvalidTest2() throws IOException{
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
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"JAC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo Kamal update\",\n" +
                        "      \"acctNumber\": \"JAC123456789\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1,\n" +
                        "      \"clientBankId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"JCC"+randomInt2+"\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"clientContactId\": 1,\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0778866111\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"clientEmailId\": 1,\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"kamalupdate@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientId\": 24,\n" +
                        "  \"clientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"ADD1\",\n" +
                        "      \"addressLine2\": \"ADD2\",\n" +
                        "      \"addressLine3\": \"ADD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"clientLocaleId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"10280\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientStatusId\": 3,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"07588661\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"deletedBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedJointAcctDtlIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedLocaleIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"D M Anjalo Kamal silva\",\n" +
                        "  \"email\": \"anjalokamalupdate@sample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 0,\n" +
                        "      \"joinClientId\": 29,\n" +
                        "      \"jointAcctContactId\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"10280\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Invalid Phone Number"));
    }

    @Test(priority = 4)
    public void getOneJointValidTest() throws IOException {
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
    public void getOneJointInvalidTest() throws IOException {
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
    public void getAllWithPaginationJointValidTest() throws IOException{
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
                .get(getAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void getAllWithPaginationJointInvalidTest() throws IOException{
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
                .get(getAllPaginationEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    @Test(priority = 6)
    public void viewOneJointValidTest() throws IOException {
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
                        .get(viewOneEndPoint, clientId)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);
    }
    @Test
    public void viewOneJointInvalidTest() throws IOException {
        int clientId = -1;
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .header("CountryId", 1)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, clientId)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("message", equalTo("Data not found"));
    }

    @Test(priority = 7)
    public void viewAllWithPaginationJointValidTest() throws IOException{
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
                        .get(ViewAllEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

    }
    @Test
    public void viewAllWithPaginationJointInvalidTest() throws IOException{
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
                .get(ViewAllEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error_description", equalTo("Page index must not be less than zero!"));
    }

    //Delete active client
    @Test(priority = 8)
    public void deleteActiveJointValidTest() throws IOException{
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
                .assertThat().statusCode(403);

    }

    //Update client as a inactive
    @Test(priority = 9)
    public void  updateJointAsInactiveValidTest() throws IOException{
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
                        "  \"acctHolderName\": \"Anjalo Kamal\",\n" +
                        "  \"acctNumber\": \"JAC123456\",\n" +
                        "  \"addressCountryId\": 1,\n" +
                        "  \"addressLine1\": \"AD1\",\n" +
                        "  \"addressLine2\": \"AD2\",\n" +
                        "  \"addressLine3\": \"AD3\",\n" +
                        "  \"addressTypeId\": 1,\n" +
                        "  \"bankBranchId\": 1,\n" +
                        "  \"bankId\": 1,\n" +
                        "  \"cityId\": 1,\n" +
                        "  \"clientBank\": [\n" +
                        "    {\n" +
                        "      \"acctHolderName\": \"Anjalo Kamal update\",\n" +
                        "      \"acctNumber\": \"JAC123456789\",\n" +
                        "      \"bankId\": 1,\n" +
                        "      \"bnkBranchId\": 1,\n" +
                        "      \"clientBankId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientCode\": \"JCC"+randomInt2+"\",\n" +
                        "  \"clientContact\": [\n" +
                        "    {\n" +
                        "      \"clientContactId\": 1,\n" +
                        "      \"contactTypeId\": 1,\n" +
                        "      \"contactValue\": \"0778866111\",\n" +
                        "      \"isReceiveNotify\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientEmail\": [\n" +
                        "    {\n" +
                        "      \"activeContact\": \"p\",\n" +
                        "      \"clientEmailId\": 1,\n" +
                        "      \"emailTypeId\": 1,\n" +
                        "      \"emailValue\": \"kamalupdate@sample.com\",\n" +
                        "      \"isReceiveEmail\": true\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientId\": "+x+",\n" +
                        "  \"clientLocale\": [\n" +
                        "    {\n" +
                        "      \"addressLine1\": \"ADD1\",\n" +
                        "      \"addressLine2\": \"ADD2\",\n" +
                        "      \"addressLine3\": \"ADD3\",\n" +
                        "      \"addressTypeId\": 1,\n" +
                        "      \"cityId\": 1,\n" +
                        "      \"clientLocaleId\": 1,\n" +
                        "      \"countryId\": 1,\n" +
                        "      \"isPrimary\": true,\n" +
                        "      \"postalCode\": \"10280\",\n" +
                        "      \"stateId\": 1,\n" +
                        "      \"suburbId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"clientStatusId\": 4,\n" +
                        "  \"clientTypeId\": 3,\n" +
                        "  \"contactNumber\": \"0758866111\",\n" +
                        "  \"contactTypeId\": 1,\n" +
                        "  \"countryId\": 1,\n" +
                        "  \"deletedBankIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedContactIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedEmailIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedJointAcctDtlIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"deletedLocaleIds\": [\n" +
                        "    0\n" +
                        "  ],\n" +
                        "  \"displayNameFull\": \"Anjalo Kamal silva\",\n" +
                        "  \"displayNameInitials\": \"D M Anjalo Kamal silva\",\n" +
                        "  \"email\": \"anjalokamalupdate@sample.com\",\n" +
                        "  \"emailTypeId\": 1,\n" +
                        "  \"isJoint\": true,\n" +
                        "  \"jointAcctDtl\": [\n" +
                        "    {\n" +
                        "      \"contriPct\": 0,\n" +
                        "      \"joinClientId\": 29,\n" +
                        "      \"jointAcctContactId\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"postalCode\": \"10280\",\n" +
                        "  \"stateId\": 1,\n" +
                        "  \"suburbId\": 1,\n" +
                        "  \"titleId\": 1\n" +
                        "}")
                .when()
                .put(updateEndPoint)
                .then()
                .assertThat().statusCode(200)
                .and()
                .body("message", equalTo("Data updated successfully"));
    }

    //Delete inactive client
    @Test(priority = 10)
    public void deleteJointValidTest() throws IOException{
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
    public void deleteJointInvalidTest() throws IOException{
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

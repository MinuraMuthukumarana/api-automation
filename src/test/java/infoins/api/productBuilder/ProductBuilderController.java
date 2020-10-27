package infoins.api.productBuilder;

import infoins.AccessTokenHolder;
import infoins.BaseClass;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.List;
import java.util.Random;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

/**
 * @author : Minura Muthukumarana
 *  * @date : October 27, 2020
 *  * @version : 1.0
 *  * @copyright : © 2010-2019 Information International Limited. All Rights Reserved
 *  */
public class ProductBuilderController extends BaseClass {

    private int x;
    String baseURL;
    String createEndPoint ="/product-builder";
    String getAllPaginationEndPoint = "/product-builder/all/pagination";
    String modifyEndPoint = "/product-builder";
    String getOneEndPoint = "/product-builder/{id}";
    String deleteEndPoint = "/product-builder/{id}";

    @BeforeTest
    void setUp() throws Exception {
        getBearerToken("product-builder","5d55b498-67f4-4a2b-87dc-3adb82ca93a4");
    }


    @Test(priority = 1)
    public void createProductBuilderValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        Random randomGenerator = new Random();
        int randomInt1 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"claimsAfter\": 124567,\n" +
                        "  \"classId\": 1,\n" +
                        "  \"coverNotePeriod\": 1,\n" +
                        "  \"crossFunds\": [\n" +
                        "    {\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"funds\": [\n" +
                        "        {\n" +
                        "          \"fundPct\": 100,\n" +
                        "          \"fundTypeId\": 1,\n" +
                        "          \"perilId\": 1\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"fundPct\": 100,\n" +
                        "          \"fundTypeId\": 1,\n" +
                        "          \"perilId\": 2\n" +
                        "        }\n" +
                        "      ]\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"crossSections\": [\n" +
                        "    {\n" +
                        "      \"additionalInformations\": [\n" +
                        "        {\n" +
                        "          \"dataTypeId\": 1,\n" +
                        "          \"length\": 10,\n" +
                        "          \"levelAppl\": \"P\",\n" +
                        "          \"name\": \"Model1234\",\n" +
                        "          \"sectionId\": 1,\n" +
                        "          \"serialNo\": 1,\n" +
                        "          \"isMandatoryPolicy\": \"M\",\n" +
                        "          \"isMandatoryQuotn\": \"O\"\n" +
                        "        },\n" +
                        "        {\n" +
                        "          \"dataTypeId\": 1,\n" +
                        "          \"length\": 10,\n" +
                        "          \"levelAppl\": \"R\",\n" +
                        "          \"name\": \"Make\",\n" +
                        "          \"prodInfoId\": 1,\n" +
                        "          \"sectionId\": 1,\n" +
                        "          \"serialNo\": 1,\n" +
                        "          \"isMandatoryPolicy\": \"M\",\n" +
                        "          \"isMandatoryQuotn\": \"O\"\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"perils\": [\n" +
                        "        {\n" +
                        "          \"amount\": 1,\n" +
                        "          \"calculation\": \"Y\",\n" +
                        "          \"excessPct\": 100,\n" +
                        "          \"excessRate\": 0,\n" +
                        "          \"excessTypeId\": 2,\n" +
                        "          \"isCosAppl\": \"Y\",\n" +
                        "          \"isNcbAppl\": \"Y\",\n" +
                        "          \"perilClassId\": 1,\n" +
                        "          \"perilId\": 1,\n" +
                        "          \"riSumInsurId\": 1,\n" +
                        "          \"riskCalcMethId\": 2,\n" +
                        "          \"sectionId\": 1,\n" +
                        "          \"serialNo\": 1\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"sectionId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"crossStatementLabels\": [\n" +
                        "    {\n" +
                        "      \"statementLabels\": [\n" +
                        "        {\n" +
                        "          \"displayName\": \"Clauses\",\n" +
                        "          \"isMandatory\": \"Y\",\n" +
                        "          \"perilId\": 1,\n" +
                        "          \"stmtDesc\": \"Agreed Value cause description\",\n" +
                        "          \"stmtLabelId\": 1,\n" +
                        "          \"stmtName\": \"Agreed Value\",\n" +
                        "          \"stmtStatusId\": 7\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"stmtDisplayName\": \"Clauses\",\n" +
                        "      \"stmtLabelId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"dfltAddId\": 1,\n" +
                        "  \"endDateId\": 1,\n" +
                        "  \"isBackdateAppl\": \"Y\",\n" +
                        "  \"isCoinsuranceReq\": \"Y\",\n" +
                        "  \"isDocumensReq\": \"Y\",\n" +
                        "  \"isDriveLicenseReq\": \"Y\",\n" +
                        "  \"isFinInterestReq\": \"Y\",\n" +
                        "  \"isNicReq\": \"Y\",\n" +
                        "  \"isNoclaimAppl\": \"Y\",\n" +
                        "  \"isPolicyAddlInfoReq\": \"Y\",\n" +
                        "  \"isPolicyInfoReq\": \"Y\",\n" +
                        "  \"isReinsuranceReq\": \"Y\",\n" +
                        "  \"isRenewable\": \"Y\",\n" +
                        "  \"isRiskAddlInfoReq\": \"Y\",\n" +
                        "  \"isRiskInfoReq\": \"Y\",\n" +
                        "  \"isSpecialTaxAppl\": \"Y\",\n" +
                        "  \"isSumInsuredReq\": \"Y\",\n" +
                        "  \"isSurveyReq\": \"Y\",\n" +
                        "  \"isUsed\": \"Y\",\n" +
                        "  \"labels\": [\n" +
                        "    {\n" +
                        "      \"displayName\": \"Clauses\",\n" +
                        "      \"stmtLabelId\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"displayName\": \"Conditions\",\n" +
                        "      \"stmtLabelId\": 2\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"noClaims\": [\n" +
                        "    {\n" +
                        "      \"calcBasisId\": 1,\n" +
                        "      \"calcMethId\": 1,\n" +
                        "      \"endYearLimit\": 1,\n" +
                        "      \"noAccidents\": \"1\",\n" +
                        "      \"noClaimPct\": 10,\n" +
                        "      \"noClaimRate\": 0,\n" +
                        "      \"operatorId\": 1,\n" +
                        "      \"serialNo\": 1,\n" +
                        "      \"startYearLimit\": 0\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"calcBasisId\": 2,\n" +
                        "      \"calcMethId\": 2,\n" +
                        "      \"endYearLimit\": 1,\n" +
                        "      \"noAccidents\": \"2\",\n" +
                        "      \"noClaimPct\": 0,\n" +
                        "      \"noClaimRate\": 100,\n" +
                        "      \"operatorId\": 2,\n" +
                        "      \"serialNo\": 2,\n" +
                        "      \"startYearLimit\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"otherCharges\": [\n" +
                        "    {\n" +
                        "      \"isEditable\": \"Y\",\n" +
                        "      \"isQuotnAppl\": \"Y\",\n" +
                        "      \"othChrgFreqId\": 2,\n" +
                        "      \"othChargeId\": 1,\n" +
                        "      \"othChrgStatusId\": 7,\n" +
                        "      \"plusMinus\": \"P\",\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"isEditable\": \"Y\",\n" +
                        "      \"isQuotnAppl\": \"Y\",\n" +
                        "      \"othChrgFreqId\": 1,\n" +
                        "      \"othChargeId\": 2,\n" +
                        "      \"othChrgStatusId\": 7,\n" +
                        "      \"plusMinus\": \"M\",\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"prodStatusId\": 7,\n" +
                        "  \"productCode\": \"MWP"+randomInt1+"\",\n" +
                        "  \"productName\": \"Motor Comprehensive Private Car "+randomInt1+"\",\n" +
                        "  \"riAutoAuthAppl\": \"Y\",\n" +
                        "  \"riClaimAutoAuth\": \"Y\",\n" +
                        "  \"riUwAutoAuth\": \"Y\",\n" +
                        "  \"riskDupId\": 2,\n" +
                        "  \"shortName\": \"MWP"+randomInt1+"\",\n" +
                        "  \"specialTaxes\": [\n" +
                        "    {\n" +
                        "      \"calcType\": \"P\",\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"taxTypeId\": 1\n" +
                        "    },\n" +
                        "    {\n" +
                        "      \"calcType\": \"R\",\n" +
                        "      \"taxPct\": 0,\n" +
                        "      \"taxRate\": 1000,\n" +
                        "      \"taxTypeId\": 2\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"taxes\": [\n" +
                        "    {\n" +
                        "      \"calcType\": \"P\",\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"taxTypeId\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
                        "}")
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(201)
                .and()
                .body("message", equalTo("Data added successfully"));

    }
    @Test
    public void createProductBuilderInvalidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\productbuilder\\"+"productbuilder-create-product-builder-invalid.json"))
                .when()
                .post(createEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 2)
    public void getAllWithPaginationProductBuilder() throws IOException {

        baseURL = getURL();
        baseURI = baseURL;
        Response response =
                given()
                        .header("accept", "*/*")
                        .header("authorization", AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .queryParam("pageNo", 0)
                        .queryParam("pageSize", 100)
                        .queryParam("sortBy", "productId")
                        .when()
                        .get(getAllPaginationEndPoint)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("Data List: " + jsonStr);

        int size = response.jsonPath().getList("data.productId").size();
        System.out.println("Data Size: " + size);

        List<Integer> ids = response.jsonPath().getList("data.productId");
        x= ids.get(size-1);
        System.out.println("Last index:" +x);
        for (Integer i : ids) {
            System.out.print(i);
        }
    }

    @Test(priority = 3)
    public void getSingleProductValidTest() throws IOException {
        int productId = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization",AccessTokenHolder.access_token)
                        .contentType(ContentType.JSON)
                        .when()
                        .get(getOneEndPoint, productId)
                        .then()
                        .assertThat().statusCode(200)
                        .and().extract().response();

        String jsonStr = response.getBody().asString();
        System.out.println("GetSingleProduct Data List: " + jsonStr);
    }
    @Test
    public void getSingleProductInvalidTest() throws IOException {
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 4)
    public void modifyProductBuilderValidTest() throws IOException {
        baseURL = getURL();
        baseURI = baseURL;

        Random randomGenerator = new Random();
        int randomInt2 = randomGenerator.nextInt(100);
        given()
                .header("accept", "*/*")
                .header("authorization",AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"claimsAfter\": 1,\n" +
                        "  \"classId\": 1,\n" +
                        "  \"coverNotePeriod\": 1,\n" +
                        "  \"crossFunds\": [\n" +
                        "    {\n" +
                        "      \"fundId\": 1,\n" +
                        "      \"funds\": [\n" +
                        "        {\n" +
                        "          \"fundPct\": 100,\n" +
                        "          \"fundPerilId\": 29,\n" +
                        "          \"fundTypeId\": 1,\n" +
                        "          \"perilId\": 1\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"prodFundId\": 15\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"crossSections\": [\n" +
                        "    {\n" +
                        "      \"additionalInformation\": [\n" +
                        "        {\n" +
                        "           \"dataTypeId\": 1,\n" +
                        "          \"length\": 10,\n" +
                        "          \"levelAppl\": \"P\",\n" +
                        "          \"name\": \"Model\",\n" +
                        "          \"prodInfoId\": 30,\n" +
                        "          \"sectionId\": 1,\n" +
                        "          \"serialNo\": 1,\n" +
                        "          \"visibleToPolicy\": \"M\",\n" +
                        "          \"visibleToQuotn\": \"O\"\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"deletedAdditionalInformation\": [\n" +
                        "       \n" +
                        "      ],\n" +
                        "      \"deletedProductPerils\": [\n" +
                        "       \n" +
                        "      ],\n" +
                        "      \"perils\": [\n" +
                        "        {\n" +
                        "          \"amount\": 1,\n" +
                        "          \"calculation\": \"Y\",\n" +
                        "          \"excessPct\": 100,\n" +
                        "          \"excessRate\": 0,\n" +
                        "          \"excessTypeId\": 2,\n" +
                        "          \"isCosAppl\": \"Y\",\n" +
                        "          \"isNcbAppl\": \"Y\",\n" +
                        "          \"perilClassId\": 1,\n" +
                        "          \"perilId\": 1,\n" +
                        "          \"prodPerilId\": 15,\n" +
                        "          \"riSumInsurId\": 1,\n" +
                        "          \"riskCalcMethId\": 2,\n" +
                        "          \"sectionId\": 1,\n" +
                        "          \"serialNo\": 1\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"prodSecId\": 15,\n" +
                        "      \"sectionId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"crossStatementLabels\": [\n" +
                        "    {\n" +
                        "      \"prodStmtLabelId\": 15,\n" +
                        "      \"statementLabels\": [\n" +
                        "        {\n" +
                        "          \"displayName\": \"Clauses\",\n" +
                        "          \"isMandatory\": \"Y\",\n" +
                        "          \"perilId\": 1,\n" +
                        "          \"prodStmtLabelId\": 15,\n" +
                        "          \"stmtDesc\": \"Agreed Value cause description\",\n" +
                        "          \"stmtLabelId\": 1,\n" +
                        "          \"stmtName\": \"Agreed Value\",\n" +
                        "          \"stmtStatusId\": 1\n" +
                        "        }\n" +
                        "      ],\n" +
                        "      \"stmtDisplayName\": \"Clauses\",\n" +
                        "      \"stmtLabelId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"deletedCrossFunds\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedCrossSections\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedCrossStatementLabels\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedLabels\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedNoClaimBonus\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedOthCharges\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedSpecialTaxes\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"deletedTaxes\": [\n" +
                        "   \n" +
                        "  ],\n" +
                        "  \"dfltAddId\": 1,\n" +
                        "  \"endDateId\": 1,\n" +
                        "  \"isBackdateAppl\": \"Y\",\n" +
                        "  \"isCoinsuranceReq\": \"Y\",\n" +
                        "  \"isDocumensReq\": \"Y\",\n" +
                        "  \"isDriveLicenseReq\": \"Y\",\n" +
                        "  \"isFinInterestReq\": \"Y\",\n" +
                        "  \"isNicReq\": \"Y\",\n" +
                        "  \"isNoclaimAppl\": \"Y\",\n" +
                        "  \"isPolicyAddlInfoReq\": \"Y\",\n" +
                        "  \"isPolicyInfoReq\": \"Y\",\n" +
                        "  \"isReinsuranceReq\": \"Y\",\n" +
                        "  \"isRenewable\": \"Y\",\n" +
                        "  \"isRiskAddlInfoReq\": \"Y\",\n" +
                        "  \"isRiskInfoReq\": \"Y\",\n" +
                        "  \"isSpecialTaxAppl\": \"Y\",\n" +
                        "  \"isSumInsuredReq\": \"Y\",\n" +
                        "  \"isSurveyReq\": \"Y\",\n" +
                        "  \"isUsed\": \"Y\",\n" +
                        "  \"labels\": [\n" +
                        "    {\n" +
                        "      \"displayName\": \"Clauses\",\n" +
                        "      \"prodLabelId\": 29,\n" +
                        "      \"stmtLabelId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"noClaimBonus\": [\n" +
                        "    {\n" +
                        "      \"calcBasisId\": 2,\n" +
                        "      \"calcMethId\": 2,\n" +
                        "      \"endYearLimit\": 1,\n" +
                        "      \"noAccidents\": \"string\",\n" +
                        "      \"noClaimId\": 2,\n" +
                        "      \"noClaimPct\": 0,\n" +
                        "      \"noClaimRate\": 100,\n" +
                        "      \"operatorId\": 2,\n" +
                        "      \"serialNo\": 2,\n" +
                        "      \"startYearLimit\": 0\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"othCharges\": [\n" +
                        "    {\n" +
                        "      \"isEditable\": \"Y\",\n" +
                        "      \"isQuotnAppl\": \"Y\",\n" +
                        "      \"othChrgFreqId\": 1,\n" +
                        "      \"othChrgId\": 2,\n" +
                        "      \"othChrgStatusId\": 7,\n" +
                        "      \"plusMinus\": \"M\",\n" +
                        "      \"prodOthChrgId\": 28,\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"tranTypeId\": 1\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"prodStatusId\": 7,\n" +
                        "  \"productCode\": \"BMW"+randomInt2+"\",\n" +
                        "  \"productId\": "+x+",\n" +
                        "  \"productName\": \"Motor Comprehensive Car"+randomInt2+" update\",\n" +
                        "  \"riAutoAuthAppl\": \"Y\",\n" +
                        "  \"riClaimAutoAuth\": \"Y\",\n" +
                        "  \"riUwAutoAuth\": \"Y\",\n" +
                        "  \"riskDupId\": 2,\n" +
                        "  \"shortName\": \"BMW"+randomInt2+"\",\n" +
                        "  \"specialTaxes\": [\n" +
                        "    {\n" +
                        "      \"calcType\": \"R\",\n" +
                        "      \"prodSpecialTaxId\": 28,\n" +
                        "      \"taxPct\": 0,\n" +
                        "      \"taxRate\": 1000,\n" +
                        "      \"taxTypeId\": 2\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"taxes\": [\n" +
                        "    {\n" +
                        "      \"calcType\": \"P\",\n" +
                        "      \"prodTaxId\": 15,\n" +
                        "      \"taxPct\": 100,\n" +
                        "      \"taxRate\": 0,\n" +
                        "      \"taxTypeId\": 1\n" +
                        "    }\n" +
                        "  ]\n" +
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
                .header("authorization", AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .body(getGeneratedString("\\productbuilder\\"+"productbuilder-modify-product-builder-invalid.json"))
                .when()
                .put(modifyEndPoint)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 5)
    public void getOneProductBuilderValidTest() throws IOException {
        int id = x;
        baseURL = getURL();
        baseURI = baseURL;
        Response response=
                given()
                        .header("accept", "*/*")
                        .header("authorization",AccessTokenHolder.access_token)
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
    public void getOneProductBuilderInvalidTest() throws IOException {
        String id = "id";
        baseURL = getURL();
        baseURI = baseURL;
        given()
                .header("accept", "*/*")
                .header("authorization",AccessTokenHolder.access_token)
                .contentType(ContentType.JSON)
                .when()
                .get(getOneEndPoint, id)
                .then()
                .assertThat().statusCode(400)
                .and()
                .body("error", equalTo("Bad Request"));
    }

    @Test(priority = 6)
    public void deleteProductBuilderValidTest() throws IOException {
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

}

package com.restapi.testCases;

import com.restapi.base.TestBase;
import com.restapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC005_Delete_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void createEmployee() throws InterruptedException {

        logger.info("*******Started TC005_Delete_Employee_Record *********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        response = httpRequest.request(Method.GET, "/employees");

        // First get the JsonPath object instance from the Response interface
        JsonPath jsonPathEvaluator = response.jsonPath();

        // Capture id
        String empID = jsonPathEvaluator.get("[0].id");
        response = httpRequest.request(Method.DELETE, "/delete/" + empID); // Pass Id to delete record

        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody(){
        //logger.info("*******Checking Response Body *********");
        String responseBody = response.getBody().asString();
        //logger.info("Response Body==>" + responseBody);
        Assert.assertEquals(responseBody.contains("Successfully! Record has been deleted"), true);
    }

    @Test
    void checkStatusCode(){
        //logger.info("*******Checking Status Code *********");

        int statusCode = response.getStatusCode(); // Getting status code
        //logger.info("Status Code is ==>" + statusCode); // 200
        Assert.assertEquals(statusCode,200);
    }

    @Test
    void checkStatusLine(){
        //logger.info("*******Checking Status Line *********");

        String statusLine = response.getStatusLine(); // Getting status line
        //logger.info("Status Line is ==>" + statusLine);
        Assert.assertEquals(statusLine,"HTTP/1.1 200 OK");
    }

    @Test
    void checkContentType(){
        //logger.info("*******Checking Content Type *********");

        String contentType = response.header("Content-Type");
        //logger.info("Content Type is ==>" + contentType);
        Assert.assertEquals(contentType,"application/json");
    }

    @Test
    void checkServerType(){
        //logger.info("*******Checking Server Type *********");

        String serverType = response.header("Server");
        //logger.info("Server Type is ==>" + serverType);
        Assert.assertEquals(serverType,"cloudflare");
    }

    @Test
    void checkContentEncoding(){
        //logger.info("*******Checking Content Encoding *********");

        String contentEncoding = response.header("Content-Encoding");
        //logger.info("Content Encoding is ==>" + contentEncoding);
        Assert.assertEquals(contentEncoding,"gzip");
    }

    @AfterClass
    void tearDown(){
        logger.info("******* Finished TC005_Delete_Employee_Record *********");

    }




}

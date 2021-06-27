package com.restapi.testCases;

import com.restapi.base.TestBase;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC002_Get_Single_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    @BeforeClass
    void getAllEmployees() throws InterruptedException {

        logger.info("*******Started TC002_Get_Single_Employee_Record *********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();
        response = httpRequest.request(Method.GET, "/employee/" + empID);
        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody(){
        //logger.info("*******Checking Response Body *********");
        String responseBody = response.getBody().asString();
        //logger.info("Response Body==>" + responseBody);
        Assert.assertEquals(responseBody.contains(empID), true);
    }

    @Test
    void checkStatusCode(){
        //logger.info("*******Checking Status Code *********");

        int statusCode = response.getStatusCode(); // Getting status code
        //logger.info("Status Code is ==>" + statusCode); // 200
        Assert.assertEquals(statusCode,200);
    }

    @Test
    void checkResponseTime(){
        //logger.info("*******Checking Response Time *********");

        long responseTime = response.getTime(); // Getting status Time
        //logger.info("Response Time is ==>" + responseTime);

        /*if(responseTime>2000)
            logger.warn("Response Time is greater than 2000");*/

        Assert.assertTrue(responseTime<6000);

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
    void checkContentLength(){
        //logger.info("*******Checking Content Length *********");

        String contentLength = response.header("Content-Length");
        //logger.info("Content Length is ==>" + contentLength);

        //Assert.assertTrue(Integer.parseInt(contentLength)>1500);
    }

    @AfterClass
    void tearDown(){
        logger.info("******* Finished TC002_Get_Single_Employee_Record *********");

    }










}

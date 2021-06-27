package com.restapi.testCases;

import com.restapi.base.TestBase;
import com.restapi.utilities.RestUtils;
import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class TC004_Put_Employee_Record extends TestBase {

    RequestSpecification httpRequest;
    Response response;

    String empName = RestUtils.empName();
    String empSalary = RestUtils.empSalary();
    String empAge = RestUtils.empAge();

    @BeforeClass
    void createEmployee() throws InterruptedException {

        logger.info("*******Started TC004_Put_Employee_Record *********");

        RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
        httpRequest = RestAssured.given();

        // JSONObject is a class that represents a simple JSON. We can add Key-Value pairs using the put method
        // {"name":"Khaled","salary":"3000","age":"23"}
        JSONObject requestParams = new JSONObject();
        requestParams.put("name", empName); // Cast
        requestParams.put("salary", empSalary);
        requestParams.put("age", empAge);

        // Add a header stating the Request body is a JSON
        httpRequest.header("Content-Type","application/json");

        // Add the Json to the body of the request
        httpRequest.body(requestParams.toJSONString());

        response = httpRequest.request(Method.PUT, "/update/" + empID);
        Thread.sleep(3000);
    }

    @Test
    void checkResponseBody(){
        //logger.info("*******Checking Response Body *********");
        String responseBody = response.getBody().asString();
        //logger.info("Response Body==>" + responseBody);
        Assert.assertEquals(responseBody.contains(empName), true);
        Assert.assertEquals(responseBody.contains(empSalary), true);
        Assert.assertEquals(responseBody.contains(empAge), true);
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
        logger.info("******* Finished TC004_Put_Employee_Record *********");

    }


}

package com.restapi.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.testng.annotations.BeforeClass;

public class TestBase {

    public static RequestSpecification httpRequest;
    public static Response response;
    public String empID = "24"; // 8283 Hard coded - Input for Get details of Single Employee & update employee

    public Logger logger;

    @BeforeClass
    public void setup(){

        logger = Logger.getLogger("EmployeesRestAPI"); // added Logger
        PropertyConfigurator.configure("Log4j.properties"); // added logger
        logger.setLevel(Level.DEBUG);
    }

}

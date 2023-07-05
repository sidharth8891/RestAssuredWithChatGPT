package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.Test;
import org.testng.annotations.BeforeMethod;
import org.apache.log4j.Logger;
public class ApiTests {
    private static final Logger LOGGER = Logger.getLogger(ApiTests.class);

    @BeforeMethod
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Test to get users on page 2")
    public void getUsersPage2Test() {
        // Send GET request and capture the response
        Response response = RestAssured.given()
                .when()
                .get("/users?page=2");

        // Get response status code
        int statusCode = response.getStatusCode();
        LOGGER.info("Status code: " + statusCode);

        // Get response body
        String responseBody = response.getBody().asString();
        LOGGER.info("Response body: " + responseBody);

        // Add assertions as per your requirements
        // For example, to assert the status code is 200
        org.testng.Assert.assertEquals(statusCode, 200);
    }

    @Test(description = "Test to handle invalid endpoint")
    public void testInvalidEndpoint() {
        // Send GET request to an invalid endpoint
        Response response = RestAssured.given()
                .when()
                .get("test/invalid-endpoint");

        // Get response status code
        int statusCode = response.getStatusCode();
        LOGGER.info("Status code: " + statusCode);

        // Add assertions as per your requirements
        // For example, to assert that the status code is in the 4xx series
        org.testng.Assert.assertTrue(statusCode >= 400 && statusCode < 500);
    }

    @Test(description = "Test to handle resource not found")
    public void testResourceNotFound() {
        // Send GET request to a non-existing resource
        Response response = RestAssured.given()
                .when()
                .get("/users/1000");

        // Get response status code
        int statusCode = response.getStatusCode();
        LOGGER.info("Status code: " + statusCode);

        // Add assertions as per your requirements
        // For example, to assert that the status code is 404 (Not Found)
        org.testng.Assert.assertEquals(statusCode, 404);
    }
    //write a test script for user page = 3 and check the status code
    @Test(description = "Test to get users on page 3")
    public void getUsersPage3Test() {
        // Send GET request and capture the response
        Response response = RestAssured.given()
                .when()
                .get("/users?page=3");

        // Get response status code
        int statusCode = response.getStatusCode();
        LOGGER.info("Status code: " + statusCode);

        // Get response body
        String responseBody = response.getBody().asString();
        LOGGER.info("Response body: " + responseBody);

        // Add assertions as per your requirements
        // For example, to assert the status code is 200
        org.testng.Assert.assertEquals(statusCode, 200);
    }
}

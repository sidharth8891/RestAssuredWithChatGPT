package Tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class QueryParam {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @DataProvider(name = "testData")
    public Object[][] getTestData() {
        return new Object[][]{
                {1}, // Valid page number
                {0}, // Invalid page number
                {-1}, // Invalid page number
                {"abc"} // Invalid page number
        };
    }

    @Test(description = "Test different test data sets on query parameter 'page'", dataProvider = "testData")
    public void testQueryParam(Object page) {
        // Send GET request with the query parameter 'page'
        Response response = given()
                .queryParam("page", page)
                .when()
                .get("/users");

        // Get response status code
        int statusCode = response.getStatusCode();

        // Add assertions as per your requirements
        // For example, to assert the expected status code
        assertEquals(statusCode, 200, "Incorrect status code");

        // Add additional assertions or validations as needed
        // For example, to assert the response body or specific values
        // String responseBody = response.getBody().asString();
        // System.out.println("Response body: " + responseBody);
        // ...
    }
}

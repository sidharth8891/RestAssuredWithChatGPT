package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import org.testng.annotations.DataProvider;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class PutTests {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Test positive scenario for PUT method")
    public void testPutMethodPositiveScenario() {
        // Request body
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"zion resident\" }";

        // Send PUT request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/2");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code");

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Add assertions as per your requirements
        // For example, to assert the response body contains the expected values
        assertEquals(response.jsonPath().getString("name"), "morpheus", "Incorrect name value");
        assertEquals(response.jsonPath().getString("job"), "zion resident", "Incorrect job value");
    }
    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"", "zion resident"},
                {"morpheus", ""}
        };
    }

    @Test(description = "Test negative scenarios for PUT method with invalid data", dataProvider = "invalidData")
    public void testPutMethodNegativeScenario(String name, String job) {
        // Request body
        String requestBody = "{ \"name\": \"" + name + "\", \"job\": \"" + job + "\" }";

        // Send PUT request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .put("/users/2");

        // Get response status code
        int statusCode = response.getStatusCode();

        // Add assertions as per your requirements
        // For example, to assert the expected status code for bad request
        assertEquals(statusCode, 400, "Incorrect status code for bad request");
    }
}

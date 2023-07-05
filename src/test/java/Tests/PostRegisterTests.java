package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;
public class PostRegisterTests {
    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Test positive scenario for POST method")
    public void testPostMethodPositiveScenario() {
        // Request body
        String requestBody = "{ \"email\": \"eve.holt@reqres.in\", \"password\": \"pistol\" }";

        // Send POST request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/register");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 200, "Incorrect status code");

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Add assertions as per your requirements
        // For example, to assert the response body contains the expected values
        assertEquals(response.jsonPath().getString("id"), "4", "Incorrect id value");
        assertEquals(response.jsonPath().getString("token"), "QpwL5tke4Pnpja7X4", "Incorrect token value");
    }

    @DataProvider(name = "invalidData")
    public Object[][] getInvalidData() {
        return new Object[][]{
                {"", "pistol"}, // Empty email
                {"eve.holt@reqres.in", ""}, // Empty password
                {"invalidemail", "pistol"}, // Invalid email format
                {"eve.holt@gmail.in", "password"} // Invalid domain
        };
    }

    @Test(description = "Test negative scenarios for POST method with invalid data", dataProvider = "invalidData")
    public void testPostMethodNegativeScenario(String email, String password) {
        // Request body
        String requestBody = "{ \"email\": \"" + email + "\", \"password\": \"" + password + "\" }";

        // Send POST request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/register");

        // Get response status code
        int statusCode = response.getStatusCode();

        // Add assertions as per your requirements
        // For example, to assert the expected status code for bad request
        assertEquals(statusCode, 400, "Incorrect status code for bad request");
    }
}

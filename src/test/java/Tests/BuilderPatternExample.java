package Tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static org.testng.Assert.assertEquals;

public class BuilderPatternExample {


    @Test
    public void getPostsByUserIdNormalApproach() {
        // Set base URI
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com";

        // Send GET request with query parameter
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .queryParam("userId", 1)
                .when()
                .get("/posts");

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Get response status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);

        // Add assertions or perform further operations as needed
        assertEquals(statusCode, 200, "The status code is not 200");
    }

    @Test
    public void getPostsByUserId_BuilderPattern() {
        // Create a new instance of RequestBuilder
        RequestBuilder builder = new RequestBuilder();

        // Set the base URI using the builder
        RequestSpecification requestSpecification = builder
                .setContentType("application/json") // Set the content-type to JSON
                .setBaseUri("https://jsonplaceholder.typicode.com")
                .addQueryParam("userId", "1")
                .build();

        // Send GET request with the built RequestSpecification
        Response response = requestSpecification.when().get("/posts");

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Get response status code
        int statusCode = response.getStatusCode();
        System.out.println("Status code: " + statusCode);

        // Assertion for the status code
        assertEquals(statusCode, 200, "The status code is not 200");

        // Add additional assertions or perform further operations as needed
    }


}

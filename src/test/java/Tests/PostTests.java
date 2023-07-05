package Tests;

import Pojo.RequestBody;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

public class PostTests {
    @Test(description = "Test positive scenario for POST method")
    public void testPostMethodPositiveScenario() {

        // Request body
        String requestBody = "{ \"name\": \"morpheus\", \"job\": \"leader\" }";

        // Send POST request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .when()
                .post("/users");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 201, "Incorrect status code");

        // Get response body
        String responseBody = response.getBody().asString();
        System.out.println("Response body: " + responseBody);

        // Add assertions as per your requirements
        // For example, to assert the response body contains the expected values
        assertEquals(response.jsonPath().getString("name"), "morpheus", "Incorrect name value");
        assertEquals(response.jsonPath().getString("job"), "leader", "Incorrect job value");
        // assertEquals(response.jsonPath().getString("id"), "874", "Incorrect id value");
        // assertEquals(response.jsonPath().getString("createdAt"), "2023-06-22T21:30:51.352Z", "Incorrect createdAt value");
    }

    @Test(description = "Test negative scenario for POST method with invalid request body")
    public void testPostMethodNegativeScenario() {

        // Request body with invalid format
        String invalidRequestBody = "InvalidRequestBody";

        // Send POST request and capture the response
        Response response = given()
                .contentType(ContentType.JSON)
                .body(invalidRequestBody)
                .when()
                .post("/users");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 400, "Incorrect status code");
    }

    @BeforeClass
    public void setUp() {
        RestAssured.baseURI = "https://reqres.in/api";
    }

    @Test(description = "Test POST request with header")
    public void testPostMethodwithheader() {
        // Request body
        String requestBody = "{\n" +
                "    \"name\": \"sidharth\",\n" +
                "    \"languages\": [\"Java\",\"Python\"],\n" +
                "    \"City\": [\n" +
                "        {\n" +
                "            \"Name\": \"bangalore\",\n" +
                "            \"Temperature\": \"30\"\n" +
                "        },\n" +
                "        {\n" +
                "            \"Name\": \"delhi\",\n" +
                "            \"Temperature\": \"40\"\n" +
                "        }\n" +
                "    ]\n" +
                "}";

        // Send POST request and capture the response
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authentication", "bearer gtywsf176534fd61ut4f")
                .body(requestBody)
                .when()
                .post("/users");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 201, "Incorrect status code");

        // Validate response header
        String serverHeaderValue = response.getHeader("Server");
        assertEquals(serverHeaderValue, "cloudflare", "Incorrect Server header value");
    }

    @Test(description = "Test POST request")
    public void testPostMethodwithPojo() {
        // Create the request body object
        RequestBody requestBody = new RequestBody();
        requestBody.setName("sidharth");
        requestBody.setLanguages(List.of("Java", "Python"));

        RequestBody.City city1 = new RequestBody.City();
        city1.setName("bangalore");
        city1.setTemperature("30");

        RequestBody.City city2 = new RequestBody.City();
        city2.setName("delhi");
        city2.setTemperature("40");

        requestBody.setCity(List.of(city1, city2));

        // Send POST request and capture the response
        Response response = given()
                .header("Content-Type", "application/json")
                .header("Authentication", "bearer gtywsf176534fd61ut4f")
                .body(requestBody)
                .when()
                .post("/users");

        // Get response status code
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, 201, "Incorrect status code");

        // Validate response body
        //RequestBody responseBody = response.getBody().as(RequestBody.class);
        //assertEquals(responseBody.getName(), "sidharth", "Incorrect value for name in the response body");
    }

    @Test(description = "Test POST request to create a user")
    public void shouldCreateUserWithValidData() {
        // Prepare the request body
        RequestBody requestBody = createRequestBody();

        // Send POST request and capture the response
        Response response = sendPostRequest(requestBody);

        // Assert the response
        assertResponseStatus(response, 201);
        //assertResponseBody(response.getBody().as(RequestBody.class), "sidharth");
    }

    private RequestBody createRequestBody() {
        RequestBody requestBody = new RequestBody();
        requestBody.setName("sidharth");
        requestBody.setLanguages(List.of("Java", "Python"));

        RequestBody.City city1 = new RequestBody.City();
        city1.setName("bangalore");
        city1.setTemperature("30");

        RequestBody.City city2 = new RequestBody.City();
        city2.setName("delhi");
        city2.setTemperature("40");

        requestBody.setCity(List.of(city1, city2));

        return requestBody;
    }

    private Response sendPostRequest(RequestBody requestBody) {
        return given()
                .header("Content-Type", "application/json")
                .header("Authentication", "bearer gtywsf176534fd61ut4f")
                .body(requestBody)
                .when()
                .post("/users");
    }

    private void assertResponseStatus(Response response, int expectedStatusCode) {
        int statusCode = response.getStatusCode();
        assertEquals(statusCode, expectedStatusCode, "Incorrect status code");
    }

    private void assertResponseBody(RequestBody responseBody, String expectedName) {
        assertEquals(responseBody.getName(), expectedName, "Incorrect value for name in the response body");
    }

}

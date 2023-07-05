package Tests;

import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;

public class RequestBuilder {
    private RequestSpecification requestSpecification;

    public RequestBuilder() {
        // Initialize the RequestSpecification object
        requestSpecification = RestAssured.given();
    }

    public RequestBuilder setBaseUri(String baseUri) {
        // Set the base URI for the request
        requestSpecification.baseUri(baseUri);
        return this;
    }

    public RequestBuilder addQueryParam(String param, String value) {
        // Add query parameter to the request
        requestSpecification.queryParam(param, value);
        return this;
    }

    public RequestBuilder setContentType(String contentType) {
        // Set the content-type header for the request
        requestSpecification.contentType(contentType);
        return this;
    }

    public RequestSpecification build() {
        // Return the built RequestSpecification object
        return requestSpecification;
    }
}
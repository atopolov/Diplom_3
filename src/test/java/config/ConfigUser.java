package config;

import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

import static config.Config.*;
import static io.restassured.RestAssured.*;

// Class for user configuration
public class ConfigUser {
    public static RequestSpecification spec() {
        return given()
                .baseUri(BASE_URI)
                .basePath(API_AUTH)
                .contentType(ContentType.JSON);
    }
}
package com.rest;

import io.restassured.config.LogConfig;
import org.testng.annotations.Test;

import java.util.HashSet;
import java.util.Set;

import static io.restassured.RestAssured.config;
import static io.restassured.RestAssured.given;

public class LoggingTest {

    @Test
    public void request_response_logging() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                log().all().
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void log_only_if_error() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                log().all().
                when().
                get("/workspaces").
                then().
                log().ifError().
                assertThat().
                statusCode(200);
    }

    @Test
    public void log_only_if_validation_fails() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                config(config.logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails())).
                //            log().ifValidationFails().
                        when().
                get("/workspaces").
                then().
                //            log().ifValidationFails().
                        assertThat().
                statusCode(201);
    }

    @Test
    public void logs_blacklist_header() {
        Set<String> headers = new HashSet<String>();
        headers.add("X-Api-Key");
        headers.add("Accept");
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                config(config.logConfig(LogConfig.logConfig().blacklistHeaders(headers))).
                log().all().
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200);
    }
}
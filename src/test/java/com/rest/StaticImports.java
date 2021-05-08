package com.rest;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class StaticImports {

    @Test
    public void simple_test_case() {
        given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                statusCode(200).
                body("name", is(equalTo("Omprakash")),
                        "email", is(equalTo("askomdch@gmail.com")));
    }
}

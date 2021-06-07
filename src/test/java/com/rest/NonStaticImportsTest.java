package com.rest;

import io.restassured.RestAssured;
import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class NonStaticImportsTest {

    @Test
    public void simple_test_case() {
        RestAssured.given().
                baseUri("https://api.postman.com").
                header("x-api-key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                statusCode(200).
                body("name", Matchers.is(Matchers.equalTo("kjh")),
                        "email", Matchers.is(Matchers.equalTo("lkj@gmail.com")));
    }
}

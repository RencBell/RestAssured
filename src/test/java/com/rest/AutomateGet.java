package com.rest;

import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Collections;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

public class AutomateGet {

    @Test
    public void validate_status_code() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().log().all().
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_response_body() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", hasItems("My Workspace"), "workspaces.type", hasItems("personal"));
    }

    @Test
    public void request_response_logging() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                log().headers(). // PRINTS JUST THE HEADERS
                when().
                get("/workspaces").
                then().
                log().body(). // PRINTS JUST THE BODY
                assertThat().
                statusCode(200);
    }

    @Test
    public void validate_response_body_hamcrest_learnings() {
        given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                log().all().
                assertThat().
                statusCode(200).
                body("workspaces.name", containsInAnyOrder("My Workspace"),
                        "workspaces.name", is(not(emptyArray())),
                        "workspaces.name", hasSize(1),
                        //                       "workspaces.name", everyItem(startsWith("My"))
                        "workspaces[0]", hasKey("id"),
                        "workspaces[0]", hasValue("My Workspace"),
                        "workspaces[0]", hasEntry("id", "0fa17046-08e1-4619-8237-e50692c603c2"),
                        "workspaces[0]", not(equalTo(Collections.EMPTY_MAP)),
                        "workspaces[0].name", allOf(startsWith("My"), containsString("Workspace"))
                );
    }

    @Test
    public void extract_response() {
        Response res = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response();
        System.out.println("response = " + res.asString());
    }

    @Test
    public void extract_single_value_from_response() {
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[0].name");
        System.out.println("workspace name = " + name);
        //   System.out.println("workspace name = " + JsonPath.from(res).getString("workspaces[0].name"));
        //    System.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
        //    System.out.println("workspace name = " + res.path("workspaces[0].name"));
    }

    @Test
    public void hamcrest_assert_on_extracted_response() {
        String name = given().
                baseUri("https://api.postman.com").
                header("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                when().
                get("/workspaces").
                then().
                assertThat().
                statusCode(200).
                extract().
                response().path("workspaces[0].name");
        System.out.println("workspace name = " + name);

        //   assertThat(name, equalTo("My Workspace"));
        Assert.assertEquals(name, "My Workspace");
        //   System.out.println("workspace name = " + JsonPath.from(res).getString("workspaces[0].name"));
        //    System.out.println("workspace name = " + jsonPath.getString("workspaces[0].name"));
        //    System.out.println("workspace name = " + res.path("workspaces[0].name"));
    }
}
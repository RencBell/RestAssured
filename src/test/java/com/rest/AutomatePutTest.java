package com.rest;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.matchesPattern;

public class AutomatePutTest {

    @BeforeClass
    public void beforeClass(){
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://api.postman.com").
                addHeader("X-Api-Key", "PMAK-6073f78c9486d000422c421b-d56b8379bb04893a118064a60dd8b4c498").
                setContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        RestAssured.responseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void validate_put_request_bdd_style(){
        String workspaceId = "0fa17046-08e1-4619-8237-e50692c603c2";
        String payload = "{\n" +
                "    \"workspace\": {\n" +
                "        \"name\": \"newWorkspace\",\n" +
                "        \"type\": \"personal\",\n" +
                "        \"description\": \"this is created by Rest Assured\"\n" +
                "    }\n" +
                "}";
        given().
                body(payload).
                pathParam("workspaceId", workspaceId).
        when().
                put("/workspaces/{workspaceId}").
        then().
                log().all().
                assertThat().
                body("workspace.name", equalTo("newWorkspace"),
                        "workspace.id", matchesPattern("^[a-z0-9-]{36}$"),
                        "workspace.id", equalTo(workspaceId));
    }
}

package com.rest;

import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class UploadFileTest {

    //To be able to upload a file, remember to create a new file in the project structure and name it as "temp.txt"

    @Test
    public void upload_file_multipart_form_data(){
        String attributes = "{\"name\":\"temp.txt\",\"parent\":{\"id\":\"123456\"}}";
        given().
                baseUri("https://postman-echo.com").
                multiPart("file", new File("temp.txt")).
                multiPart("attributes", attributes, "application/json").
                log().all().
        when().
                post("/post").
        then().
                log().all().
                assertThat().
                statusCode(200);
    }
}

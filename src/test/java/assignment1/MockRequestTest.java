package assignment1;

import java.util.ArrayList;
import java.util.HashMap;

import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import io.restassured.specification.ResponseSpecification;

public class MockRequestTest {
    ResponseSpecification customResponseSpecification;

    @BeforeClass
    public void beforeClass() {
        RequestSpecBuilder requestSpecBuilder = new RequestSpecBuilder().
                setBaseUri("https://b4479d39-8a10-47d7-bc4b-f0b139bded05.mock.pstmn.io").
                addHeader("x-mock-match-request-body", "true").
                //        setConfig(config.encoderConfig(EncoderConfig.encoderConfig()
                //                .appendDefaultContentCharsetToContentTypeIfUndefined(false))).
                        setContentType("application/json;charset=utf-8").
                        log(LogDetail.ALL);

        RestAssured.requestSpecification = requestSpecBuilder.build();

        ResponseSpecBuilder responseSpecBuilder = new ResponseSpecBuilder().
                expectStatusCode(200).
                expectContentType(ContentType.JSON).
                log(LogDetail.ALL);
        customResponseSpecification = responseSpecBuilder.build();
    }

    @Test
    public void automateComplexJsonPost() {
        ArrayList<Integer> rgba = new ArrayList<Integer>();
        rgba.add(0);
        rgba.add(0);
        rgba.add(0);
        rgba.add(1);
        HashMap<String, Object> code = new HashMap<String, Object>();
        code.put("rgba", rgba);
        code.put("hex", "#FFF");
        HashMap<String, Object> color2 = new HashMap<String, Object>();
        color2.put("color", "white");
        color2.put("category", "value");
        color2.put("code", code);

        ArrayList<Integer> rgba1 = new ArrayList<Integer>();
        rgba1.add(255);
        rgba1.add(255);
        rgba1.add(255);
        rgba1.add(1);
        HashMap<String, Object> code1 = new HashMap<String, Object>();
        code1.put("rgba", rgba1);
        code1.put("hex", "#000");
        HashMap<String, Object> color1 = new HashMap<String, Object>();
        color1.put("color", "black");
        color1.put("category", "hue");
        color1.put("type", "primary");
        color1.put("code", code1);
        HashMap<Object, Object> mainHashMap = new HashMap<Object, Object>();
        given().spec(requestSpecification).when().body(mainHashMap).post("/postAssignment1").then().log().all();
//Process that facilitates conversion to Java Objects is Serialization using library Jackson
    }
}
package api_tests;

import static io.restassured.RestAssured.*;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigurationReader;

import java.util.HashMap;
import java.util.Map;

public class QueryParamTest {


    /**
     *
     /**
     Given Accept type is Json
     And query parameter values are:
                    gender|Female
                    nameContains|e
     When user sends GET request to /api/spartans/search
     Then response status code should be 200
     And response content-type: application/json
     And "Female" should be in response payload
     And "Janette" should be in response payload
     */

    @BeforeEach
    public void setup() {
        baseURI = ConfigurationReader.getProperty("spartan.url");

        //RestAssured otomatik bu degeri gorur.
        //sadece endpoint olan kismi islemde ekliyoruz.
    }


    @DisplayName("GET request to /api/spartans/search")
    @Test
    public void Test1() {

        Response res = given().accept(ContentType.JSON).log().all().
                and().queryParam("gender", "Female").
                and().queryParam("nameContains", "e").
                when().get("/api/spartans/search");

        //2.yol queryParams
        Response res2 = given().accept(ContentType.JSON).log().all().
                and().queryParams("gender", "Female",
                                   "nameContains", "e" ).
                when().get("/api/spartans/search");

        res2.prettyPrint();  // benzer sekilde => log().all().

        //Java Map Object ile
        Map<String, Object> map1 = new HashMap<>();
        map1.put("gender", "Female");
        map1.put("nameContains", "e");

        Response res3 = given().accept(ContentType.JSON).log().all().
                and().queryParams(map1).
                when().get("/api/spartans/search");




        //veriye ait ayrintili bilgileri ekrana basar

        //assertion
        res.then().statusCode(HttpStatus.SC_OK). //200
                and().contentType(ContentType.JSON);

        //And "Female" should be in response payload
        //     And "Janette" should be in response payload

        assertTrue(res.asString().contains("Female"));
        assertTrue(res.asString().contains("Janette"));


    }

}

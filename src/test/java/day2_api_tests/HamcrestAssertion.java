package day2_api_tests;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import groovy.transform.stc.POJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
public class HamcrestAssertion {

    String url = "http://api.zippopotam.us/us/";
    @Test
    public void Test1() {

        Response res = given().accept(ContentType.JSON)
                .when().get(url + "78729");
        res.prettyPrint();

            given().accept(ContentType.JSON)
                    .when().get(url + "78729").then()
                    .assertThat().statusCode(200).
                    and().contentType(ContentType.JSON)
                    .body("country", equalTo("United States"));
            // is veya equalTo ayni sekilde kullanilabilir.

    }

    @Test
    public void SpartanTestHamcrest() {

        String url = "http://107.20.29.195:8000/api";
        /**
         TEST:
         add new class SpartanHamcrestTest
         given accept type is json
         and path id is 24
         When i send get request to /spartans/{id}
         then status code is 200
         and content type is application/json
         and id" is 24,
         "name" is "Julio",
         "gender" is "Male",
         "phone" is 9393139934

         */
        given().accept(ContentType.JSON)
                .and().pathParam("id", 24)
                .when().get(url+"/spartans/{id}")
                .then().statusCode(is(200))
                .and().contentType(is("application/json"))
                .and().body(
                        //actual, expected
                        "id", is(24),
                        "name", equalTo("Julio"),
                        "gender", is("Male"),
                        "phone", is(9393139934L)
                );

    }

}

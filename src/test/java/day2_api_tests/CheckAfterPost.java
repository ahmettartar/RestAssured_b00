package day2_api_tests;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;

public class CheckAfterPost {

    @Test
    public void Test() {
        String url = "http://107.20.29.195:8000/api";
       // String url = "http://107.20.29.195:8000/api"

        given().accept(ContentType.JSON)
                .and().pathParam("id", 110)
                .when().get(url+"/spartans/{id}")
                .then().statusCode(200)
                .and().contentType(ContentType.JSON).
                 and().body("name", is("Hamza"));


    }
}

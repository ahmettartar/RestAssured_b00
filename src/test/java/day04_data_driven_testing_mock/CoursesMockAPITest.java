package day04_data_driven_testing_mock;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.baseURI;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;

public class CoursesMockAPITest {
    @BeforeAll
    public static void setUp(){
        baseURI = "https://23fedf7e-94c5-4101-9633-5e7513977b0c.mock.pstmn.io";
    }

    /**

     */
    @DisplayName("GET /courses mock api")
    @Test
    public void coursesMockTest() {
        given().and().accept(ContentType.JSON)
                .when().get("/eduzeny/1")
                .then().assertThat().statusCode(200)
                .and().contentType("text/html; charset=utf-8")
                //.and().body("classes", hasItems("java", "selenium"),
                        //"batch", is(1))
                .log().all();
    }

}

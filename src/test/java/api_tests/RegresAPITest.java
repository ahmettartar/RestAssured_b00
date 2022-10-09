package api_tests;


import static io.restassured.RestAssured.*;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class RegresAPITest {
    /**

     https://reqres.in/api/users
     When User sends GET Request to
     https://reqres.in/api/users
     Then Response status code should be 200
     And Response body should contain "George"
     And Header Content type should be json

     */
    @DisplayName("Regres Testi")
    @Test
    public void Test1() {
        String url = "https://reqres.in/api/users";

        Response res = get(url);
        res.prettyPrint();

        assertEquals(200, res.statusCode());
        assertTrue(res.asString().contains("George"));
        assertEquals("application/json; charset=utf-8", res.contentType());





    }


}

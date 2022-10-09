package api_tests;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import javax.annotation.meta.When;

public class HelloWorldAPITest {

    /** java doc style

    - When user sends GET Request to :
    https://sandbox.api.service.nhs.uk/hello-world/hello/world

    - Then status code should be 200
    - And "Hello World!" message should be in the response

     Send GET Request to Hello World API
     Then response status code is 200
     And response body contains "Hello World!"

     */

    @DisplayName("Test1 Hello World API Testi")
    @Test
    public void HelloWorldTest() {
        String url = "https://sandbox.api.service.nhs.uk/hello-world/hello/world";

        //RestAssured Library
        Response response = RestAssured.get(url);
        //Response interface

        //System.out.println(response); direkt boyle yazilmaz

        response.prettyPrint();

        //Assertion

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());

        //junit -> assertEquals(exp, act)
        Assertions.assertEquals(200,response.statusCode());
        Assertions.assertEquals("application/json; charset=UTF-8", response.contentType());

        //body check
        //response ->
        // ilk stringe cevirelim . response.asString();

        System.out.println("response.asString() = " + response.asString());

        Assertions.assertTrue(response.asString().contains("Hello World!"));

    }




}

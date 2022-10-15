package day1_api_tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class Spartan {

    String url = "http://107.20.29.195:8000/";

    /**
     *  When I send GET request to http://xxxx:8000/api/hello
     *  Given I send GET request to http://xxxx:8000/api/spartans
     *  Then status code should be 200
     *  When I send GET request to http://xxxx:8000/api/hello
     *  And response body should be equal to "Hello from Sparta"
     *  And content type is "text/plain;charset=UTF-8"
     */

    @Test
    public void Test1(){
        //api da BDD structure kullaniyoruz.
        // Gherkin language: when. and, given, then ...

        when().get(url + "api/spartans"). //response ile calisilir
                then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON);//"application/json

        Response res = when().get(url + "api/spartans");




    }

    @Test
    public void Test2(){

        Response res = when().get(url + "api/hello");
        assertTrue(res.asString().contains("Hello from Sparta"));

        when().get(url + "api/hello").
                then().statusCode(200).
                and().contentType("text/plain;charset=UTF-8");
    }

    @Test
    public void Test3(){
        //3.kisinin bilgilerini alalim
        Response res1 = given().accept(ContentType.JSON). //api, bana json formatinda
                and().pathParam("id", 5).//sonuc gonder
                when().get(url + "api/spartans/{id}");

        res1.prettyPrint();

        //baska assert/validation ile ilgili testler yapilabilir.

        res1.then().assertThat().statusCode(200).
                and().contentType(ContentType.JSON);

        //response daki veriler icindeki degerleri alalim.
        System.out.println("res1.getHeader(\"Date\") = " + res1.getHeader("Date"));
        System.out.println("res1.getHeader(\"Connection\") = " + res1.getHeader("Connection"));


    }

}

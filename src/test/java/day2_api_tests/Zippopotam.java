package day2_api_tests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
public class Zippopotam {
    String url = "http://api.zippopotam.us/us/";

    @Test
    public void Test1() {

        Response res = given().accept(ContentType.JSON)
                .when().get(url + "78729");
        //baseURI -> get("/78729")
        res.prettyPrint();

        //validation-assertion
        res.then().statusCode(200)
                .and().contentType(ContentType.JSON);

        //path and jsonPath ile elemanlara ulasalim
        System.out.println("res.path(\"post code\") = " + res.path("'post code'"));
        System.out.println("res.path(\"places.state\") = " + res.path("places.state"));
        System.out.println("res.path(\"places.'place name'\") = " + res.path("places.'place name'"));

        //jsonPath
        JsonPath jp = res.jsonPath();
        System.out.println("jp.getString(\"'postal code'\") = " + jp.getString("'post code'"));
        System.out.println("jp.getString(\"places.latitude\") = " + jp.getString("places.latitude"));

        //Eger data olmazsa, null verir.Excepttion vermiyor.

    }

    @Test
    public void Test2Germany() {

        String url1 = "http://api.zippopotam.us/DE/";
        Response res = given().accept(ContentType.JSON)
                .when().get(url1 + "65195");
        res.prettyPrint();


        //driver.get("url")
        //driver.get("https://www.google.com");

    }
    //adress -> https://www.zippopotam.us/#where

    //Tr icin practice -> 07040

    @Test
    public void Test2Turkey() {

        String url1 = "http://api.zippopotam.us/TR/";
        Response res = given().accept(ContentType.JSON)
                .when().get(url1 + "34320");
        res.prettyPrint();


        //driver.get("url")
        //driver.get("https://www.google.com");

    }
}

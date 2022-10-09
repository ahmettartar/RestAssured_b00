package api_tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import utils.ConfigurationReader;

public class SpartanPath {

    @BeforeEach
    public void setup(){
        baseURI= ConfigurationReader.getProperty("spartan.url");
    }

    @Test
    public void test1(){

        Response res = given().accept(ContentType.JSON)
                .and().pathParam("id", 5)
                .when().get("/api/spartans/{id}");

        res.prettyPrint();

        /*
        {
    "id": 5,
    "name": "Blythe",
    "gender": "Female",
    "phone": 3677539542
}
         */

        //path() metodu kullanarak herbir elemana ulasalim

        System.out.println("res.path(\"id\") = " + res.path("id"));
        System.out.println("res.path(\"name\") = " + res.path("name"));
        System.out.println("res.path(\"phone\") = " + res.path("phone"));


    }
}

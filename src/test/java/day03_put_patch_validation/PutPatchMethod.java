package day03_put_patch_validation;
import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class PutPatchMethod {

    String url = "http://107.20.29.195:8000/api";

    @Test
    public void TestPut() {
        Response response = given().accept(ContentType.JSON)
                .when().get(url + "/spartans");
        //response.prettyPrint();

        //PUT
        /**
         {
         "id": 128,
         "name": "Hamza",
         "gender": "Male",
         "phone": 59696969660
         //"email": aaa@gmail.com
         }
         */
        Spartan sp = new Spartan();
        sp.setName("Mahir");
        sp.setGender("Male");
        sp.setPhone(59696969660L);


        Response res = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(sp)
                .and().pathParam("id", 128)
                .when().put(url + "/spartans/{id}");


        //validation part
        //      get -> 200
        //      post -> 201
        //      put -> 204
        res.then().statusCode(204).
                and().body(emptyString());





        //res.prettyPrint();
        //put ettgimiz response, empty body.

        //Tum listeyi gormek icin get yapalim.

        when().get(url + "/spartans").prettyPrint();

    }


    @Test
    public void PatchTest() {

        //"gender": "Erkek",
        //1- String :
        // Map
        Map<String, String> map1 = new HashMap<>();
        map1.put("gender", "Erkek");

        //pojo
        Spartan pojo1 = new Spartan();
        pojo1.setGender("Erkek");



        given().accept(ContentType.JSON).log().all()
                .and().contentType(ContentType.JSON)
                .and().body(map1) //body(map1)
                .and().pathParam("id", 128)
                .when().patch(url + "/spartans/{id}");

        //sonucu gormek icin get kullanalim
        when().get(url + "/spartans").prettyPrint();


    }
}

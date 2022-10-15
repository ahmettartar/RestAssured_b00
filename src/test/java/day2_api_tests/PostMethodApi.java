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

import java.util.LinkedHashMap;
import java.util.Map;

public class PostMethodApi {

    String url = "http://107.20.29.195:8000/api";
    @Test
    public void TestPost1() {
        Response response = given().accept(ContentType.JSON)
                .when().get(url + "/spartans");
       // response.prettyPrint();

        //Post method
        //1 -String Yapisi
        String eleman = "{\n" +
                "     \"gender\": \"Male\",\n" +
                "     \"name\": \"Aliali\",\n" +
                "     \"phone\": 1234567425\n" +
                "     }";;

        //using post method
       Response response1 = given().accept(ContentType.JSON)
               .and().contentType(ContentType.JSON)
                .and().body(eleman)
                .when().post(url + "/spartans");
       response1.prettyPrint();

       // 2 ve 3. metodlar => Serialization

       //2.YONTEM
        //POJO CLASS ile
        Spartan sp = new Spartan();
        sp.setName("Ayse");
        sp.setGender("Female");
        sp.setPhone(19393949494L);

        Response response3 = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(sp)
                .when().post(url + "/spartans");
        response3.prettyPrint();

        //3.YONTEM
        //Map object
        Map<String, Object> map1 = new LinkedHashMap<>();
        map1.put("name", "Hamza");
        map1.put("gender", "Male");
        map1.put("phone", 59696969660L);

        Response response4 = given().accept(ContentType.JSON)
                .and().contentType(ContentType.JSON)
                .and().body(map1)
                .when().post(url + "/spartans");
        response4.prettyPrint();

        //validation
        //response4.then().statusCode(201)
               // .and().contentType(ContentType.JSON)
               // .and().body("name", is("Hamza"));

        /*
        Expected: is "Hamza"
        Actual: null
         */
        //Cunku, ben sadece yeni bir eleman olusturduk.
        //yeni bir eleman olusturunca body kismi bos oluyor.

        //Olusturdugumuz adami check ediyoruz.
//        {
//            "id": 110,
//                "name": "Hamza",
//                "gender": "Male",
//                "phone": 59696969660
//        }












    }

}

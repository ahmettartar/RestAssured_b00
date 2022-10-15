package day2_api_tests;

import static io.restassured.RestAssured.*;

import groovy.transform.stc.POJO;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;
import pojo.Spartan;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ConvertToJavaObject {
    String url = "http://api.zippopotam.us/us/";

    @Test
    public void Test1() {

        Response res = given().accept(ContentType.JSON)
                .when().get(url + "78729");
        //baseURI -> get("/78729")
        res.prettyPrint();

    }

    @Test
    public void Test2Spartan(){

        String url = "http://107.20.29.195:8000/";
            //api da BDD structure kullaniyoruz.
            // Gherkin language: when. and, given, then ...


        //Tek kisi icin
        Response resSpartan = given().accept(ContentType.JSON).
                and().pathParam("id", 10).
                when().get(url + "api/spartans/{id}");
        resSpartan.prettyPrint();

        ///elimizde json formatinda sonuc var
        //Convert json to Java Object (Map Object)
        //BUNA DESERIALIZATION
        Map<String, Object> map1 = resSpartan.as(Map.class);
        System.out.println("map1.get(\"id\") = " + map1.get("id"));

        //coklu durum
        //Tek kisi icin
        Response resSpartanAll = given().accept(ContentType.JSON).
                when().get(url + "api/spartans");
        //resSpartanAll.prettyPrint();

        ///elimizde json formatinda sonuc var
        //Convert json to Java Object (Map Object)
        //BUNA DESERIALIZATION
       //List<Map<String, Object>> listAll = resSpartanAll.as(Map.class);




        //SOLUTION:
        //POJO CLASS
        //JSON to Java Object or POJO :> DESERILAZTION


        // Spartan pojo clasimiz hazir.
        //Tek kisi icin POJO
        Response resSpartan3 = given().accept(ContentType.JSON).
                and().pathParam("id", 10).
                when().get(url + "api/spartans/{id}");
        //resSpartan3.prettyPrint();
        Spartan sp = resSpartan3.as(Spartan.class);
        System.out.println("Spartan Objesi: " + sp);
        //POJO objesinden degerleri almak

        System.out.println("sp.getId() = " + sp.getId());
        System.out.println("sp.getName() = " + sp.getName());

        //
        sp.setName("Ali");
        System.out.println("sp.getName() = " + sp.getName());

        //Liste almak
        List<Spartan> listAll =resSpartanAll.jsonPath().getList("", Spartan.class);
        System.out.println(listAll);
        // sonuc liste formatinda: ["ali", element1, ]
        System.out.println("listAll.get(9).getName() = " + listAll.get(9).getName());

        //baska degerler icin practice yapiniz.

        //stream kullanalim
        //sadece gender larin Female olanlari alalim

        List<Spartan> femaleList = listAll.stream().filter(e->e.getGender().equals("Female")).collect(Collectors.toList());
        System.out.println("femaleList = " + femaleList);
        
        //sayisini alalim
        // stream() count sayisini verir
        long numberOfFemale = listAll.stream().filter(e->e.getGender().equals("Female")).count();
        System.out.println("numberOfFemale = " + numberOfFemale); //47

        //baylar
        long numberOfMale = listAll.stream().filter(e->e.getGender().equals("Male")).count();
        System.out.println("numberOfMale = " + numberOfMale); //53

    }
}

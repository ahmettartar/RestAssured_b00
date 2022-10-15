package day2_api_tests;

import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

public class ApiTestJsonPath {

        String url = "https://reqres.in/api/users";

        @Test
        public void RegresApiJsonPathTest() {

            //sending request with bdd cucumber gherkin lang


            Response res = given().accept(ContentType.JSON)
                    .when().get(url);

            res.prettyPrint();

            //jsonPath
            JsonPath jp = res.jsonPath();
            System.out.println("jp.getString(\"page\") = " + jp.getString("page")); //
            //Bu deger String olarak sonuc verir
            //int sayi1 = jp.getString("page"); return type dogrulamis olduk.


            System.out.println("jp.getInt(\"page\") = " + jp.getInt("page"));
            int sayi = jp.getInt("page");
            System.out.println(sayi);

            //getList
            System.out.println("jp.getList(\"data.id\") = " + jp.getList("data.id"));

            //birinci alanin email adresini alalim

            System.out.println("jp.getString(\"data.email[0]\") = " + jp.getString("data.email[0]"));
        }
}

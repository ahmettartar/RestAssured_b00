package day1_api_tests;
import static io.restassured.RestAssured.*;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.Test;

public class RegresPath {

    String url = "https://reqres.in/api/users";

    @Test
    public void Test1() {

        Response res = given().accept(ContentType.JSON)
                .when().get(url);

        res.prettyPrint();

        System.out.println("res.path(\"page\") = " + res.path("page"));
        System.out.println("res.path(\"total_pages\") = " + res.path("total_pages"));

        //data kismi
        System.out.println("res.path(\"data\") = " + res.path("data"));

        //data.id tum idleri verir
        System.out.println("res.path(\"data.id\") = " + res.path("data.id"));
        //res.path("data.id") = [1, 2, 3, 4, 5, 6]

        //listenin 0.elementine gidelim
        //data.id[0] veya data[0].id

        //res.path("data.id[0]") = 1
        //res.path("data[0].id") = 1
        System.out.println("res.path(\"data.id[0]\") = " + res.path("data.id[0]"));
        System.out.println("res.path(\"data[0].id\") = " + res.path("data[0].id"));

        System.out.println("res.path(\"data.first_name[0]\") = " + res.path("data.first_name[0]"));

        //olmayan veri istenirse null verir.
        System.out.println("res.path(\"texas.id\") = " + res.path("texas.id"));

        //res.path("texas.id") = null

    }


}

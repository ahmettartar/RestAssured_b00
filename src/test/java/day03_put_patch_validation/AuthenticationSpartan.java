package day03_put_patch_validation;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class AuthenticationSpartan {

    String url = "http://107.20.29.195:7000/api"; //sifreli

    @Test
    public void Test1() {

        given().accept(ContentType.JSON)
                .and().auth().basic("admin", "admin")
                //.and().queryParam("Username", "admin")
                //.and().queryParam("Password", "admin")
                .when().get(url + "/spartans")
                .then().statusCode(200);
        //Expected status code <200> but was <401>.

        //Sifre Gondermeniz gerekir
        //1. Basic Auth
                    // and().auth().basic("username", "password")
        //QueryParams

        // request: header

    }


}

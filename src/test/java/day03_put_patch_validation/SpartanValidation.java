package day03_put_patch_validation;


import io.restassured.http.ContentType;
import io.restassured.module.jsv.JsonSchemaValidator;
import org.junit.jupiter.api.Test;

import java.io.File;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class SpartanValidation {

    String url = "http://107.20.29.195:8000/api";

    /**
     {
     "id": 128,
     "name": "Hamza",
     "gender": "Male",
     "phone": 59696969660
     //"email": aaa@gmail.com
     }

     Schema web address:
                https://jsonschema.net/app/schemas/0
     */

    @Test
    public void SchemaValidation() {
        given().accept(ContentType.JSON)
                .and().pathParam("id", 128)
                .when().get(url+"/spartans/{id}")
                .then().statusCode(200)
                //burda schema validation yapalim
  .and().body(JsonSchemaValidator.matchesJsonSchema(new File("src/test/resources/JsonSchemas/spartanSchema.json"))
                ).log().all();

    }



}

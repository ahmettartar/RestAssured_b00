package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

@Data //this will add getter/setters for each var
//lombok Library den geliyor.
public class Spartan {
//    @JsonProperty("id")
//    private int isim; //originalde olan id. ama ben programda isim olarak kullanmak
    //istersek bu kolayligi bize sunar.

    private int id;
    private String name;
    private String gender;
    private long phone;
//    private String email;
}


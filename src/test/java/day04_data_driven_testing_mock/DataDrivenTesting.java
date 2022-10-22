package day04_data_driven_testing_mock;
import io.restassured.http.ContentType;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;
import utils.ConfigurationReader;
import utils.ExcelUtil;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.*;


public class DataDrivenTesting {

    //Data Druven Testing
    //1- @ValueSource

    @ParameterizedTest
    @ValueSource(ints = {1,35,8,3,7})
    public void TestSayi(int sayi) {
        System.out.println("Sayi:" + sayi);

    }


    //String icin
    @ParameterizedTest
    @ValueSource(strings = {"1","35","api testing","3"})
    public void TestYazi(String yazi) {
        System.out.println("Sayi:" + yazi);

    }


    @BeforeAll
    public static void setUp() {
        baseURI = ConfigurationReader.getProperty("zipcode.api.url");
    }

    @ParameterizedTest
    @ValueSource(ints = {22102, 22031, 22034, 11209, 15090, 15237,12345,20879,21224,33433})
    public void zipCodeTest(int zipCode) {
        //each time new zipcode value from ValueSource is assigned to zipCode variable
        given().accept(ContentType.JSON)
                .and().pathParam("postal-code", zipCode) //using zipCode as path parameter
                .when().get("/us/{postal-code}")
                .then().assertThat().statusCode(200)
                .log().all();

    }


    //2- @CsvSource (csv: comma seperated value)

    @ParameterizedTest
    @CsvSource({"7, 5, 12",
            "3, 99, 102",
            "32, 44, 76",
            "38, 41, 79"})
    public void basicAddTest(int num1, int num2, int expSum) {
        System.out.println("num1 = " + num1);
        System.out.println("num2 = " + num2);
        System.out.println("expSum = " + expSum);
        int actSum = num1 + num2;
        System.out.println("actSum = " + actSum);
        assertThat(actSum, equalTo(expSum)); //hamcrest assertion
        // assertThat(3+5, is(8));
        //          actual  expected

    }

    //example
    @ParameterizedTest
    @CsvSource({"New York City, NY",
            "Denver, CO",
            "Boston, MA",
            "East Pittsburgh, PA",
            "Raleigh, NC",
            "San Diego, CA",
            "Baltimore, MD",
            "Austin, TX",
            "Fairfax, VA,"})
    public void cityAndStateZipCodeAPITest(String city, String state) {

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("state", state);
        paramsMap.put("city", city);

        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("'place name'", equalTo(city))
                .log().all();
    }


    //3-CsvFileSource


    @ParameterizedTest
    @CsvFileSource(resources = "/ZpCodes.csv", numLinesToSkip = 1)
    public void zipCodeTest(String state, String city, int zipCodeCount) {

        Map<String, String> paramsMap = new HashMap<>();
        paramsMap.put("state", state);
        paramsMap.put("city", city);

        given().accept(ContentType.JSON)
                .and().pathParams(paramsMap)
                .when().get("/us/{state}/{city}")
                .then().assertThat().statusCode(200)
                .and().contentType(ContentType.JSON)
                .and().body("places", hasSize(zipCodeCount)) //is(zipCodeCount)
                .log().all();
    }

    //4- methodSource

    /**
     * data provider method.
     */
    public static List<String> getCountries() {
        List<String> countries = Arrays.asList("Canada","USA","France","Turkey","Mexico"
                ,"Egypt","Germany");

        return countries;
    }

    @ParameterizedTest
    @MethodSource("getCountries") //Metodun adi girilir
    public void countriesTest(String countryName) { //List<String>
        System.out.println("countryName = " + countryName);
        System.out.println("Isim uzunlugu: " + countryName.length());
    }


    //5- Excel Yardimi ile okumak
    //Excel dosyalarini Apachi POI ile okuyoruz.

    @Test
    public void readBookItUsersTest() {
        String filePath = "src/test/resources/BookItQa3.xlsx";
        ExcelUtil excelUtil = new ExcelUtil(filePath,"QA3");
        System.out.println("columns = " + excelUtil.getColumnsNames());

        int rowsCount = excelUtil.rowCount();
        System.out.println("rowsCount = " + rowsCount);

        System.out.println(excelUtil.getCellData(1,1));

        List<Map<String,String>> data = excelUtil.getDataList();
        System.out.println("data = " + data);
    }





}

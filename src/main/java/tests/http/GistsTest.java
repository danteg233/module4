package tests.http;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GistsTest {
    @BeforeTest
    public void init(){
        RestAssured.baseURI = "https://api.github.com";
        RestAssured.given().auth().basic("danteg233", "test123").expect().statusCode(200).when().get("users/danteg233");
    }

    @Test
    public void smt(){
        Response rp = RestAssured.given().post("/gists", );
        int statusCode = rp.getStatusCode();
        System.out.println(statusCode);


    }
}

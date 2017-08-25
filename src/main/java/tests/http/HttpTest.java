package tests.http;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import tests.http.business_objects.User;

public class HttpTest {
    @BeforeTest
    public void init(){
        RestAssured.baseURI = "https://jsonplaceholder.typicode.com/";
    }

    @Test
    public void checkStatusCode(){
        Response rp = RestAssured.given().get("/users").andReturn();
        int statusCode = rp.getStatusCode();
        Assert.assertEquals(statusCode, 200);
    }

    @Test
    public void checkHeader(){
        Response rp = RestAssured.given().get("/users").andReturn();
        String headerType = rp.getHeader("content-type");
        Assert.assertTrue(headerType.contains("application/json; charset=utf-8"));
    }

    @Test
    public void httpResponseBody(){
        Response rp = RestAssured.given().get("/users").andReturn();
        User[] users = rp.as(User[].class);
        Assert.assertEquals(users.length, 10);
    }
}

package com.mytests.spring.security.customExpressionHandler;

import io.restassured.RestAssured;
import io.restassured.authentication.FormAuthConfig;
import io.restassured.response.Response;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;

import static io.restassured.RestAssured.given;

@SpringBootTest
class SpringSecurityCustomExpressionhandlerApplicationTests {

    @BeforeAll
    public static void setup() {
        RestAssured.baseURI = "http://localhost:8080";
    }

    @Test
    public void getTest1_success() {
        String username = "Ivan_1";
        String password = "password";
        Response response = given()
                .auth()
                .form(username, password, new FormAuthConfig("/login","username","password"))
                .when()
                .get("/test1")
                .then()
                .extract().response();
        System.out.println(response.getBody().print());
        Assertions.assertEquals(200, response.statusCode());
    }
    @Test
    public void getTest1_failure() {
        String username = "Maria_4";
        String password = "password";
        Response response = given()
                .auth()
                .form(username, password, new FormAuthConfig("/login","username","password"))
                .when()
                .get("/test1")
                .then()
                .extract().response();
        System.out.println(response.getBody().print());
        Assertions.assertEquals(403, response.statusCode());
    }

    @Test
    public void getTest3_success() {
        String username = "Maxim_9";
        String password = "password";
        Response response = given()
                .auth()
                .form(username, password, new FormAuthConfig("/login","username","password"))
                .when()
                .get("/test3")
                .then()
                .extract().response();
        System.out.println(response.getBody().jsonPath().getString("message"));
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("1", response.getBody().jsonPath().getString("principal.principal.employee.secLevel"));
    }

    @Test
    public void testPosting() {
        String username = "Marina_11";
        String password = "password";
        Response response = given()
                .log().uri()
                .auth()
                .form(username, password, new FormAuthConfig("/login","username","password"))
                //contentType(MediaType.APPLICATION_JSON_VALUE) // no injection
                .contentType("application/json")  // ok
                .body("{\n" +
                        "        \"id\": 100,\n" +
                        "        \"firstName\": \"Varvara\",\n" +
                        "        \"lastName\": \"Varvarova\",\n" +
                        "        \"team\": \"AAA\",\n" +
                        "        \"title\": \"developer\",\n" +
                        "        \"salary\": 300\n" +
                        "      }")
                .post("/addEmployee/AAA");
        System.out.println(response.getBody().print());
        Assertions.assertEquals(200, response.statusCode());
        Assertions.assertEquals("Varvara", response.jsonPath().getString("firstName"));
        Assertions.assertEquals("AAA", response.jsonPath().getString("team"));
        Assertions.assertEquals("Varvarova", response.jsonPath().getString("lastName"));
    }
    /*@Test
    public void testPostingXML() {
        String username = "Marina_11";
        String password = "password";
        Response response = given()
                .log().uri()
                .auth()
                .form(username, password, new FormAuthConfig("/login","username","password"))
                .contentType(MediaType.APPLICATION_XML_VALUE) // no injection
                //.contentType("application/xml")  // ok
                .body("<aaa>foo</aaa>")
                .post("/addEmployeeXML/AAA");
        System.out.println(response.getBody().print());
        Assertions.assertEquals(200, response.statusCode());

    }*/
}

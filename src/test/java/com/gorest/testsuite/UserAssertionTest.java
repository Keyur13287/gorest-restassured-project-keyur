package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class UserAssertionTest  {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in/public/v2";
        //RestAssured.port=3035;
        response=given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "20")
                .get("/users")
                .then().log().all().statusCode(200);
    }

    // Verify if the total record is 20
    @Test
    public void test001() {
        response.body("", hasSize(20));
    }

    // Verify if the name of id = 7015044 is equal to ”Gorakhanatha Pillai”
    @Test
    public void test002() {
        response.body("find{it.id == 7018183}.name", equalTo("Vidya Dutta"));
    }

    // Check the single ‘Name’ in the Array list (Gorakhanatha Pillai)
    @Test
    public void test003() {
        response.body("name", hasItem("Fr. Aagam Embranthiri"));
    }

    // Check the multiple ‘Names’ in the ArrayList (Gorakhanatha Pillai, Daksha Patel, Nawal Johar )
    @Test
    public void test004() {
        response.body("name", hasItems("Chakravartee Varma", "Udit Agarwal", "Prof. Ujjwal Khan"));
    }

    // Verify the email of userid = 7015044 is equal “pillai_gorakhanatha@prohaska.test”
    @Test
    public void test005() {
        response.body("find{it.id == 7018180}.email", equalTo("embranthiri_fr_aagam@hagenes.test"));
    }

    // Verify the status is “inActive” of user name is “Gorakhanatha Pillai”
    @Test
    public void test006() {
        response.body("find{it.name == 'Udit Agarwal'}.status", equalTo("inactive"));
    }


    // Verify the Gender = female of user name is “Gorakhanatha Pillai”
    @Test
    public void test007() {
        response.body("find{it.name == 'Vidya Dutta'}.gender", equalTo("female"));
    }


}

package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

import static io.restassured.RestAssured.given;

public class UserExtractionTest {

    /***
     * 1. Extract the All Ids
     * 2. Extract the all Names
     * 3. Extract the name of 5
     * th object
     * 4. Extract the names of all object whose status = inactive
     * 5. Extract the gender of all the object whose status = active
     * 6. Print the names of the object whose gender = female
     * 7. Get all the emails of the object where status = inactive
     * 8. Get the ids of the object where gender = male
     * 9. Get all the status
     * 10. Get email of the object where name = Lal Dwivedi
     * 11. Get gender of id = 5914189
     */
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
                .then().statusCode(200);
    }
    // 1) Extract the value of limit
    @Test
    public void test001() {
        List<Integer> id =response.extract().path("id");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Id is : "+ id);
        System.out.println("------------------End of Test---------------------------");

    }
    //Extract the all Names
    @Test
    public void test002() {
        List<Integer> names =response.extract().path("name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Id is : "+ names);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the name of 5
    @Test
    public void test003() {
        String name =response.extract().path("[4].name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Id is : "+ name);
        System.out.println("------------------End of Test---------------------------");

    }
    //3. Extract the names of all object whose status = inactive
    @Test
    public void test004() {
        List<String> name =response.extract().path("findAll{it.status=='inactive'}.name");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("The value of Id is : "+ name);
        System.out.println("------------------End of Test---------------------------");

    }
    //5. Extract the gender of all the object whose status = active
    @Test
    public void test005() {
        List<?> allActiveGenderStatus = response.extract().path("findAll{it.status == 'active'}.gender");

        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Status of Active: " + allActiveGenderStatus);
        System.out.println("------------------End of Test---------------------------");

    }

    //6. Print the names of the object whose gender = female
    @Test
    public void test006() {
        List<?> allObjectWithGenderFemale = response.extract().path("findAll{it.gender == 'female'}.name");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allObjectWithGenderFemale);
        System.out.println("------------------End of Test---------------------------");

    }

    //7. Get all the emails of the object where status = inactive
    @Test
    public void test07() {
        List<?> allEmailsOfStatusInactive = response.extract().path("findAll{it.status == 'inactive'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Inactive : " + allEmailsOfStatusInactive);
        System.out.println("------------------End of Test---------------------------");
    }

    //8. Get the ids of the object where gender = male
    @Test
    public void test08() {
        List<?> allIdWithGenderMale = response.extract().path("findAll{it.gender == 'male'}.id");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender is Female: " + allIdWithGenderMale);
        System.out.println("------------------End of Test---------------------------");
    }

    //9. Get all the status
    @Test
    public void test09() {
        List<Integer> statusList = response.extract().path("status");
        statusList.size();
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("All Status list : " + statusList);
        System.out.println("------------------End of Test---------------------------");
    }

    //10. Get email of the object where name = Dev Tagore
    @Test
    public void test10() {
        String email = response.extract().path("find{it.id == '7018192'}.email");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Email Of Dev Tagore : " + email);
        System.out.println("------------------End of Test---------------------------");
    }


    //11. Get gender of id = 7018191

    @Test
    public void test11() {
        List<?> getGenderOfId = response.extract().path("findAll{it.id == '7018186' }.gender");
        System.out.println("------------------StartingTest---------------------------");
        System.out.println("Gender Of Id 7018191 : " + getGenderOfId);
        System.out.println("------------------End of Test---------------------------");
    }

}

package com.gorest.crudtest;

import com.gorest.model.Userpojo;
import com.gorest.testbase.TestBase;
import com.gorest.utils.TestUtils;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
public class UserCRUDTest extends TestBase {
    static ValidatableResponse response;
    static int id;
    static String name = TestUtils.getRandomValue() + " smita";
    static String email = "smita"+TestUtils.getRandomValue()+"@gmail.com";
    static String gender = "female";
    static String status = "active";
    @Test(priority = 0)
    public void verifyUserCreatedSuccessfully() {
        Userpojo userPojo = new Userpojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);
        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 36dbd20543eb8aad4d3d205601019f5ae4b4baf6f7fc208d7bf202962bf21e1e")
                .when()
                .body(userPojo)
                .post("/users");
        response.then().log().ifValidationFails().statusCode(201);
        response.prettyPrint();
        id = response.jsonPath().get("id");
    }
    @Test(priority = 1)
    public void verifyUserReadSuccessfully() {
        Response response = given()
                .when()
                .get("/users");
        response.then().statusCode(200);
        response.prettyPrint();
    }
    @Test(priority = 2)
    public void verifyUserUpdateSuccessfully() {
        name = name + "Updated";
        gender = "Male";
        status = "active";
        Userpojo userPojo = new Userpojo();
        userPojo.setName(name);
        userPojo.setEmail(email);
        userPojo.setGender(gender);
        userPojo.setStatus(status);

        Response response = given().log().ifValidationFails()
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 36dbd20543eb8aad4d3d205601019f5ae4b4baf6f7fc208d7bf202962bf21e1e")
                .pathParam("id", id)
                .when()
                .body(userPojo)
                .put("/users/{id}");
        response.prettyPrint();
        response.then().log().ifValidationFails().statusCode(200);
    }
    @Test(priority = 3)
    public void VerifyUserDeleteSuccessfully() {
        Response response = given().log().all()
                .pathParam("id",id)
                .header("Content-Type", "application/json")
                .header("Authorization", "Bearer 36dbd20543eb8aad4d3d205601019f5ae4b4baf6f7fc208d7bf202962bf21e1e")
                .when()
                .delete("/users/{id}");
        response.then().statusCode(204);
        response.prettyPrint();
    }

}
 
 
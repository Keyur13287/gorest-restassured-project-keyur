package com.gorest.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasItems;

public class PostsAssertionTest {
    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI="https://gorest.co.in/public/v2";
        //RestAssured.port=3035;
        response=given()
                .when()
                .queryParam("page", "1")
                .queryParam("per_page", "25")
                .get("/posts")
                .then().statusCode(200);
    }
    /**
     * Verify the if the total record is 25
     * 2. Verify the if the title of id = 93997 is equal to ”Demitto conqueror atavus argumentum corrupti
     * cohaero libero.”
     * 3. Check the single user_id in the Array list (5914249)
     * 4. Check the multiple ids in the ArrayList (5914243, 5914202, 5914199)
     * 5. Verify the body of userid = 5914197 is equal “Desidero vorax adsum. Non confero clarus.
     * Velut defessus acceptus. Alioqui dignissimos alter. Tracto vel sordeo. Vulpes curso tollo. Villa usus
     * vos. Terreo vos curtus. Condico correptius praesentium. Curatio deripio attero. Tempus creptio
     * tumultus. Adhuc consequatur undique. Adaugeo terminatio antiquus. Stultus ex temptatio. Autus
     * acerbitas civitas. Comptus terminatio tertius. Utpote fugit voluptas. Sequi adulescens caecus.”
     */

    @Test
    public void test001(){
        response.body("size()",equalTo(25));
    }
    //2. Verify the if the title of id =  139915 is equal to ”Defaeco in carbo decet audeo volutabrum corroboro.
    @Test
    public void test2() {
        response.body("[0].title", equalTo("Urbs clibanus aurum apostolus ea tubineus alter amo."));
    }

    //3. Check the single user_id in the Array list (7015124)
    @Test
    public void test3() {
        response.body("[0].user_id", equalTo(7018214));
    }

    //4. Check the multiple ids in the ArrayList (139915, 139914, 139909)
    @Test
    public void test4() {
        response.body("id", hasItems(140329, 140328, 140300));
    }

    //5. Verify the body of userid = 139915 is equal “Ultio cattus patrocinor. Sint cubitum vapulus. Valetudo tertius excepturi. Convoco delego sollers. Supellex antepono admoveo. Culpa appello deleniti. Aro dolores certo. Avaritia testimonium degero. Vir culpa temeritas. Vel modi theca. Voluptas vado error. Abduco sulum desipio. Suffoco quibusdam spiritus. Ea convoco velit..
    @Test
    public void test5() {
        response.body("[0].body", equalTo("Tondeo recusandae cornu. Laborum cernuus urbs. Cedo trado est. Ipsum sperno careo. Tergo amicitia cunae. Cetera tabesco benigne. Deinde capillus valens. Inflammatio tempore consequatur. Absconditus corona contabesco. Artificiose vita volup. Voluptatem utrimque videlicet. Suffoco terror summopere."));
    }



}

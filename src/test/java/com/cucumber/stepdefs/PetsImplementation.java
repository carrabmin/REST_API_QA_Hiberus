package com.cucumber.stepdefs;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItems;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.Serializable;
import java.util.HashMap;


public class PetsImplementation implements Serializable {
    private Response responseGetPet = null;
    private String bodyResponse = null;
    private String status;
    private String photoUrl;

    @Before
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/pet/";
    }

    @Given("the following get request that brings us the pet by id")
    public Response getPet() {
        responseGetPet = given().given().get("https://petstore.swagger.io/v2/pet/102023");
        //bodyResponse = responseGetPet.getBody().asString();
        return responseGetPet;
    }

    @And("the response is 200")
    public void validateResponse() {

        assertTrue("The response is not 200", responseGetPet.statusCode()==200);
    }

    @And("the id is {int}")
    public void theIdIs(Integer id) {
        responseGetPet.then().body("$", hasItems(id));
    }

    @And("the body response contains category name {string}")
    public void theBodyResponseContainsCategoryName(String categoryName) {
        responseGetPet.then().body("category.name",hasItems(categoryName));
    }

    @And("the body response contains name {string}")
    public void theBodyResponseContainsNameName(String name) {
        responseGetPet.then().body("name", hasItems(name));
    }


    @Then("the body reponse contains photoUrl {string}")
    public void theBodyReponseContainsPhotoUrlPhotoUrl(String photoUrl) {
        JsonPath jsonPathUrl = new JsonPath(responseGetPet.body().asString());
        String jsonUserName = jsonPathUrl.getString("photoUrls");
        assertEquals("The value of the photoUrl field is not what is expected", photoUrl, jsonUserName);

    }

}

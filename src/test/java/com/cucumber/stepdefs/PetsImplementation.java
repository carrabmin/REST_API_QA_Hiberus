package com.cucumber.stepdefs;

import static io.restassured.RestAssured.given;

import static org.junit.Assert.*;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;
import java.io.Serializable;


public class PetsImplementation {
    private Response responsePet = null;
    private JsonPath jsonPathPet = null;

    private final String PET_ID = "101";

    @Before("@pet")
    public void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/";
    }

    //Post Test
    @Given("POST request to add pet")
    public void postAddPet(){
        File filePostPet = new File("src/main/resources/data/pet/JSON/pet/postPet.json");
        responsePet = given().contentType(ContentType.JSON).body(filePostPet).post("/pet");
    }

    @Then("the status code response - POST pet request")
    public void validatePostAddPetStatus() {
        assertTrue("The response is not 200",responsePet.statusCode() == 200);
    }

    @And("POST request body response contains the id {string} and name {string}")
    public void validateResponsePostAddPet(String petId, String petName) {
        jsonPathPet = new JsonPath(responsePet.body().asString());
        String jsonPathPetId = jsonPathPet.getString("id");
        String jsonPathPetName = jsonPathPet.getString("name");
        assertEquals("Error -  idPet is not equals as expected", petId , jsonPathPetId);
        assertEquals("Error - namePet is not equals as expected", petName, jsonPathPetName);
    }

    // GET Test
    @Given("the following GET request returns pet by id")
    public void getPetByd(){
        responsePet =  given().log().all().get("/pet/" + PET_ID);
    }

    @Then("the status code is 200 - GET pet request by id")
    public void validateGetPetByIdStatus() {
        assertTrue("The response is not 200",responsePet.statusCode() == 200);
    }

    @And("GET pet by id request body response contains {string} and {string}")
    public void validateResponseGetPetByID(String petId, String petName) {
        jsonPathPet = new JsonPath(responsePet.body().asString());
        String jsonPathPetId = jsonPathPet.getString("id");
        String jsonPetPathName = jsonPathPet.getString("name");
        assertEquals("Error - idPet is not equals as expected", petId, jsonPathPetId);
        assertEquals("Error - idName is not equals as expected", petName, jsonPetPathName);
    }

    //PUT Test
    @Given("PUT request update a pet")
    public void putUpdatePet(){
        File file = new File("src/main/resources/data/pet/JSON/pet/putPet.json");
         responsePet = given().contentType(ContentType.JSON).body(file).put("/pet");
    }

    @Then("the status code is 200 - PUT pet request")
    public void validatePutUpdatePetStatus() {
        System.out.println("Staus code is " + responsePet.statusCode());
        assertTrue("The response is not 200",responsePet.statusCode() == 200);
    }

    @And("PUT pet request body response contains id {string} and name {string}")
    public void validateResponsePutUpdatePet(String petId,String petName) {
        jsonPathPet = new JsonPath(responsePet.body().asString());
        String jsonPathPetId = jsonPathPet.getString("id");
        String jsonPathPetName = jsonPathPet.getString("name");
        assertEquals("Error -  idPet is not equals as expected", petId, jsonPathPetId);
        assertEquals("Error - namePet is not equals as expected", petName, jsonPathPetName);
    }

    // DELETE Test
    @Given("DELETE request deletes pet by id")
    public void deletePetByID(){
        responsePet =  given().log().all().delete("/pet/" + PET_ID);
    }

    @Then("the status code is 200 - DELETE pet request by id")
    public void validateDeletePetByIDStatus() {

        assertTrue("The response is not 200",responsePet.statusCode() == 200);
    }

    @And("DELETE pet request by id body response contains the id {string}")
    public void validateResponseDeletePetByID(String petId) {
        jsonPathPet = new JsonPath(responsePet.body().asString());
        String jsonPathPetId = jsonPathPet.getString("message");
        assertEquals("Error - idPet is not equals as expected", petId, jsonPathPetId);
    }
}

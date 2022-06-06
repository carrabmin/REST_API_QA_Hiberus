package com.cucumber.stepdefs;

import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.io.File;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasKey;
import static org.junit.Assert.*;

public class StoresImplementation {

    private Response responseStore = null;
    private JsonPath jsonPathOrder = null;

    private final String ORDER_ID = "101101";

    @Before("@store")
    public static void before() {
        RestAssured.baseURI = "https://petstore.swagger.io/v2/store";
    }

    //POST Test
    @Given("POST request to add a order")
    public void postAddOrder() {
        File file = new File("src/main/resources/data/pet/JSON/store/postStore.json");
        responseStore = given().contentType(ContentType.JSON).body(file).post("/order");
    }

    @Then("the status code response - POST order request")
    public void validatePostAddOrderStatus() {
        assertTrue("The response is not 200", responseStore.statusCode() == 200);
    }

    @And("POST order request body response contains the {string} and {string}")
    public void validateResponsePostAddOrder(String orderId, String petId) {
        jsonPathOrder = new JsonPath(responseStore.body().asString());
        String jsonPathOrderId = jsonPathOrder.getString("id");
        String jsonPathPetId = jsonPathOrder.getString("petId");
        assertEquals("Error -  orderId is not equals as expected", orderId, jsonPathOrderId);
        assertEquals("Error - petId is not equals as expected", petId, jsonPathPetId);
    }

    //GET Test
    @Given("the following GET request returns order pet by id")
    public void geteOrderByID() {
        responseStore = given().log().all().get("/order/" + ORDER_ID);
    }

    @And("the status code is 200 - GET order request by id")
    public void validateGetOrderByIDStatus() {
        assertTrue("The response is not 200", responseStore.statusCode() == 200);
    }

    @Then("GET order by id request body response contains {string} and {string}")
    public void validateResponseGetOrderByID(String orderId, String petId) {
        jsonPathOrder = new JsonPath(responseStore.body().asString());
        String jsonPathOrderId = jsonPathOrder.getString("id");
        String jsonPathPetId = jsonPathOrder.getString("petId");
        assertEquals("Error - orderId is not equals as expected", orderId, jsonPathOrderId);
        assertEquals("Error - petId is not equals as expected", petId, jsonPathPetId);
    }

    //DELETE Test
    @Given("DELETE request delete order by id")
    public void deleteOrderByID() {
        responseStore = given().log().all().delete("/order/" + ORDER_ID);
    }

    @Then("the status code is 200 - DELETE order request by id")
    public void validateDeleteOrderByIDStatus() {
        assertTrue("The response is not 200", responseStore.statusCode() == 200);
    }

    @And("DELETE order request by id body response contains the {string}")
    public void validateResponseDeleteOrderByID(String petId) {
        jsonPathOrder = new JsonPath(responseStore.body().asString());
        String jsonPathOrderId = jsonPathOrder.getString("message");
        assertEquals("Error - idPet is not equals as expected", petId, jsonPathOrderId);
    }


    //GET Inventory Test
    @Given("the following GET request returns order inventory")
    public void getOrderInventory() {
        responseStore = given().log().all().get("/inventory");
    }

    @Then("the status code is 200 - GET inventory request")
    public void validateResponseGetOrderInventory() {
        assertTrue("The response is not 200", responseStore.statusCode() == 200);
    }

    @And("GET order inventory body response request is not empty")
    public void validateBodyGetOrderInventory() {
        assertNotNull(responseStore.body());
        assertNotEquals("", responseStore.body().toString());
    }
}
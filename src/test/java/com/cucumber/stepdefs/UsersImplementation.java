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
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class UsersImplementation {

    private Response responseUser = null;
    private JsonPath jsonPathUser = null;

    private final String USER_NAME = "Carlos";

    private final String PASSWORD = "654321";



    @Before("@user")
    public static void before(){
        RestAssured.baseURI="https://petstore.swagger.io/v2/user";
    }

    //Post Array Test
    @Given("POST user request to add user with array")
    public void postAddUserArray(){
        File file = new File("src/main/resources/data/pet/JSON/user/postUserArray.json");
        responseUser = given().contentType(ContentType.JSON).body(file).post("/createWithArray");
    }

    @Then("the status code response - POST user request with array")
    public void validatePostAddUserArrayStatus() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("POST user request with array contains ok in the message")
    public void validateResponsePostAddUserArray() {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserMessage = jsonPathUser.getString("message");
        assertEquals("Error -  message is not equals as expected", "ok", jsonPathUserMessage);
    }

    //Post list test
    @Given("POST user request with list")
    public void postAddUserList(){
        File file = new File("src/main/resources/data/pet/JSON/user/postUserArray.json");
        responseUser = given().contentType(ContentType.JSON).body(file).post("/createWithList");
    }

    @Then("the status code response - POST user request with list")
    public void validatePostAddUserListStatus() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("POST request with list body response contains ok in the message")
    public void validateResponsePostAddUserList() {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserMessage = jsonPathUser.getString("message");
        assertEquals("Error -  message is not equals as expected","ok", jsonPathUserMessage);
    }

    //GET user test
    @Given("GET user request by name")
    public void getUserByName(){
        responseUser = given().log().all().get("/" + USER_NAME);
    }

    @Then("the status code is 200 - GET user request by name")
    public void validateGetUserByNameStatus() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("GET user by id request body response contains name {string}")
    public void validateResponseGetUserByName(String userName) {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserName = jsonPathUser.getString("username");
        assertEquals("The value of the name field is not what is expected", userName, jsonPathUserName);
    }

    //PUT user test
    @Given("PUT request update a user")
    public void putUserUpdate() {
        File file = new File("src/main/resources/data/pet/JSON/user/putUser.json");
        responseUser =  given().contentType(ContentType.JSON).body(file).put("/" + USER_NAME);
    }

    @Then("the status code is 200 - PUT user request")
    public void validatePutUserUpdateStatus() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("PUT request body response contains {string} when user is updated")
    public void validateResponsePutUserUpdate(String userId) {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserId = jsonPathUser.getString("message");
        assertEquals("Error -  userId is not equals as expected", userId, jsonPathUserId);
    }

    //DELETE Test
    @Given("DELETE request deletes pet by name")
    public void deleteUserByName(){
        responseUser =  given().log().all().delete("/" + USER_NAME);
    }

    @Then("the status code is 200 - DELETE pet request by name")
    public void validateDeleteUserByNameStatus() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("DELETE user request by name body response contains the message {string}")
    public void validateResponseDeleteUserByName(String userName) {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserName = jsonPathUser.getString("message");
        assertEquals("Error - the message is not equals as expected", userName, jsonPathUserName);
    }

    //USER test login
    @Given("GET request user logging")
    public void userLogin(){
        responseUser = given().log().all().param("username",USER_NAME).param("password", PASSWORD).get("/login");
    }

    @Then("the status code is 200 - GET user logging request")
    public void validateResponseUserLogin() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("GET logging user body response request contains contains 'logged in user session' in the message")
    public void validateResponseLogInUserName() {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserMessage = jsonPathUser.getString("message");
        assertTrue("Error - the message is not equals as expected", jsonPathUserMessage.contains("logged in user session"));
    }

    //USER test logout
    @Given("the following GET request logout the user")
    public void userLogout(){
        responseUser =  given().log().all().get("/logout");
    }

    @Then("the status code is 200 - GET user logout request")
    public void validateResponseUserLogout() {
        assertTrue("The response is not 200",responseUser.statusCode()==200);
    }

    @And("GET logout user request body response contains ok in the message")
    public void validateResponseUserLogoutBody() {
        jsonPathUser = new JsonPath(responseUser.body().asString());
        String jsonPathUserMessage = jsonPathUser.getString("message");
        assertEquals("The value of the name field is not what is expected","ok", jsonPathUserMessage);
    }



}



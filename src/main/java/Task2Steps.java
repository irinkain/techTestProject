import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;

import static io.restassured.RestAssured.given;

public class Task2Steps {
    private Response response;
    private ValidatableResponse json;
    private RequestSpecification request;
    private String endpoint = "https://www.fakeurl.com/v1/transactions";

    @Given("BET is registered")
    public void betIsRegistered(String parameter) {
        request = given().param("click", parameter);
    }

    @When("User wins")
    public void userWins() {
        response = request.when().get(endpoint);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("Win request is received")
    public void winRequestIsReceived() {
        json = response.then().statusCode(200);
    }

    @And("Check session validation")
    public void checkSessionValidation(String sessionId) {
        Assert.assertEquals(response.getBody().jsonPath().getString("sessionID"), sessionId);
    }

    @Given("User’s session exists in system")
    public void sessionExists(String session) {
        Assert.assertEquals(response.getBody().jsonPath().getString("sessionID"), session);
    }

    @When("User starts transaction")
    public void startUserTransaction(String transactionPath) {
        response = request.when().post(endpoint + transactionPath);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("Check if Bet request exists to win")
    public void checkBetRequest(int statusCode) {
        json = response.then().statusCode(statusCode);
    }

    @And("Check win transaction")
    public void checkWinTransaction(String win) {
        Assert.assertEquals(response.getBody().jsonPath().getString("transaction"), win);
    }

    @Given("User’s session does not exist in system or its not valid")
    public void sessionIsNotValid(String session) {
        Assert.assertEquals(response.getBody().jsonPath().getString("details"), "Session not found");
        Assert.assertEquals(response.getStatusCode(), 125);
    }


    @Then("Register new deposit transaction")
    public void registerNewDeposit(String depositRegisterPath) {
        response = request.when().post(endpoint + depositRegisterPath);
        System.out.println("response: " + response.prettyPrint());
    }

    @And("Return success code 10")
    public void checkSuccessStatusCode(int statusCode) {
        Assert.assertEquals(response.getStatusCode(), statusCode);
    }

    @Given("WIN is registered in the system")
    public void winIsRegistered(boolean win) {
        Assert.assertTrue(Boolean.parseBoolean(response.getBody().jsonPath().getString("winIsRegistered")));
    }

    @When("User win")
    public void userWin(String winPath) {
        response = request.when().post(endpoint + winPath);
        System.out.println("response: " + response.prettyPrint());
    }
    @Given("BET is not registered")
    public void betIsNotRegistered(String incorrectUrl) {
        request = given().param("requestUrl", incorrectUrl);
    }

    @When("User does not win")
    public void userDoesnotWin() {
        response = request.when().get(endpoint);
        System.out.println("response: " + response.prettyPrint());
    }

    @Then("Bet request does not exist")
    public void betRequestNotExists() {
        Assert.assertTrue(Boolean.parseBoolean(response.getBody().jsonPath().getString("BetExists")));
    }

    @And("Check status code and error message")
    public void checkStatusCodeAndErrorMessageWhenBetDoesNotExists(String sessionId) {
        Assert.assertEquals(response.getBody().jsonPath().getString("details"), "Bet not found");
        Assert.assertEquals(response.getStatusCode(), 105);
    }

}

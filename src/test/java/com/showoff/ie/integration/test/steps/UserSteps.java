package com.showoff.ie.integration.test.steps;

import com.showoff.ie.integration.test.utils.ApiUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.io.IOException;
import java.util.Map;

import static com.jayway.restassured.RestAssured.given;
import static com.showoff.ie.integration.test.utils.ConstantsUtil.*;


public class UserSteps  extends StepDefinitionBase{

    private Map<String, String> userInfo;
    private final transient ApiUtil util = new ApiUtil();
    private static String user_id;
    private static String access_token;

    private void submitRequest(String method, Map<String, String> userInfo, String endPoint, String request, String access_token) {
        performHttpMethod(method, endPoint, userInfo, request, access_token);
    }

        private void performHttpMethod(String method, String url, Map<String, String> userInfo ,String body, String access_token ){
            Map<String,String> headers =  util.getValidHeaders();
            if(null != access_token){
             //   headers.put("Authorization","Bearer 689150a9e2fd787be5fb2fdf1bfe30ed2e9cba99be393c7fd16e4692af5794c0");
                headers.put("Authorization","Bearer " +access_token);
            }

        switch (method) {
            case "get":
                makeGetCall(url, headers, body);
                break;
            case "post":
                makePostCallWithJson(url, headers,  util.getRequestWithNewValueToTheField(userInfo, body));
                break;
            case "delete":
                makeDeleteCallWithJson(url, headers, body);
                break;
            case "put":
                makePutCallWithJson(url, headers, util.getRequestWithNewValueToTheField(userInfo, body));
                break;
            case "patch":
                makePatchCallWithJson(url, headers, body);
                break;
            default:
                makePostCallWithJson(url, headers, body);
                break;
        }
    }

    @Given("User registration Api is up and running")
    public void userRegistrationApiIsUpAndRunning() throws IOException {
        System.out.println("dummy method to health check endpoint");
    }

    @When("I submit a request to perform registration with below info")
    public void iSubmitARequestToPerformRegistrationWithBelowInfo(Map<String, String> userInfo) {
        this.userInfo = userInfo;
        submitRequest(POST_METHOD, userInfo, CREATE_USER_ENDPOINT, CREATE_USER,null );
    }

    @Then("Validate the response body")
    public void validateTheResponseBody() {
        /**
         * Dto mapping to be added for response body
         */
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.name"),userInfo.get("first_name")+ " "+userInfo.get("last_name"));
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.email"),userInfo.get("email").toLowerCase());
        user_id = (response.extract().body().jsonPath().getJsonObject("data.user.id").toString());
        access_token = (response.extract().body().jsonPath().getJsonObject("data.token.access_token").toString());
    }

    @When("I perform the get operation for ID")
    public void iPerformTheGetOperationForID() {
        submitRequest(GET_METHOD, userInfo, GET_USER_ID_ENDPOINT, user_id, access_token );
        Assert.assertEquals(response.extract().statusCode(),200);
    }


    @Then("Api returns success response")
    public void apiReturnsSuccessResponse() {
    }


    @When("I perform the get operation for me")
    public void iPerformTheGetOperationForMe() {
        submitRequest(GET_METHOD, userInfo, GET_USER_ME_ENDPOINT, null, access_token );
        Assert.assertEquals(response.extract().statusCode(),200);
    }

    @When("I perform the update operation for the user")
    public void iPerformTheUpdateOperationForTheUser(Map<String, String> userInfo) {
        this.userInfo = userInfo;
        submitRequest(PUT_METHOD, userInfo, PUT_USER_ENDPOINT, UPDATE_USER, access_token );
        Assert.assertEquals(response.extract().statusCode(),200);
    }

    @When("I perform the get operation for check email end point")
    public void iPerformTheGetOperationForCheckEmailEndPoint() {
        submitRequest(GET_METHOD, userInfo, CHECK_EMAIL, null, access_token );
        Assert.assertEquals(response.extract().statusCode(),200);
    }
}

package com.showoff.ie.integration.test.steps;

import com.showoff.ie.integration.test.utils.ApiUtil;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import java.io.IOException;
import java.util.Map;

import static com.showoff.ie.integration.test.utils.ConstantsUtil.*;


public class UserSteps  extends StepDefinitionBase{

    private Map<String, String> userInfo;

    private final transient ApiUtil util = new ApiUtil();
    @Given("Api is up and running")
    public void apiIsUpAndRunning() {
        System.out.println("test");
    }

    private void submitRequest(String method, Map<String, String> userInfo, String endPoint, String request) {

        performMethod(method, endPoint,
                util.getValidHeaders(),
                util.getRequestWithNewValueToTheField(userInfo,
                        request));
    }

        private void performMethod(String method, String url, Map<String,String> headers, String body ){
        switch (method) {
            case "get":
                makeGetCall(url, headers );
                break;
            case "post":
                makePostCallWithJson(url, headers, body);
                break;
            case "delete":
                makeDeleteCallWithJson(url, headers, body);
                break;
            case "put":
                makePutCallWithJson(url, headers, body);
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
    public void iSubmitARequestToPerformRegistrationWithBelowInfo(Map<String, String> userInfo  ) {
        this.userInfo = userInfo;
        submitRequest(POST_METHOD, userInfo, CREATE_USER_ENDPOINT, CREATE_USER );
        Assert.assertEquals(response.extract().statusCode(),200);
    }

    @Then("Validate the response body")
    public void validateTheResponseBody() {
        /**
         * Dto mapping to be added for response body
         */
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.name"),userInfo.get("first_name")+ " "+userInfo.get("last_name"));
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.email"),userInfo.get("email").toLowerCase());
    }

}

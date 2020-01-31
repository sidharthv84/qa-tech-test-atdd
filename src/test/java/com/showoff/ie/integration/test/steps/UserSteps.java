package com.showoff.ie.integration.test.steps;

import com.showoff.ie.integration.test.utils.ApiUtil;
import com.showoff.ie.integration.test.utils.ConfigFileReader;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;

import java.io.IOException;
import java.util.Map;

import static com.showoff.ie.integration.test.utils.ConstantsUtil.*;


public class UserSteps extends StepDefinitionBase {

    private Map<String, String> userInfo;
    private final transient ApiUtil util = new ApiUtil();
    private static String user_id;
    private static String access_token;
    private static String email_id;
    ConfigFileReader configFileReader = new ConfigFileReader();

    private void submitRequest(String method, Map<String, String> userInfo, String endPoint, String request, String access_token) {
        performHttpMethod(method, endPoint, userInfo, request, access_token);
    }

    private void performHttpMethod(String method, String url, Map<String, String> userInfo, String body, String access_token) {
        Map<String, String> headers = util.getValidHeaders();
        //  headers.put("Authorization","Bearer 689150a9e2fd787be5fb2fdf1bfe30ed2e9cba99be393c7fd16e4692af5794c0");
        if (null != access_token) {
            headers.put("Authorization", "Bearer " + access_token);
        }

        switch (method) {
            case "get":
                makeGetCall(url, headers, body);
                break;
            case "post":
                makePostCallWithJson(url, headers, util.getRequestWithNewValueToTheField(userInfo, body));
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
        submitRequest(POST_METHOD, userInfo, configFileReader.getUrl("BASE_URL"), CREATE_USER, null);
    }

    @Then("^I validate (.+) for the request")
    public void iValidateForTheRequest(String status) {
        Assert.assertEquals(response.extract().statusCode(), Integer.parseInt(status.substring(1, 4)));
    }

    @Then("Validate the response body")
    public void validateTheResponseBody() {
        /**
         * Dto mapping to be added for response body
         */
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.name"), userInfo.get("first_name") + " " + userInfo.get("last_name"));
        Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("data.user.email"), userInfo.get("email").toLowerCase());
        user_id = (response.extract().body().jsonPath().getJsonObject("data.user.id").toString());
        access_token = (response.extract().body().jsonPath().getJsonObject("data.token.access_token").toString());
        email_id = (response.extract().body().jsonPath().getJsonObject("data.user.email").toString());
    }

    @When("I perform the get operation for ID")
    public void iPerformTheGetOperationForID() {
        submitRequest(GET_METHOD, userInfo, configFileReader.getUrl("BASE_URL"), user_id, access_token);

    }


    @Then("Api returns success response")
    public void apiReturnsSuccessResponse() {
    }

    @When("I perform the update operation for the user")
    public void iPerformTheUpdateOperationForTheUser(Map<String, String> userInfo) {
        this.userInfo = userInfo;
        String param = userInfo.get("firstname") + userInfo.get("last_name");
        submitRequest(PUT_METHOD, userInfo, configFileReader.getUrl("BASE_URL") + "me?=" + param, UPDATE_USER, access_token);
    }

    @When("I perform the get operation for check email end point")
    public void iPerformTheGetOperationForCheckEmailEndPoint() {
        String check_email_url = configFileReader.getUrl("BASE_URL") + "email?email=" + email_id + "" +
                "&client_id=" + configFileReader.getUrl("CLIENT_ID")
                + "&client_secret=" + configFileReader.getUrl("CLIENT_SECRET");
        submitRequest(GET_METHOD, userInfo, check_email_url, null, access_token);
    }


    @When("^I perform the get operation for \"([^\"]*)\"$")
    public void iPerformTheGetOperationFor(String arg0) {
        submitRequest(GET_METHOD, userInfo, configFileReader.getUrl("BASE_URL") + arg0, null, access_token);
    }


    @And("Validate the error response (.+)")
    public void validateTheResponseBody(String text) {
        int textLength = text.length();
        if (textLength >= 2 && text.charAt(0) == '"' && text.charAt(textLength - 1) == '"') {
            String error_msg = text.substring(1, textLength - 1);
            Assert.assertEquals(response.extract().body().jsonPath().getJsonObject("message"), error_msg);
        }
    }


    @When("^I submit a request to perform registration with password and below info \"([^\"]*)\"$")
    public void iSubmitARequestToPerformRegistrationWithPasswordAndBelowInfo(String value, Map<String, String> userInfo) {
        this.userInfo = userInfo;
        submitRequest(POST_METHOD, userInfo, configFileReader.getUrl("BASE_URL"), CREATE_USER, null);
    }


    @When("^I submit a request to api to perform registration with below \"([^\"]*)\"$")
    public void iSubmitARequestToApiToPerformRegistrationWithBelow(String method, Map<String, String> userInfo) {
        this.userInfo = userInfo;
        submitRequest(method, userInfo, configFileReader.getUrl("BASE_URL"), CREATE_USER, null);
    }

    @When("I perform the get operation for ID using incorrect \"([^\"]*)\"$")
    public void iPerformTheGetOperationForIDUsingIncorrect(String arg0) {
    }

    @When("I call the get operation for ID for incorrect end point")
    public void iPerformTheGetOperationForIDForIncorrectEndPoint() {
        submitRequest(GET_METHOD, userInfo, configFileReader.getUrl("URL"), user_id, access_token);
    }

    @When("^I perform the operation for USER using incorrect \"([^\"]*)\"$")
    public void iPerformTheOperationForUSERUsingIncorrect(String method,Map<String,String> userInfo) {
        this.userInfo = userInfo;
        String param = userInfo.get("firstname") + userInfo.get("last_name");
        submitRequest(method, userInfo, configFileReader.getUrl("BASE_URL") + "me?=" + param, UPDATE_USER, access_token);

    }
}

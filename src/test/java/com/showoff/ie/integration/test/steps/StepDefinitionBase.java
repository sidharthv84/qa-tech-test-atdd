package com.showoff.ie.integration.test.steps;

import com.jayway.restassured.response.ValidatableResponse;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.http.HttpHeaders;
import org.junit.Ignore;

import java.util.HashMap;
import java.util.Map;

import static com.jayway.restassured.RestAssured.*;

@Ignore

public class StepDefinitionBase {

    static final Map<String,String> DEFAULT_HEADERS;

    static {
        DEFAULT_HEADERS = new HashMap<>();
        DEFAULT_HEADERS.put(HttpHeaders.CONTENT_TYPE, "application/json");
    }

  protected transient ValidatableResponse response;


    protected void makeGetCall(String url, Map<String,String> headers, String userId) {
        if( null != userId){
          url = url + "/"+userId;
        }

        response = given().urlEncodingEnabled(false).log().all()
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .when()
                .port(port)
                .get(url)
                .then();
    }

    protected void makePostCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = given().urlEncodingEnabled(false).log().all()
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .post(url)
                .then();
    }

    protected void makePutCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = given().urlEncodingEnabled(false).log().all()
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .put(url)
                .then();
    }

    protected void makeDeleteCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = given().urlEncodingEnabled(false).log().all()
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .get(url)
                .then();
    }

    protected void makePatchCallWithJson(String url, Map<String,String> headers, Object body ) {
        response = given().urlEncodingEnabled(false).log().all()
                .headers(ObjectUtils.defaultIfNull(headers, new HashMap<>()))
                .body(body)
                .when()
                .port(port)
                .get(url)
                .then();
    }

    protected void makePostCallWithJson1( String body ) {
        response = given()
                .contentType("application/json")
                .body(body)
                .when()
                .get("https://showoff-rails-react-production.herokuapp.com/api/v1/users")
                .then();

        System.out.println("body"+  body);


    }
}
package com.github.slovb.idag.day;

import static org.hamcrest.Matchers.*;

import java.util.ArrayList;

import static org.hamcrest.MatcherAssert.assertThat;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.given;
import org.apache.http.HttpStatus;

@QuarkusTest
class DayResourceTest {
    @Test
    @DisplayName("GET, expect to find title \"Code!\"")
    @Tag("GET")
    void testDayEndpoint() {
    	Response response = given()
          .when().get("/day")
          .then()
          	 .assertThat()
             .statusCode(HttpStatus.SC_OK)
             .and()
             .contentType(ContentType.JSON)
             .and()
             .extract()
             .response();

    	ArrayList<String> titles = response.path("2025-02-13.form_after.title");

    	assertThat(titles, contains("Code!"));
    }

}
package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;

import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.greaterThan;

public class ResponseField {

    private ResponseField() {}

    public static void validate(String campo, Object esperado) {
        SerenityRest.lastResponse().then().body(campo, equalTo(esperado));
    }

    public static void listNotEmpty() {
        SerenityRest.lastResponse().then().body("size()", greaterThan(0));
    }

    public static int bookingId() {
        return SerenityRest.lastResponse().jsonPath().getInt("bookingid");
    }
}
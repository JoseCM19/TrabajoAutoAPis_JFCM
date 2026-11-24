package org.example.Questions;

import net.serenitybdd.rest.SerenityRest;

public class ResponseCode {

    private ResponseCode() {}

    public static void validate(int esperado) {
        SerenityRest.lastResponse().then().statusCode(esperado);
    }
}
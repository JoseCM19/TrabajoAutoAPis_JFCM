package org.example.StepDefinitions;

import io.cucumber.java.ParameterType;

public class ParameterDefinition {

    @ParameterType("con depósito|sin depósito")
    public boolean deposito(String valor) {
        return valor.equals("con depósito");
    }
}
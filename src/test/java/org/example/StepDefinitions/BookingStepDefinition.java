package org.example.StepDefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.example.Apis.BookingApi;
import org.example.Models.BookingRequest;
import org.example.Questions.ResponseCode;
import org.example.Questions.ResponseField;
import org.example.Taks.GetAllBookings;
import org.example.Taks.GetBookingById;
import org.example.Taks.PostBooking;

public class BookingStepDefinition {

    private int idReserva;

    @Given("que el servicio de reservas está disponible")
    public void servicioDisponible() {
        BookingApi.ping();
        ResponseCode.validate(201);
    }

    @Given("que existe una reserva a nombre de {string} {string}")
    public void existeReserva(String nombre, String apellido) {
        PostBooking.execute(BookingRequest.of(nombre, apellido, 100, true));
        idReserva = ResponseField.bookingId();
    }

    @When("consulto todas las reservas")
    public void consultoTodas() {
        GetAllBookings.execute();
    }

    @When("creo una reserva a nombre de {string} {string} con precio {int} {deposito}")
    public void creoReserva(String nombre, String apellido, int precio, boolean deposito) {
        PostBooking.execute(BookingRequest.of(nombre, apellido, precio, deposito));
    }

    @When("consulto la reserva creada")
    public void consultoReservaCreada() {
        GetBookingById.execute(idReserva);
    }

    @When("consulto la reserva con id {int}")
    public void consultoReservaPorId(int id) {
        GetBookingById.execute(id);
    }

    @Then("el código de respuesta debe ser {int}")
    public void codigoRespuesta(int codigo) {
        ResponseCode.validate(codigo);
    }

    @Then("la lista de reservas no debe estar vacía")
    public void listaNoVacia() {
        ResponseField.listNotEmpty();
    }

    @Then("el campo {string} debe ser {int}")
    public void campoEntero(String campo, int valor) {
        ResponseField.validate(campo, valor);
    }

    @Then("el campo {string} debe ser {string}")
    public void campoTexto(String campo, String valor) {
        ResponseField.validate(campo, valor);
    }
}
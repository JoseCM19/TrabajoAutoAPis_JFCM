package org.example.Apis;

import io.restassured.config.HttpClientConfig;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.rest.SerenityRest;
import org.apache.http.NoHttpResponseException;
import org.apache.http.client.HttpRequestRetryHandler;
import org.apache.http.conn.ConnectTimeoutException;
import org.apache.http.impl.client.DefaultHttpClient;

import java.net.ConnectException;

public class BookingApi {

    public static final String BASE_URL = "https://restful-booker.herokuapp.com";
    public static final String BOOKING = "/booking";
    public static final String BOOKING_BY_ID = "/booking/{id}";
    public static final String PING = "/ping";

    private static final int MAX_REINTENTOS = 3;

    // Reintenta solo cuando no se pudo conectar (la petición no llegó a enviarse)
    private static final HttpRequestRetryHandler REINTENTAR_CONEXION =
            (exception, executionCount, context) ->
                    executionCount <= MAX_REINTENTOS
                            && (exception instanceof ConnectTimeoutException
                            || exception instanceof ConnectException
                            || exception instanceof NoHttpResponseException);

    private BookingApi() {}

    @SuppressWarnings("deprecation")
    private static RestAssuredConfig config() {
        return RestAssuredConfig.config().httpClient(
                HttpClientConfig.httpClientConfig()
                        .httpClientFactory(() -> {
                            DefaultHttpClient client = new DefaultHttpClient();
                            client.setHttpRequestRetryHandler(REINTENTAR_CONEXION);
                            return client;
                        })
                        .setParam("http.connection.timeout", 5000)
                        .setParam("http.socket.timeout", 20000));
    }

    public static RequestSpecification request() {
        return SerenityRest.given()
                .config(config())
                .baseUri(BASE_URL)
                .contentType(ContentType.JSON)
                .header("Accept", "application/json");
    }

    public static void ping() {
        SerenityRest.given()
                .config(config())
                .baseUri(BASE_URL)
                .get(PING);
    }
}
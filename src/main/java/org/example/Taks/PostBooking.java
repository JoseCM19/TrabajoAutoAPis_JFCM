package org.example.Taks;

import org.example.Apis.BookingApi;
import org.example.Models.BookingRequest;

public class PostBooking {

    private PostBooking() {}

    public static void execute(BookingRequest payload) {
        BookingApi.request()
                .body(payload)
                .when()
                .post(BookingApi.BOOKING);
    }
}
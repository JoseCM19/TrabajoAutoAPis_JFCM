package org.example.Taks;

import org.example.Apis.BookingApi;

public class GetBookingById {

    private GetBookingById() {}

    public static void execute(int id) {
        BookingApi.request()
                .pathParam("id", id)
                .when()
                .get(BookingApi.BOOKING_BY_ID);
    }
}
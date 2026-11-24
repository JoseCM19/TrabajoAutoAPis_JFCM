package org.example.Taks;

import org.example.Apis.BookingApi;

public class GetAllBookings {

    private GetAllBookings() {}

    public static void execute() {
        BookingApi.request()
                .when()
                .get(BookingApi.BOOKING);
    }
}
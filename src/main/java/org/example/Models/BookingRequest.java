package org.example.Models;

public class BookingRequest {

    public static class BookingDates {
        private final String checkin;
        private final String checkout;

        public BookingDates(String checkin, String checkout) {
            this.checkin = checkin;
            this.checkout = checkout;
        }
        public String getCheckin() { return checkin; }
        public String getCheckout() { return checkout; }
    }

    private final String firstname;
    private final String lastname;
    private final int totalprice;
    private final boolean depositpaid;
    private final BookingDates bookingdates;
    private final String additionalneeds;

    public BookingRequest(String firstname, String lastname, int totalprice,
                          boolean depositpaid, BookingDates bookingdates, String additionalneeds) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.totalprice = totalprice;
        this.depositpaid = depositpaid;
        this.bookingdates = bookingdates;
        this.additionalneeds = additionalneeds;
    }

    public static BookingRequest of(String nombre, String apellido, int precio, boolean deposito) {
        return new BookingRequest(nombre, apellido, precio, deposito,
                new BookingDates("2026-12-01", "2026-12-05"), "Breakfast");
    }

    public String getFirstname() { return firstname; }
    public String getLastname() { return lastname; }
    public int getTotalprice() { return totalprice; }
    public boolean isDepositpaid() { return depositpaid; }
    public BookingDates getBookingdates() { return bookingdates; }
    public String getAdditionalneeds() { return additionalneeds; }
}
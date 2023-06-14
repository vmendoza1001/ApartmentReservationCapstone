package com.vanessa.ApartmentReservationCapstone.exception;

public class ReservationNotFoundException extends RuntimeException {
    public ReservationNotFoundException(Integer id) {
        super("Reservation id not found : " + id);
    }
}

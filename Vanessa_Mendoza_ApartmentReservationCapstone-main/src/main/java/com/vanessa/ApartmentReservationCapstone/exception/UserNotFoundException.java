package com.vanessa.ApartmentReservationCapstone.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(Integer id) {
        super("User id not found : " + id);
    }
}


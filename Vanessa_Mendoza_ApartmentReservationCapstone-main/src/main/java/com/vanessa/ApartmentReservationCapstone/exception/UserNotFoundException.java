package com.vanessa.ApartmentReservationCapstone.exception;

public class UserNotFoundException extends RuntimeException {
    public UserNotFoundException(String fullName) {
        super("User id not found : " + fullName);
    }
}


package com.vanessa.ApartmentReservationCapstone.exception;

public class EmployeeNotFoundException extends RuntimeException {
    public EmployeeNotFoundException(String fullName) {
        super("Employee not found with fullName: " + fullName);
    }
}

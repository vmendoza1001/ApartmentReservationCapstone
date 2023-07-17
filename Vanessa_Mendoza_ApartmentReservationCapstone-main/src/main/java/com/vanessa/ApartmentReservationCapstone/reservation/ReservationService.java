package com.vanessa.ApartmentReservationCapstone.reservation;

import java.util.List;

public interface ReservationService {

    List<Reservation> getAllReservations();

    Reservation getReservationById(int reservationId);

    void saveOrUpdateReservation(Reservation reservation);

    void deleteReservation(int reservationId);
}

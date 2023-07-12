package com.vanessa.ApartmentReservationCapstone.repository;

import com.vanessa.ApartmentReservationCapstone.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {
    Reservation findByReservationNumber(int reservationNumber);
}

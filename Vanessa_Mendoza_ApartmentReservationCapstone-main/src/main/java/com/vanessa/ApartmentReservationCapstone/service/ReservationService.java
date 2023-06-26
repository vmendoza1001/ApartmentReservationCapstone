package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.exception.ReservationNotFoundException;
import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.Reservation;
import com.vanessa.ApartmentReservationCapstone.repository.ReservationRepository;
import com.vanessa.ApartmentReservationCapstone.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;
    @Autowired
    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }
    public Reservation createReservation(Reservation reservation) throws Exception, UserNotFoundException {
        if(reservation.getUser() == null || userRepository.findById(reservation.getUser().getId()).isEmpty()){
            throw new UserNotFoundException(reservation.getUser().getFullName());
        }
        return reservationRepository.save(reservation);
    }

    public Reservation getReservation(Integer id) throws ReservationNotFoundException {
        return reservationRepository.findById(id).orElseThrow(() -> new ReservationNotFoundException(id));
    }

    public Reservation updateReservation(Reservation reservation) throws ReservationNotFoundException, UserNotFoundException {
        if(reservationRepository.findById(reservation.getId()).isEmpty()){
            throw new ReservationNotFoundException(reservation.getId());
        }
        if(reservation.getUser() == null || userRepository.findById(reservation.getUser().getId()).isEmpty()){
            throw new UserNotFoundException(reservation.getUser().getFullName());
        }
        return reservationRepository.save(reservation);
    }

    public void deleteReservation(Integer id) throws ReservationNotFoundException {
        if(reservationRepository.findById(id).isEmpty()){
            throw new ReservationNotFoundException(id);
        }
        reservationRepository.deleteById(id);
    }
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }
}

package com.vanessa.ApartmentReservationCapstone.service;

import com.vanessa.ApartmentReservationCapstone.reservation.Reservation;
import com.vanessa.ApartmentReservationCapstone.reservation.ReservationRepository;
import com.vanessa.ApartmentReservationCapstone.reservation.ReservationServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;

class ReservationServiceImplTest {

    @Mock
    ReservationRepository reservationRepository;

    @InjectMocks
    ReservationServiceImpl reservationService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void getAllReservations() {
        Reservation reservation1 = new Reservation();
        Reservation reservation2 = new Reservation();
        when(reservationRepository.findAll()).thenReturn(Arrays.asList(reservation1, reservation2));

        reservationService.getAllReservations();

        verify(reservationRepository, times(1)).findAll();
    }

    @Test
    void getReservationById() {
        Reservation reservation = new Reservation();
        int id = 1;
        when(reservationRepository.findById(id)).thenReturn(Optional.of(reservation));

        reservationService.getReservationById(id);

        verify(reservationRepository, times(1)).findById(id);
    }

    @Test
    void saveOrUpdateReservation() {
        Reservation reservation = new Reservation();

        reservationService.saveOrUpdateReservation(reservation);

        verify(reservationRepository, times(1)).save(reservation);
    }

    @Test
    void deleteReservation() {
        int id = 1;

        reservationService.deleteReservation(id);

        verify(reservationRepository, times(1)).deleteById(id);
    }
}

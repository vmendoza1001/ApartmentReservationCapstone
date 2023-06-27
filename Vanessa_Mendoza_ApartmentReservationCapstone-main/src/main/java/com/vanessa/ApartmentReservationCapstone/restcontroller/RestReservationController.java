//package com.vanessa.ApartmentReservationCapstone.restcontroller;
//
//import com.vanessa.ApartmentReservationCapstone.exception.ReservationNotFoundException;
//import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
//import com.vanessa.ApartmentReservationCapstone.model.Reservation;
//import com.vanessa.ApartmentReservationCapstone.model.User;
//import com.vanessa.ApartmentReservationCapstone.service.ReservationService;
//import com.vanessa.ApartmentReservationCapstone.service.UserService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.web.bind.annotation.*;
//
//@RestController
//public class RestReservationController {
//
//    @Autowired
//    private ReservationService reservationService;
//
//    @Autowired
//    private UserService userService;
//
//    @PostMapping("/reservations")
//    public Reservation createReservation(@RequestBody Reservation reservation) throws Exception {
//        User user = userService.getUserByFullName(reservation.getUser().getFullName());
//        reservation.setUser(user);
//        return reservationService.createReservation(reservation);
//    }
//
//
//    @GetMapping("/reservations/{id}")
//    public Reservation getReservation(@PathVariable Integer id) throws ReservationNotFoundException {
//        return reservationService.getReservation(id);
//    }
//
//    @PutMapping("/reservations")
//    public Reservation updateReservation(@RequestBody Reservation reservation) throws UserNotFoundException, ReservationNotFoundException {
//        User user = userService.getUserByFullName(reservation.getUser().getFullName());
//        reservation.setUser(user);
//        return reservationService.updateReservation(reservation);
//    }
//
//    @DeleteMapping("/reservations/{id}")
//    public void deleteReservation(@PathVariable Integer id) throws ReservationNotFoundException {
//        reservationService.deleteReservation(id);
//    }
//
//    @Override
//    public String toString() {
//        return "ReservationController{" +
//                "reservationService=" + reservationService +
//                ", userService=" + userService +
//                '}';
//    }
//}

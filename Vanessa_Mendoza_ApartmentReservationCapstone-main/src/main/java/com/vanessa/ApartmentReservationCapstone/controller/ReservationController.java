package com.vanessa.ApartmentReservationCapstone.controller;

import com.vanessa.ApartmentReservationCapstone.exception.ReservationNotFoundException;
import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.Reservation;
import com.vanessa.ApartmentReservationCapstone.service.ReservationService;
import com.vanessa.ApartmentReservationCapstone.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
public class ReservationController {

    private ReservationService reservationService;
    private UserService userService;

    @Autowired
    public ReservationController(ReservationService reservationService, UserService userService) {
        this.reservationService = reservationService;
        this.userService = userService;
    }

    @GetMapping("/reservations")
    public String listReservations(Model model) {
        List<Reservation> reservations = reservationService.getAllReservations();
        model.addAttribute("reservations", reservations);
        return "reservations";
    }

    @GetMapping("/reservations/new")
    public String newReservation(Model model) {
        model.addAttribute("reservation", new Reservation());
        return "reservationForm";
    }

    @PostMapping("/reservations")
    public String createReservation(@ModelAttribute Reservation reservation) throws Exception, UserNotFoundException {
        reservationService.createReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/reservations/edit/{id}")
    public String editReservation(@PathVariable Integer id, Model model) throws ReservationNotFoundException {
        Reservation existingReservation = reservationService.getReservation(id);
        model.addAttribute("reservation", existingReservation);
        return "reservationForm";
    }

    @PostMapping("/reservations/{id}")
    public String updateReservation(@ModelAttribute Reservation reservation) throws ReservationNotFoundException, UserNotFoundException {
        reservationService.updateReservation(reservation);
        return "redirect:/reservations";
    }

    @GetMapping("/reservations/delete/{id}")
    public String deleteReservation(@PathVariable Integer id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}

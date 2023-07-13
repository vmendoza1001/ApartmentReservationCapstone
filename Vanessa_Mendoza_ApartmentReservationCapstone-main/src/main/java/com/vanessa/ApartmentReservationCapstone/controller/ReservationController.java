package com.vanessa.ApartmentReservationCapstone.controller;

import com.vanessa.ApartmentReservationCapstone.exception.ReservationNotFoundException;
import com.vanessa.ApartmentReservationCapstone.exception.UserNotFoundException;
import com.vanessa.ApartmentReservationCapstone.exception.UsernameNotFoundException;
import com.vanessa.ApartmentReservationCapstone.model.Reservation;
import com.vanessa.ApartmentReservationCapstone.model.User;
import com.vanessa.ApartmentReservationCapstone.service.ReservationService;
import com.vanessa.ApartmentReservationCapstone.service.UserService;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

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
    public String submitReservation(@ModelAttribute Reservation reservation, HttpServletRequest request) throws Exception {
        String username = request.getUserPrincipal().getName();
        User loggedInUser = userService.getLoggedInUser(username).orElseThrow(() -> new UsernameNotFoundException("User Not Found with username: " + username));
        reservation.setUser(loggedInUser);
        reservationService.createReservation(reservation);
        return "redirect:/confirmation";
    }

    @GetMapping("/confirmation")
    public String showConfirmationPage() {
        return "confirmation";
    }

    @GetMapping("/reservations/edit/{id}")
    public String editReservation(@PathVariable int id, Model model) throws ReservationNotFoundException {
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
    public String deleteReservation(@PathVariable int id) throws ReservationNotFoundException {
        reservationService.deleteReservation(id);
        return "redirect:/reservations";
    }
}

package com.vanessa.ApartmentReservationCapstone.reservation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @GetMapping("/reservation_list")
    public String viewReservationList(Model model) {
        model.addAttribute("listReservations", reservationService.getAllReservations());
        return "reservation_list";
    }

    @GetMapping("/new_reservation")
    public String showNewReservationForm(Model model) {
        Reservation reservation = new Reservation();
        model.addAttribute("reservation", reservation);
        return "new_reservation";
    }

    @PostMapping("/saveReservation")
    public String saveReservation(@ModelAttribute("reservation") Reservation reservation) {
        reservationService.saveOrUpdateReservation(reservation);
        return "redirect:/reservation_list";
    }

    @GetMapping("/update_reservation/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {
        Reservation reservation = reservationService.getReservationById(id);
        model.addAttribute("reservation", reservation);
        return "update_reservation";
    }

    @GetMapping("/deleteReservation/{id}")
    public String deleteReservation(@PathVariable(value = "id") int id) {
        this.reservationService.deleteReservation(id);
        return "redirect:/reservation_list";
    }
}


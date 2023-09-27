package com.example.salonbella.controller.reservation;

import com.example.salonbella.service.reservation.ReservationResponse;
import com.example.salonbella.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.text.ParseException;
import java.util.List;

@Controller
public class ReservationUserController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationUserController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }

    @GetMapping("/schedule-reservation")
    public String getUserPage() {
        return "user-schedule-reservation";
    }

    @GetMapping("/my-reservations")
    public String getReservationsUser(Model model) {
        List<ReservationResponse> reservations = reservationService.getUserReservations();
        model.addAttribute("reservations", reservations);
        return "user-my-reservations";
    }

    @PostMapping("/get-free-reservations")
    public String getFreeReservations(@RequestParam("date") String date, @RequestParam("type") String type, Model model) throws ParseException {
        model.addAttribute("free_reservations", reservationService.getFreeReservations(date, type));
        model.addAttribute("date", date);
        model.addAttribute("tip", type);
        return "user-schedule-reservation";
    }

    @PostMapping("/make-reservation")
    public String makeReservation(@RequestParam("date") String date, @RequestParam("type") String type, @RequestParam("time") String time) {
        reservationService.makeReservation(date, time, type);
        return "redirect:/userDashboard";
    }

    @PostMapping("/cancel-reservation")
    public String cancelReservation(@RequestParam("id") String id) {
        reservationService.cancelReservation(Long.parseLong(id));
        return "redirect:/my-reservations";
    }


}
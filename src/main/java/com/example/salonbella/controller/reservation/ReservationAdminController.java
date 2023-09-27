package com.example.salonbella.controller.reservation;

import com.example.salonbella.service.reservation.ReservationResponse;
import com.example.salonbella.service.reservation.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.View;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.List;

@Controller
public class ReservationAdminController {

    private final ReservationService reservationService;

    @Autowired
    public ReservationAdminController(ReservationService reservationService) {
        this.reservationService = reservationService;
    }


    @GetMapping("/admin-scheduled-reservations")
    public String getScheduledReservations() {
        return "admin-reservations";
    }

    @GetMapping("/admin-block-reservation")
    public String getBlockReservationPage() {
        return "admin-block-reservation";
    }

    @GetMapping("/admin-schedule-reservation")
    public String getReservationPage() {
        return "admin-schedule-reservation";
    }

    @GetMapping("/admin-get-blocked-reservations")
    public String getBlockedReservations(Model model) {
        List<ReservationResponse> list = reservationService.getBlockedReservations();
        model.addAttribute("reservations", list);
        return "admin-blocked-reservations";
    }

    @PostMapping("/admin-get-scheduled-reservations")
    public String getScheduledReservations(@RequestParam("date") String date, @RequestParam("type") String type, Model model) {
        List<ReservationResponse> reservations = reservationService.getScheduledReservations(date, type);
        model.addAttribute("reservations", reservations);
        model.addAttribute("date", date);
        model.addAttribute("type", type);
        return "admin-scheduled-reservations";
    }

    @PostMapping("/admin-cancel-reservation")
    public ModelAndView cancelReservation(@RequestParam("id") String id, @RequestParam("date") String date,
                                          @RequestParam("type") String type, HttpServletRequest request) {
        reservationService.cancelReservationAdmin(Long.parseLong(id));
        request.setAttribute(View.RESPONSE_STATUS_ATTRIBUTE, HttpStatus.TEMPORARY_REDIRECT);
        request.setAttribute("date", date);
        request.setAttribute("type", type);
        return new ModelAndView("redirect:/admin-get-scheduled-reservations");
    }


    @PostMapping("/admin-block-reservation")
    public String blockReservation(@RequestParam("date") String date, @RequestParam("type") String type, @RequestParam("time") String time) {
        reservationService.blockReservation(date, time, type);
        return "redirect:/adminDashboard";
    }

    @PostMapping("/admin-unblock-reservation")
    public String unblockReservation(@RequestParam("id") String id) {
        reservationService.unblockReservation(Long.parseLong(id));
        return "redirect:/admin-get-blocked-reservations";
    }

    @PostMapping("/admin-get-free-reservations")
    public String getFreeReservations(@RequestParam("date") String date, @RequestParam("type") String type, Model model) throws ParseException {
        model.addAttribute("free_reservations", reservationService.getFreeReservations(date, type));
        model.addAttribute("date", date);
        model.addAttribute("tip", type);
        return "admin-schedule-reservation";
    }

    @PostMapping("/admin-make-reservation")
    public String makeReservation(@RequestParam("date") String date, @RequestParam("type") String type, @RequestParam("time") String time) {
        reservationService.makeReservation(date, time, type);
        return "redirect:/adminDashboard";
    }

}

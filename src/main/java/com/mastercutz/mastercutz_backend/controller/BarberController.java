package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.exception.ScheduleNotFoundException;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.BarberSchedule;
import com.mastercutz.mastercutz_backend.model.ReservationRequest;
import com.mastercutz.mastercutz_backend.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @PostMapping("/addBarber")
    public Barber addBarber(@RequestBody Barber barber) {
        return barberService.addBarber(barber);
    }

    @GetMapping("/getAllBarbers")
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

    @GetMapping("/barbers/schedules")
    public ResponseEntity<List<BarberSchedule>> getBarberSchedules() {
        List<BarberSchedule> schedules = barberService.getAllSchedules();
        return ResponseEntity.ok(schedules);
    }

    @PostMapping("/barbers/schedules/reserve")
    public ResponseEntity<String> reserveSchedule(@RequestBody ReservationRequest reservationRequest) {
        try {
            boolean success = barberService.reserveSchedule(reservationRequest);
            if (success) {
                return ResponseEntity.ok("Reservation successful");
            } else {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Schedule is already reserved");
            }
        } catch (ScheduleNotFoundException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Schedule not found");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An unexpected error occurred");
        }
    }




}
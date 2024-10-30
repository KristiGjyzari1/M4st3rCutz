package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.exception.TimeSlotIsNotAvaible;
import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/bookAppointment")
    public Appointment bookAppointment(@RequestBody Appointment appointment) throws Throwable {
        Long barberId = appointment.getBarber().getId();
        Long clientId = appointment.getClient().getId();
        LocalDateTime localDateTime = appointment.getDateTime();

        return appointmentService.registerAppointment(appointment, barberId, clientId, localDateTime);
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }

    @GetMapping("/availableAppointments/{barberId}")
    public ResponseEntity<List<LocalDateTime>> getAvailableAppointments(@PathVariable Long barberId) {
        List<LocalDateTime> availableAppointments = appointmentService.getAvailableAppointments(barberId);
        return ResponseEntity.ok(availableAppointments);
    }

    @DeleteMapping("/cancelAppointment/{appointmentId}")
    public ResponseEntity<Void> cancelAppointment(@PathVariable Long appointmentId) {
        appointmentService.cancelAppointment(appointmentId);
        return ResponseEntity.noContent().build();
    }
}

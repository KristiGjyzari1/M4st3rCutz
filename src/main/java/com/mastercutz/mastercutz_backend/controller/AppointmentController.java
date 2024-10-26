package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.exception.TimeSlotIsNotAvaible;
import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.services.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @PostMapping("/bookAppointment")
    public Appointment bookAppointment(@RequestBody  Barber barber, LocalDateTime dateTime, Client client) throws TimeSlotIsNotAvaible {
        return appointmentService.reserveAppointment(barber,dateTime,client);
    }

    @GetMapping("/getAllAppointments")
    public List<Appointment> getAppointments() {
        return appointmentService.getAppointments();
    }
}

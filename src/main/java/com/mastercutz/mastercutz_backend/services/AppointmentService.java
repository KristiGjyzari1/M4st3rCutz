package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

    public Appointment bookAppointment(Appointment appointment) {
        // Kontrollo nëse orari është i lirë, etj.
        return appointmentRepository.save(appointment);
    }

    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }
}

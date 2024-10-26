package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.exception.EmailIsAlradyInUseException;
import com.mastercutz.mastercutz_backend.exception.TimeSlotIsNotAvaible;
import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.repository.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;

////    public Appointment bookAppointment(Appointment appointment) {
////        // Kontrollo nëse orari është i lirë, etj.
////        return appointmentRepository.save(appointment);
////    }
//
//    public Appointment bookAppointment(Appointment appointment) throws TimeSlotIsNotAvaible {
//        // Kontrollo nëse orari është i lirë, etj.
//        if (appointmentRepository.findByDateTime(appointment.getDateTime()) == null) {
//            return appointmentRepository.save(appointment);
//        }else
//            throw new TimeSlotIsNotAvaible("Time Slot Is Not Avaible"); // ose hedh një përjashtim (exception) për email të përdorur
//    }


    public Appointment reserveAppointment(Barber barber, LocalDateTime dateTime, Client client) {
        // Kontrollo nëse ekziston një rezervim për këtë berber në këtë orar
        if (appointmentRepository.existsByBarberIdAndDateTime(barber, dateTime)) {
            throw new IllegalStateException("This time slot is already taken.");
        }
        // Krijo një objekt të ri për rezervimin
        Appointment appointment = new Appointment();
        appointment.setBarber(barber);
        appointment.setDateTime(dateTime);
        appointment.setClient(client);
        // Ruaj rezervimin në bazën e të dhënave
        return appointmentRepository.save(appointment);
    }
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

}





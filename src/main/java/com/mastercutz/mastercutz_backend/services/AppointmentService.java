package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.exception.TimeSlotIsNotAvaible;
import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.repository.AppointmentRepository;
import com.mastercutz.mastercutz_backend.repository.BarberRepository;
import com.mastercutz.mastercutz_backend.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class AppointmentService {

    @Autowired
    private AppointmentRepository appointmentRepository;
    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private ClientRepository clientRepository;

    public Appointment registerAppointment(Appointment appointment, Long barberId, Long clientId, LocalDateTime localDateTime) throws TimeSlotIsNotAvaible {
        // Kontrolloni nëse ID-të nuk janë null
        if (barberId == null || clientId == null) {
            throw new IllegalArgumentException("Barber ID and Client ID must not be null");
        }

        // Merrni Barber nga baza e të dhënave
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("Barber not found"));

        // Merrni Client nga baza e të dhënave
        Client client = clientRepository.findById(clientId)
                .orElseThrow(() -> new IllegalArgumentException("Client not found"));

        // Kontrolloni nëse ekziston një rezervim për këtë berber në këtë orar
        if (appointmentRepository.existsByBarberIdAndDateTime(barber.getId(), localDateTime)) {
            throw new TimeSlotIsNotAvaible("Appointment Is Already In Use");
        }

        // Lidheni barberin dhe klientin me objektin e rezervimit
        appointment.setBarber(barber);
        appointment.setClient(client);
        appointment.setDateTime(localDateTime);

        // Ruani rezervimin
        Appointment savedAppointment = appointmentRepository.save(appointment);
        System.out.println("Appointment saved with ID: " + savedAppointment.getId());

        return savedAppointment;
    }
    public List<Appointment> getAppointments() {
        return appointmentRepository.findAll();
    }

    public List<LocalDateTime> getAvailableAppointments(Long barberId) {
        // Merrni të gjitha rezervimet për berberin e dhënë
        List<Appointment> appointments = appointmentRepository.findByBarberId(barberId);

        // Definoni oraret e mundshme (p.sh., çdo 30 minuta nga ora 9:00 deri në 17:00)
        List<LocalDateTime> availableSlots = new ArrayList<>();
        LocalDateTime start = LocalDateTime.of(LocalDate.now(), LocalTime.of(9, 0)); // Ora filluese
        LocalDateTime end = LocalDateTime.of(LocalDate.now(), LocalTime.of(17, 0)); // Ora përfundimtare

        for (LocalDateTime slot = start; slot.isBefore(end); slot = slot.plusMinutes(30)) {
            availableSlots.add(slot);
        }

        // Hiqni oraret e rezervuara nga lista e orareve të mundshme
        for (Appointment appointment : appointments) {
            LocalDateTime appointmentTime = appointment.getDateTime();
            availableSlots.removeIf(slot -> slot.isEqual(appointmentTime));
        }

        return availableSlots;
    }
    public void cancelAppointment(Long appointmentId) {
        Appointment appointment = appointmentRepository.findById(appointmentId)
                .orElseThrow(() -> new IllegalArgumentException("Appointment not found"));
        appointmentRepository.delete(appointment);
    }

    public List<Appointment> getAppointmentHistory(Long clientId) {
        return appointmentRepository.findByClientId(clientId);
    }
}





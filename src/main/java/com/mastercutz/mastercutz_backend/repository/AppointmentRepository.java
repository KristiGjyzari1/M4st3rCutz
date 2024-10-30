package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
        boolean existsByBarberIdAndDateTime(Long barber, LocalDateTime dateTime);

        List<Appointment> findByBarberId(Long barberId);

        List<Appointment> findByClientId(Long clientId);

        @Query("SELECT a FROM Appointment a WHERE a.dateTime > CURRENT_TIMESTAMP")
        List<Appointment> findUpcomingAppointments();
}

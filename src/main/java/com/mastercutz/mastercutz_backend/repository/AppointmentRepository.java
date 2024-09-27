package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
}

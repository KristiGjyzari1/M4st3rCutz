package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Appointment;
import com.mastercutz.mastercutz_backend.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    Object findByDateTime(LocalDateTime dateTime);

    //boolean existsByBarberIdAndDateTime(Long barber, LocalDateTime dateTime);


        boolean existsByBarberIdAndDateTime(Barber barber, LocalDateTime dateTime);

}

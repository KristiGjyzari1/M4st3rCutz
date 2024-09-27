package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.BarberSchedule;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BarberScheduleRepository extends JpaRepository<BarberSchedule, Long> {
}


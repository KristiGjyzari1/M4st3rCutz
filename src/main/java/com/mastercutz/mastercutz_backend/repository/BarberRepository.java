package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Barber;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BarberRepository extends JpaRepository<Barber, Long> {
}

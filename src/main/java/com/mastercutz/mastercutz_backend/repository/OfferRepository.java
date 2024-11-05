package com.mastercutz.mastercutz_backend.repository;

import com.mastercutz.mastercutz_backend.model.Offer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;

public interface OfferRepository extends JpaRepository<Offer, Long> {
    List<Offer> findAllByStartDateBeforeAndEndDateAfter(LocalDate date, LocalDate today);
}

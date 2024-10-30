package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.dto.BarberProfileUpdateDto;
import com.mastercutz.mastercutz_backend.dto.UserProfileUpdateDto;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.Client;
import com.mastercutz.mastercutz_backend.repository.BarberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;

    public Barber addBarber(Barber barber) {
        return barberRepository.save(barber);
    }

    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public Barber updateUserProfile(Long barberId, BarberProfileUpdateDto updateDto) {
        Barber barber = barberRepository.findById(barberId)
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        barber.setName(updateDto.getName());
        barber.setSpecialization(updateDto.getSpecialization());

        return barberRepository.save(barber);
    }

}

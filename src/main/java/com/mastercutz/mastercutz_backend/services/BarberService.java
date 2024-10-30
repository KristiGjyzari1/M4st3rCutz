package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.model.Barber;
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

}

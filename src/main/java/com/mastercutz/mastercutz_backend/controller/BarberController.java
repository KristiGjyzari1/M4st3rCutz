package com.mastercutz.mastercutz_backend.controller;

import com.mastercutz.mastercutz_backend.exception.ScheduleNotFoundException;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.services.BarberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/barbers")
public class BarberController {

    @Autowired
    private BarberService barberService;

    @PostMapping("/addBarber")
    public Barber addBarber(@RequestBody Barber barber) {
        return barberService.addBarber(barber);
    }

    @GetMapping("/getAllBarbers")
    public List<Barber> getAllBarbers() {
        return barberService.getAllBarbers();
    }

}
package com.mastercutz.mastercutz_backend.services;

import com.mastercutz.mastercutz_backend.exception.ScheduleNotFoundException;
import com.mastercutz.mastercutz_backend.model.Barber;
import com.mastercutz.mastercutz_backend.model.BarberSchedule;
import com.mastercutz.mastercutz_backend.model.ReservationRequest;
import com.mastercutz.mastercutz_backend.repository.BarberRepository;
import com.mastercutz.mastercutz_backend.repository.BarberScheduleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class BarberService {

    @Autowired
    private BarberRepository barberRepository;
    @Autowired
    private BarberScheduleRepository barberScheduleRepository;

    public Barber addBarber(Barber barber) {
        return barberRepository.save(barber);
    }

    public List<Barber> getAllBarbers() {
        return barberRepository.findAll();
    }

    public List<BarberSchedule> getAllSchedules() {
        return barberScheduleRepository.findAll();
    }

    @Transactional
    public boolean reserveSchedule(ReservationRequest reservationRequest) throws ScheduleNotFoundException {
        Optional<BarberSchedule> scheduleOpt = barberScheduleRepository.findById(reservationRequest.getScheduleId());

        if (!scheduleOpt.isPresent()) {
            throw new ScheduleNotFoundException("Schedule not found");
        }

        BarberSchedule schedule = scheduleOpt.get();

        if (schedule.isReserved()) {
            return false;
        }

        // Krijo rezervimin dhe shëno orarin si të rezervuar
        ReservationRequest reservation = new ReservationRequest();
        reservation.setScheduleId(schedule.getId());
        reservation.setClientName(reservationRequest.getClientName());
        reservation.setClientEmail(reservationRequest.getClientEmail());

        schedule.setReserved(true);

        // Ruaj rezervimin dhe përditëso orarin në databazë
        reservationRepository.save(reservation);
        barberScheduleRepository.save(schedule);

        return true;
    }


}

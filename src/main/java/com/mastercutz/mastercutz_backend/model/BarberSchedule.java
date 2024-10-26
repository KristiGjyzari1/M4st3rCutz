package com.mastercutz.mastercutz_backend.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class BarberSchedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String barberName;
    private String date;
    private String timeSlot;
    private boolean available;

    private boolean reserved;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getBarberName() {
        return barberName;
    }

    public void setBarberName(String barberName) {
        this.barberName = barberName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public boolean isAvailable() {
        return available;
    }

    public boolean isReserved() {
        return reserved;
    }

    public void setReserved(boolean reserved) {
        this.reserved = reserved;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

}

package com.mastercutz.mastercutz_backend.dto;

public class BarberProfileUpdateDto {

    private String name;

    private String specialization;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSpecialization() {
        return specialization;
    }

    public void setSpecialization(String specalization) {
        this.specialization = specalization;
    }
}

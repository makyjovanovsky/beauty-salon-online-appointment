package com.example.salonbella.service.reservation;

import java.time.LocalDateTime;

public class ReservationResponse {
    private Long id;
    private LocalDateTime localDateTime;
    private String type;
    private boolean valid;
    private String name = "";
    private String surname = "";

    public ReservationResponse(Long id, LocalDateTime localDateTime, String type,boolean valid) {
        this.id = id;
        this.localDateTime = localDateTime;
        this.type = type;
        this.valid = valid;
    }

    public LocalDateTime getLocalDateTime() {
        return localDateTime;
    }

    public void setLocalDateTime(LocalDateTime localDateTime) {
        this.localDateTime = localDateTime;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public boolean isValid() {
        return valid;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    @Override
    public String toString() {
        return String.format("Date and time: %s\n Type: %s", localDateTime, type);
    }
}
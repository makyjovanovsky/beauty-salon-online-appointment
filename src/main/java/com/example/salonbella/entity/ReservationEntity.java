package com.example.salonbella.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservations")
public class ReservationEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


    @Column(name = "type", nullable = false, length = 30)
    private String type;

    @Column(name = "date_and_time", nullable = false, length = 30)
    private LocalDateTime dateTime;

    @Column(name = "valid", nullable = false)
    private boolean valid = true;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity user;


    public ReservationEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setUser(UserEntity user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public UserEntity getUser() {
        return user;
    }

    public void setValid(boolean valid) {
        this.valid = valid;
    }

    public boolean isValid() {
        return valid;
    }
}

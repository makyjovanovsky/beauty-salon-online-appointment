package com.example.salonbella.repository;

import com.example.salonbella.entity.ReservationEntity;
import com.example.salonbella.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<ReservationEntity, Long> {
    List<ReservationEntity> findByTypeAndDateTimeBetween(String type,LocalDateTime start, LocalDateTime end);

    List<ReservationEntity> findAllByUser(UserEntity user);

    List<ReservationEntity> findAllByValid(boolean valid);

    ReservationEntity findReservationEntityByDateTimeAndType(LocalDateTime dateTime, String type);
}

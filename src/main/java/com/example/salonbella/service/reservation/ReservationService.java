package com.example.salonbella.service.reservation;

import com.example.salonbella.entity.ReservationEntity;
import com.example.salonbella.entity.UserEntity;
import com.example.salonbella.repository.ReservationRepository;
import com.example.salonbella.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final UserRepository userRepository;

    @Autowired
    public ReservationService(ReservationRepository reservationRepository, UserRepository userRepository) {
        this.reservationRepository = reservationRepository;
        this.userRepository = userRepository;
    }

    // get all reservations for specified date and type
    private List<ReservationEntity> getReservations(String date, String type) {
        LocalDateTime start = stringToDateAndTime(date, "00:00");
        LocalDateTime end = stringToDateAndTime(date, "23:59");
        return reservationRepository.findByTypeAndDateTimeBetween(type, start, end);
    }

    // make reservation
    public void makeReservation(String date, String time, String type) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username);
        ReservationEntity reservationEntity = new ReservationEntity();
        reservationEntity.setDateTime(stringToDateAndTime(date, time));
        reservationEntity.setType(type);
        reservationEntity.setUser(user);
        reservationRepository.save(reservationEntity);
    }

    // convert string to localdatetime
    private LocalDateTime stringToDateAndTime(String date, String time) {
        int year = Integer.parseInt(date.split("-")[0]);
        int month = Integer.parseInt(date.split("-")[1]);
        int day = Integer.parseInt(date.split("-")[2]);
        int hour = Integer.parseInt(time.split(":")[0]);
        int minutes = Integer.parseInt(time.split(":")[1]);
        LocalDate tempDate = LocalDate.of(year, month, day);
        LocalTime tempTime = LocalTime.of(hour, minutes);
        return LocalDateTime.of(tempDate, tempTime);
    }

    // get free reservations
    public List<LocalTime> getFreeReservations(String date, String type) {

        List<ReservationEntity> reservations = getReservations(date, type);
        List<LocalTime> freeReservations = getWorkingHours();
        for (ReservationEntity r : reservations) {
            LocalTime temp = r.getDateTime().toLocalTime();
            for (LocalTime l : freeReservations) {
                if (temp.equals(l)) {
                    freeReservations.remove(l);
                    break;
                }
            }
        }
        return freeReservations;
    }

    // get all reservations for specified date and type
    public List<ReservationResponse> getScheduledReservations(String date, String type) {
        List<ReservationEntity> reservations = getReservations(date, type);
        List<ReservationResponse> result = new ArrayList<>();
        for (ReservationEntity r : reservations) {
            ReservationResponse reservationResponse = new ReservationResponse(r.getId(), r.getDateTime(), r.getType(), r.isValid());
            reservationResponse.setName(r.getUser().getName());
            reservationResponse.setSurname(r.getUser().getSurname());
            result.add(reservationResponse);
        }
        return result;
    }

    // get all user reservations, return another class for better security
    public List<ReservationResponse> getUserReservations() {
        UserEntity user = getUser();
        List<ReservationEntity> reservationEntities = reservationRepository.findAllByUser(user);
        List<ReservationResponse> result = new ArrayList<>();
        for (ReservationEntity r : reservationEntities) {
            result.add(new ReservationResponse(r.getId(), r.getDateTime(), r.getType(), r.isValid()));
        }
        return result;
    }


    // get current user
    public UserEntity getUser() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        String username = auth.getName();
        UserEntity user = userRepository.findByUsername(username);
        return user;
    }

    // cancel reservation - user
    @Transactional
    public void cancelReservation(Long id) {
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(id);
        if (reservationEntity.isPresent()) {
            if (getUser().getId().equals(reservationEntity.get().getUser().getId())) {
                reservationRepository.delete(reservationEntity.get());
            }
        }
    }

    // cancel reservation - user
    @Transactional
    public void cancelReservationAdmin(Long id) {
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(id);
        reservationEntity.ifPresent(reservationRepository::delete);
    }

    // block reservation

    public void blockReservation(String date, String time, String type) {
        Optional<ReservationEntity> reservationEntity = Optional.ofNullable(reservationRepository.findReservationEntityByDateTimeAndType(stringToDateAndTime(date, time), type));
        if (reservationEntity.isPresent()) {
            reservationEntity.get().setValid(false);
            reservationRepository.save(reservationEntity.get());
        } else {
            ReservationEntity reservation = new ReservationEntity();
            reservation.setDateTime(stringToDateAndTime(date, time));
            reservation.setType(type);
            reservation.setValid(false);
            reservation.setUser(getUser());
            reservationRepository.save(reservation);
        }
    }

    // get blocked reservations
    public List<ReservationResponse> getBlockedReservations() {
        List<ReservationEntity> reservationEntities = reservationRepository.findAllByValid(false);
        List<ReservationResponse> result = new ArrayList<>();
        for (ReservationEntity r : reservationEntities) {
            result.add(new ReservationResponse(r.getId(), r.getDateTime(), r.getType(), r.isValid()));
        }
        return result;
    }

    // unblock reservation
    @Transactional
    public void unblockReservation(Long id) {
        Optional<ReservationEntity> reservationEntity = reservationRepository.findById(id);
        if (reservationEntity.isPresent()) {
            if (reservationEntity.get().getUser().getName().equals("admin")) {
                reservationRepository.delete(reservationEntity.get());
            } else {
                reservationEntity.get().setValid(true);
            }
        }
    }

    // get working hours for salon
    public List<LocalTime> getWorkingHours() {
        List<LocalTime> temp = new ArrayList<>();
        for (int i = 9; i <= 21; i++) {
            temp.add(LocalTime.of(i, 0));
        }
        return temp;
    }


}


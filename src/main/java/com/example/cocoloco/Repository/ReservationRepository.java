package com.example.cocoloco.Repository;

import com.example.cocoloco.Model.Reservation;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends MongoRepository<Reservation, String> {
    List<Reservation> findByReservationDate(LocalDate reservationDate);
}

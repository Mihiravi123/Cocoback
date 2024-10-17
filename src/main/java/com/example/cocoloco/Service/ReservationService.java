package com.example.cocoloco.Service;

import com.example.cocoloco.Model.Reservation;
import com.example.cocoloco.Repository.ReservationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class ReservationService {

    @Autowired
    private ReservationRepository reservationRepository;

    // Fetch all reservations
    public List<Reservation> getAllReservations() {
        return reservationRepository.findAll();
    }


    // Fetch a single reservation by ID
    public Optional<Reservation> getReservationById(String id) {
        return reservationRepository.findById(id);
    }

    // Add a new reservation
    public Reservation addReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    // Update an existing reservation by ID
    public Reservation updateReservation(String id, Reservation updatedReservation) {
        Optional<Reservation> existingReservation = reservationRepository.findById(id);
        if (existingReservation.isPresent()) {
            Reservation reservation = existingReservation.get();
            // Update the fields of the existing reservation with the new data
            reservation.setFullName(updatedReservation.getFullName());
            reservation.setEmail(updatedReservation.getEmail());
            reservation.setContactNo(updatedReservation.getContactNo());
            reservation.setTimeSlot(updatedReservation.getTimeSlot());
            reservation.setNumberOfPack(updatedReservation.getNumberOfPack());
            reservation.setEvent(updatedReservation.getEvent());
            reservation.setMessage(updatedReservation.getMessage());
            reservation.setBuffet(updatedReservation.getBuffet());
            reservation.setBuffetPrice(updatedReservation.getBuffetPrice());
            reservation.setServiceCharge(updatedReservation.getServiceCharge());
            reservation.setTotalPrice(updatedReservation.getTotalPrice());
            reservation.setAdvancePayment(updatedReservation.getAdvancePayment());
            reservation.setPayment(updatedReservation.getPayment());
            reservation.setDuePayment(updatedReservation.getDuePayment());
            reservation.setPaymentMethod(updatedReservation.getPaymentMethod());
            reservation.setAssignedBy(updatedReservation.getAssignedBy());
            reservation.setSpecialNote1(updatedReservation.getSpecialNote1());
            reservation.setSpecialNote2(updatedReservation.getSpecialNote2());
            reservation.setStatus(updatedReservation.getStatus());
            reservation.setReservationDate(updatedReservation.getReservationDate());

            return reservationRepository.save(reservation);  // Save the updated reservation
        } else {
            return null;  // Return null if reservation not found
        }
    }

    // Delete a reservation by ID
    public void deleteReservation(String id) {
        reservationRepository.deleteById(id);
    }

    public Reservation saveReservation(Reservation reservation) {
        return reservationRepository.save(reservation);
    }

    public List<Reservation> getReservationsByDate(LocalDate date) {
        return reservationRepository.findByReservationDate(date);
    }


}

package com.example.cocoloco.Controller;

import com.example.cocoloco.Model.Reservation;
import com.example.cocoloco.Service.EmailService;
import com.example.cocoloco.Service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RequestMapping("/api/reservation")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @Autowired
    private EmailService emailService; // Properly inject the EmailService

    // Get all reservations
    @GetMapping
    public List<Reservation> getAllReservations() {
        return reservationService.getAllReservations();
    }

    // Get a single reservation by ID
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getReservationById(@PathVariable String id) {
        return reservationService.getReservationById(id)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Add a new reservation
    @PostMapping
    public ResponseEntity<String> addReservation(@RequestBody Reservation reservation) {
        // Fetch existing reservations for the given date
        List<Reservation> existingReservations = reservationService.getReservationsByDate(reservation.getReservationDate());

        // Check if "full" is already reserved for this date
        boolean isFullReserved = existingReservations.stream()
                .anyMatch(existingReservation -> existingReservation.getTimeSlot().equalsIgnoreCase("full"));

        // Prevent booking "day" or "night" if "full" is reserved
        if (isFullReserved && (reservation.getTimeSlot().equalsIgnoreCase("day") || reservation.getTimeSlot().equalsIgnoreCase("night"))) {
            return ResponseEntity.badRequest().body("Error: The 'full' time slot is already reserved.");
        }

        // Check if "day" or "night" reservations exist
        boolean isDayBooked = existingReservations.stream()
                .anyMatch(existingReservation -> existingReservation.getTimeSlot().equalsIgnoreCase("day"));
        boolean isNightBooked = existingReservations.stream()
                .anyMatch(existingReservation -> existingReservation.getTimeSlot().equalsIgnoreCase("night"));

        // Prevent booking "full" if either "day" or "night" is booked
        if (reservation.getTimeSlot().equalsIgnoreCase("full") && (isDayBooked || isNightBooked)) {
            return ResponseEntity.badRequest().body("Error: 'Day' or 'Night' time slot is already reserved, so 'full' cannot be booked.");
        }

        // Prevent booking the same time slot again
        boolean isTimeSlotAlreadyBooked = existingReservations.stream()
                .anyMatch(existingReservation -> existingReservation.getTimeSlot().equalsIgnoreCase(reservation.getTimeSlot()));

        if (isTimeSlotAlreadyBooked) {
            return ResponseEntity.badRequest().body("Error: This time slot is already reserved.");
        }

        // If the time slot is available, proceed to add the reservation
        Reservation newReservation = reservationService.addReservation(reservation);
        return ResponseEntity.ok("Reservation successfully added with ID: " + newReservation.getId());
    }


    // Update a reservation by ID
    @PutMapping("/{id}")
    public ResponseEntity<Reservation> updateReservation(@PathVariable String id, @RequestBody Reservation updatedReservation) {
        Reservation reservation = reservationService.updateReservation(id, updatedReservation);

        if (reservation != null) {
            // After successfully updating, check the status
            String status = reservation.getStatus();

            // Construct email body based on reservation status
            String emailBody;
            String subject;

            if ("Confirm".equalsIgnoreCase(status)) {
                // Construct the email body for confirmed status
                emailBody = String.format(
                        "Thank you for making a reservation with Cocolooco Garden, %s.\n\n" +
                                "Your reservation is confirmed!\n\n" +
                                "Reservation Date: %s\n\n" +
                                "Time Slot: %s\n\n" +
                                "Number Of Packages: %s\n\n" +
                                "Event Name: %s\n\n" +
                                "For any clarifications, please call Cocoloco Garden Reception.\n\n" +
                                "Cocoloco Garden\n" +
                                "Telephone No: +94 77 782 8629",
                        reservation.getFullName(),
                        reservation.getReservationDate(),
                        reservation.getTimeSlot(),
                        reservation.getNumberOfPack(),
                        reservation.getEvent()
                );
                subject = "Cocoloco Garden - Reservation Confirmed.";
            } else if ("Reject".equalsIgnoreCase(status)) {
                // Construct the email body for rejected status
                emailBody = String.format(
                        "Dear %s,\n\n" +
                                "We regret to inform you that your reservation at Cocoloco Garden on %s has been rejected!\n\n" +
                                "Cocoloco Garden - %s\n" +
                                "Telephone No: +94 77 782 8629",
                        reservation.getFullName(),
                        reservation.getReservationDate(),
                        reservation.getEvent()
                );
                subject = "Cocoloco Garden - Reservation Rejected.";
            } else if ("Pending".equalsIgnoreCase(status)) {
                // Construct the email body for pending status
                emailBody = String.format(
                        "Thank you for making a reservation with Cocolooco Garden, %s.\n\n" +
                                "Your reservation is tentatively booked!\n\n" +
                                "Reservation Date: %s\n\n" +
                                "Time Slot: %s\n\n" +
                                "Number Of Packages: %s\n\n" +
                                "Event Name: %s\n\n" +
                                "To confirm your reservation, please make an advance payment of Rs. 25,000 within 24 hours.\n\n" +
                                "If you are unable to complete the advance payment, your reservation will be automatically canceled.\n\n" +
                                "For any clarifications, please call Cocoloco Garden Reception.\n\n" +
                                "Cocoloco Garden\n" +
                                "Telephone No: +94 77 782 8629",
                        reservation.getFullName(),
                        reservation.getReservationDate(),
                        reservation.getTimeSlot(),
                        reservation.getNumberOfPack(),
                        reservation.getEvent()
                );
                subject = "Cocoloco Garden - Reservation tentatively booked.";
            } else {
                // Handle unknown status case, if needed
                return ResponseEntity.badRequest().body(null);
            }

            // Send the email based on the status
            emailService.sendEmail(reservation.getEmail(), subject, emailBody);

            return ResponseEntity.ok(reservation); // Return the updated reservation
        } else {
            return ResponseEntity.notFound().build();
        }
    }




    // Delete a reservation by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteReservation(@PathVariable String id) {
        reservationService.deleteReservation(id);
        return ResponseEntity.noContent().build();
    }

    // Get reservations by date
    @GetMapping("/date/{reservationDate}")
    public List<Reservation> getReservationsByDate(@PathVariable String reservationDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(reservationDate, formatter);
        return reservationService.getReservationsByDate(date);
    }

    // Check availability for a specific date
    @GetMapping("/checkAvailability")
    public ResponseEntity<String> checkAvailability(@RequestParam String reservationDate) {
        // Parse the reservation date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(reservationDate, formatter);

        // Fetch reservations for the date
        List<Reservation> reservations = reservationService.getReservationsByDate(date);

        // Check if the full day is booked
        boolean isFullDayBooked = reservations.stream()
                .anyMatch(reservation -> reservation.getTimeSlot().equalsIgnoreCase("full"));
        if (isFullDayBooked) {
            return ResponseEntity.ok("full");
        }

        // Check individual time slots (day, night)
        boolean isDayBooked = reservations.stream()
                .anyMatch(reservation -> reservation.getTimeSlot().equalsIgnoreCase("day"));
        boolean isNightBooked = reservations.stream()
                .anyMatch(reservation -> reservation.getTimeSlot().equalsIgnoreCase("night"));

        if (isDayBooked && isNightBooked) {
            return ResponseEntity.ok("full");
        } else if (isDayBooked) {
            return ResponseEntity.ok("night");
        } else if (isNightBooked) {
            return ResponseEntity.ok("day");
        } else {
            return ResponseEntity.ok("day, night");
        }
    }
}

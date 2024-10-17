package com.example.cocoloco.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.util.List;

@Document(collection = "reservation")
public class Reservation {

    @Id
    private String id;
    private String fullName;
    private String email;
    private String contactNo;
    private String timeSlot;
    private int numberOfPack;
    private String event; // Added event field
    private String message;
    private String buffet;
    private double buffetPrice;
    private double serviceCharge;
    private double totalPrice;
    private double advancePayment;
    private double payment;
    private double duePayment; // Added duePayment field
    private String paymentMethod;
    private String assignedBy;
    private String specialNote1;
    private String specialNote2;
    private String status;
    private LocalDate reservationDate;

    // Getters and setters

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactNo() {
        return contactNo;
    }

    public void setContactNo(String contactNo) {
        this.contactNo = contactNo;
    }

    public String getTimeSlot() {
        return timeSlot;
    }

    public void setTimeSlot(String timeSlot) {
        this.timeSlot = timeSlot;
    }

    public int getNumberOfPack() {
        return numberOfPack;
    }

    public void setNumberOfPack(int numberOfPack) {
        this.numberOfPack = numberOfPack;
    }

    public String getEvent() {
        return event; // Getter for event
    }

    public void setEvent(String event) {
        this.event = event; // Setter for event
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getBuffet() {
        return buffet;
    }

    public void setBuffet(String buffet) {
        this.buffet = buffet;
    }

    public double getBuffetPrice() {
        return buffetPrice;
    }

    public void setBuffetPrice(double buffetPrice) {
        this.buffetPrice = buffetPrice;
    }


    public double getServiceCharge() {
        return serviceCharge;
    }

    public void setServiceCharge(double serviceCharge) {
        this.serviceCharge = serviceCharge;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(double totalPrice) {
        this.totalPrice = totalPrice;
    }

    public double getAdvancePayment() {
        return advancePayment;
    }

    public void setAdvancePayment(double advancePayment) {
        this.advancePayment = advancePayment;
    }

    public double getPayment() {
        return payment;
    }

    public void setPayment(double payment) {
        this.payment = payment;
    }

    public double getDuePayment() {
        return duePayment; // Getter for duePayment
    }

    public void setDuePayment(double duePayment) {
        this.duePayment = duePayment; // Setter for duePayment
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public String getAssignedBy() {
        return assignedBy;
    }

    public void setAssignedBy(String assignedBy) {
        this.assignedBy = assignedBy;
    }

    public String getSpecialNote1() {
        return specialNote1;
    }

    public void setSpecialNote1(String specialNote1) {
        this.specialNote1 = specialNote1;
    }

    public String getSpecialNote2() {
        return specialNote2;
    }

    public void setSpecialNote2(String specialNote2) {
        this.specialNote2 = specialNote2;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDate getReservationDate() {
        return reservationDate;
    }

    public void setReservationDate(LocalDate reservationDate) {
        this.reservationDate = reservationDate;
    }
}

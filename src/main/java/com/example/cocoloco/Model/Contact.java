package com.example.cocoloco.Model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document(collection = "contact")
public class Contact {

    @Id
    private String id;
    private String name;
    private String email;
    private String functionDate;
    private String event;
    private String specialNote;

    // New fields
    private String phone;      // Field to store the phone number
    private String replyNote;  // A note to store any reply or response
    private String status;     // A field to track the status of the contact form (e.g., "pending", "replied")

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFunctionDate() {
        return functionDate;
    }

    public void setFunctionDate(String functionDate) {
        this.functionDate = functionDate;
    }

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public String getSpecialNote() {
        return specialNote;
    }

    public void setSpecialNote(String specialNote) {
        this.specialNote = specialNote;
    }

    // Getter and Setter for phone
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    // Getters and Setters for replyNote and status
    public String getReplyNote() {
        return replyNote;
    }

    public void setReplyNote(String replyNote) {
        this.replyNote = replyNote;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}

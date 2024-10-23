package com.example.cocoloco.Controller;

import com.example.cocoloco.Model.Contact;
import com.example.cocoloco.Service.ContactService;
import com.example.cocoloco.Service.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
public class ContactController {

    @Autowired
    private ContactService contactService;

    @Autowired
    private EmailService emailService; // Properly inject the EmailService

    // POST: Create a new contact
    @PostMapping
    public ResponseEntity<Contact> addContact(@RequestBody Contact contact) {
        Contact createdContact = contactService.addContact(contact);

        // The email notification functionality has been removed
        return ResponseEntity.ok(createdContact);
    }



    // GET: Retrieve all contacts
    @GetMapping
    public ResponseEntity<List<Contact>> getAllContacts() {
        List<Contact> contacts = contactService.getAllContacts();
        return ResponseEntity.ok(contacts);
    }

    // GET: Retrieve a contact by ID
    @GetMapping("/{id}")
    public ResponseEntity<Contact> getContactById(@PathVariable String id) {
        Optional<Contact> contact = contactService.getContactById(id);
        return contact.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Contact> updateContact(@PathVariable String id, @RequestBody Contact updatedContact) {
        Contact contact = contactService.updateContact(id, updatedContact);

        if (contact != null) {
            // Check the status of the updated query
            String status = contact.getStatus();
            if ("done".equalsIgnoreCase(status)) {
                String emailBody = String.format(
                        "Thank you for reaching out to Cocoloco Garden, %s.\n\n" +
                                "This is regarding your query about: %s\n\n" +
                                "Response: %s\n\n" +
                                "If you have any further questions, please contact the ABC Restaurant Front Desk.\n\n" +
                                "ABC RESTAURANT\n" +
                                "Telephone No: +94 77 782 8629",
                        contact.getName(),
                        contact.getEvent(),
                        contact.getReplyNote()
                );

                // Send the email for confirmed status
                emailService.sendEmail(
                        contact.getEmail(), // Assuming the email should be sent to the query's email
                        "Query Status Response",
                        emailBody
                );
            }

            return ResponseEntity.ok(contact);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    // DELETE: Remove a contact by ID
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteContact(@PathVariable String id) {
        contactService.deleteContact(id);
        return ResponseEntity.noContent().build();
    }
}

package com.example.cocoloco.Service;

import com.example.cocoloco.Model.Contact;
import com.example.cocoloco.Repository.ContactRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContactService {

    @Autowired
    private ContactRepository contactRepository;

    // Create a new contact entry
    public Contact addContact(Contact contact) {
        contact.setStatus("pending"); // Default status when adding a new contact
        return contactRepository.save(contact);
    }

    // Get all contacts
    public List<Contact> getAllContacts() {
        return contactRepository.findAll();
    }

    // Get contact by ID
    public Optional<Contact> getContactById(String id) {
        return contactRepository.findById(id);
    }

    // Update a contact, including status and replyNote
    public Contact updateContact(String id, Contact updatedContact) {
        Optional<Contact> contactData = contactRepository.findById(id);
        if (contactData.isPresent()) {
            Contact existingContact = contactData.get();
            existingContact.setName(updatedContact.getName());
            existingContact.setEmail(updatedContact.getEmail());
            existingContact.setFunctionDate(updatedContact.getFunctionDate());
            existingContact.setEvent(updatedContact.getEvent());
            existingContact.setSpecialNote(updatedContact.getSpecialNote());
            existingContact.setReplyNote(updatedContact.getReplyNote());  // Set reply note
            existingContact.setStatus(updatedContact.getStatus());        // Set status
            return contactRepository.save(existingContact);
        } else {
            return null;
        }
    }

    // Delete a contact by ID
    public void deleteContact(String id) {
        contactRepository.deleteById(id);
    }
}

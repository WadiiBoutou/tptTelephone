package com.example.telephoneBackend;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/contact")
@CrossOrigin(origins = "*")
public class ContactController {
@Autowired
private UserRepository userRepository;
    @Autowired
    private ContactRepository contactRepository;
    @PostMapping("/add")
    public ResponseEntity<Void> addContact(@RequestBody ContactDTO contactDTO) {
        Optional<User> userOptional = userRepository.findById(contactDTO.getUserId());
        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Check if the contact already exists
            boolean contactExists = contactRepository.existsByNumberAndUser(contactDTO.getNumber(), user);
            if (contactExists) {
                return ResponseEntity.status(HttpStatus.CONFLICT).build(); // Return 409 Conflict if contact exists
            }

            // Create and save the new contact
            Contact contact = new Contact();
            contact.setName(contactDTO.getName());
            contact.setNumber(contactDTO.getNumber());
            contact.setUser(user);

            contactRepository.save(contact);
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}

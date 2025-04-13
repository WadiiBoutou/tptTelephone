package com.example.telephoneBackend;

import com.example.telephoneBackend.Contact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactRepository extends JpaRepository<Contact, Long> {

    boolean existsByNumberAndUser(String number, User user);
}

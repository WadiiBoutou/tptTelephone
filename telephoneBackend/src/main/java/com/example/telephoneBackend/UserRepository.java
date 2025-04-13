package com.example.telephoneBackend;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByImei(String imei);
    Optional<User> findByImei(String imei);



}

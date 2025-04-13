package com.example.telephoneBackend;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/user")
@CrossOrigin(origins = "*")
public class UserController {

    @Autowired
    private UserRepository userRepository;

    @PostMapping("/add")
    public ResponseEntity<String> addUser(@RequestBody User user) {
        userRepository.save(user);
        return ResponseEntity.ok("User ajouté ");
    }
    @GetMapping("/check/{imei}")
    public ResponseEntity<Boolean> checkUserExist(@PathVariable String imei) {
        boolean exists = userRepository.existsByImei(imei);
        return ResponseEntity.ok(exists);
    }
    @GetMapping("/by-imei/{imei}")
    public ResponseEntity<Long> getUserIdByImei(@PathVariable String imei) {
        Optional<User> user = userRepository.findByImei(imei);
        return user.map(u -> ResponseEntity.ok(u.getId()))
                .orElse(ResponseEntity.notFound().build());
    }
}

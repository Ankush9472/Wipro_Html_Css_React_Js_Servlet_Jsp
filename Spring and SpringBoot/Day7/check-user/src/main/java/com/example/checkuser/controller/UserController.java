package com.example.checkuser.controller;

import com.example.checkuser.exception.AgeNotFoundException;
import com.example.checkuser.model.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        if (user.getAge() < 18)
        {
            throw new AgeNotFoundException("Age is lesss than 18--> invalid");
        }

        else

        {
            return ResponseEntity.ok("successfully registered: " + user.toString());
        }
    }
}
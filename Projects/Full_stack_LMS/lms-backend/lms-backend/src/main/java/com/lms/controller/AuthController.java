package com.lms.controller;

import com.lms.dto.ApiResponse;
import com.lms.dto.LoginRequest;
import com.lms.dto.LoginResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

@RestController
@RequestMapping("/api/auth")
@CrossOrigin(origins = {"http://localhost:5173", "http://localhost:5174", "http://localhost:3000"})
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@Valid @RequestBody LoginRequest req) {
        Authentication auth = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(req.getUsername(), req.getPassword())
        );

        String role = "USER";
        for (GrantedAuthority a : auth.getAuthorities()) {
            String s = a.getAuthority();
            if (s.equals("ROLE_ADMIN")) {
                role = "ADMIN";
                break;
            }
        }

        // Basic-auth token for the frontend to send back in Authorization header
        String raw = req.getUsername() + ":" + req.getPassword();
        String token = Base64.getEncoder().encodeToString(raw.getBytes());

        LoginResponse res = new LoginResponse(token, req.getUsername(), role, "Login successful");
        return ResponseEntity.ok(res);
    }

    @GetMapping("/me")
    public ResponseEntity<ApiResponse> me(Authentication authentication) {
        if (authentication == null) {
            return ResponseEntity.status(401).body(new ApiResponse(false, "Not authenticated"));
        }
        return ResponseEntity.ok(new ApiResponse(true, "Current user", authentication.getName()));
    }
}

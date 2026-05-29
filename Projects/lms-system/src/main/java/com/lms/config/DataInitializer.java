package com.lms.config;

import com.lms.entity.UserAccount;
import com.lms.repository.UserAccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserAccountRepository userAccountRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        if (userAccountRepository.findByUsername("admin").isEmpty()) {
            userAccountRepository.save(new UserAccount("admin", passwordEncoder.encode("admin123"), "ADMIN"));
        }
        if (userAccountRepository.findByUsername("user").isEmpty()) {
            userAccountRepository.save(new UserAccount("user", passwordEncoder.encode("user123"), "USER"));
        }
    }
}
package com.lms.config;

import com.lms.entity.Course;
import com.lms.entity.UserAccount;
import com.lms.repository.CourseRepository;
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
    private CourseRepository courseRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        // Default accounts
        if (userAccountRepository.findByUsername("admin").isEmpty()) {
            userAccountRepository.save(new UserAccount("admin", passwordEncoder.encode("admin123"), "ADMIN"));
        }
        if (userAccountRepository.findByUsername("user").isEmpty()) {
            userAccountRepository.save(new UserAccount("user", passwordEncoder.encode("user123"), "USER"));
        }

        // Sample courses
        if (courseRepository.count() == 0) {
            Course c1 = new Course();
            c1.setCode("JFS101");
            c1.setTitle("Java Full Stack");
            c1.setDescription("Complete Java Full Stack development course");
            c1.setCategory("Development");
            c1.setInstructor("Rohit Kumar");
            c1.setDuration("12 Weeks");
            c1.setFee(12000);
            c1.setCourseLink("https://www.youtube.com/watch?v=bm0OyhwFDuY&list=PLsyeobzWxl7pe_IiTfNyr55kwJPWbgxB5");
            courseRepository.save(c1);

            Course c2 = new Course();
            c2.setCode("RM201");
            c2.setTitle("React Mastery");
            c2.setDescription("Master React with hooks, context and advanced patterns");
            c2.setCategory("Frontend");
            c2.setInstructor("Rahul Kumar");
            c2.setDuration("8 Weeks");
            c2.setFee(9000);
            c2.setCourseLink("https://www.youtube.com/watch?v=LDB4uaJ87e0");
            courseRepository.save(c2);

            Course c3 = new Course();
            c3.setCode("SBP301");
            c3.setTitle("Spring Boot Pro");
            c3.setDescription("Professional Spring Boot backend development");
            c3.setCategory("Backend");
            c3.setInstructor("Ruchi");
            c3.setDuration("10 Weeks");
            c3.setFee(11000);
            c3.setCourseLink("http://youtube.com/watch?v=gJrjgg1KVL4");
            courseRepository.save(c3);
        }

        System.out.println("===== LMS Backend Initialized =====");
        System.out.println("Admin login: admin / admin123");
        System.out.println("User login:  user  / user123");
        System.out.println("===================================");
    }
}
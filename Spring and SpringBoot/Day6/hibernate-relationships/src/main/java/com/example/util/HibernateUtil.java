package com.example.util;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import com.example.model.Course;
import com.example.model.ReportCard;
import com.example.model.Student;

public class HibernateUtil {

    private static final SessionFactory sessionFactory = buildSessionFactory();

    private static SessionFactory buildSessionFactory() {
        try {
            return new Configuration()
                    .configure("hibernate.cfg.xml")
                    .addAnnotatedClass(Student.class)
                    .addAnnotatedClass(ReportCard.class)
                    .addAnnotatedClass(Course.class)        // ← NEW!
                    .buildSessionFactory();
        } catch (Exception ex) {
            throw new RuntimeException("Initial SessionFactory creation failed." + ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public static void close() {
        sessionFactory.close();
    }
}
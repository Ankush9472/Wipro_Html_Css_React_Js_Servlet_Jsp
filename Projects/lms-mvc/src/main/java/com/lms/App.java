package com.lms;

import com.lms.config.HibernateUtil;
import com.lms.entity.Course;
import com.lms.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * Standalone test class - run this WITHOUT Tomcat.
 * Right-click → Run As → Java Application
 *
 * Tests that Hibernate, MySQL, and ManyToMany relationships work correctly.
 */
public class App {

    public static void main(String[] args) {

        // Open a Session from SessionFactory
        Session session = HibernateUtil.getSessionFactory().openSession();

        // Begin a transaction
        Transaction tx = session.beginTransaction();

        // Create courses
        Course c1 = new Course("Operating System");
        Course c2 = new Course("Microprocessor");

        // Create students
        Student ritu = new Student("Ritu");
        Student sakshi = new Student("Sakshi");
        Student shubham = new Student("Shubham");

        // Enroll students in courses using the helper method
        ritu.addCourse(c1);
        ritu.addCourse(c2);

        sakshi.addCourse(c1);

        shubham.addCourse(c2);

        // Save students (CascadeType.ALL will save courses too)
        session.persist(ritu);
        session.persist(sakshi);
        session.persist(shubham);

        tx.commit();

        // Verify by fetching a course and listing its students
        Course course = session.get(Course.class, 1L);
        System.out.println("Course details: " + course.getTitle());
        course.getStudents().forEach(
            st -> System.out.println("These students are enrolled: " + st.getName())
        );

        session.close();
        HibernateUtil.shutdown();
    }
}
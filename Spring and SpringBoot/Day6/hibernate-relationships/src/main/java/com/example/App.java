package com.example;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.example.model.Course;
import com.example.model.ReportCard;
import com.example.model.Student;
import com.example.util.HibernateUtil;

/**
 * Hello world!
 */
public class App {

    public static void main(String[] args) {

        // To obtain/get the session from sessionfactory
        // (It can be multiple sessions based on multiple services or work)
        Session session = HibernateUtil.getSessionFactory().openSession();

        // To create a transaction (Transient + Persistent)
        // Transient = data not yet saved; Persistent = data saved in backend
        Transaction tx = session.beginTransaction();

        // ─── Student 1: Niti
        Student s1 = new Student("Niti");
        s1.addCourse(new Course("Java"));
        s1.addCourse(new Course("PD"));
        ReportCard rc1 = new ReportCard(450);
        s1.setReportcard(rc1);

        // ─── Student 2: Rahul
        Student s2 = new Student("Rahul");
        s2.addCourse(new Course("Spring"));
        s2.addCourse(new Course("Hibernate"));
        ReportCard rc2 = new ReportCard(500);
        s2.setReportcard(rc2);

        // ─── Student 3: Amit
        Student s3 = new Student("Amit");
        s3.addCourse(new Course("React"));
        s3.addCourse(new Course("JS"));
        ReportCard rc3 = new ReportCard(420);
        s3.setReportcard(rc3);

        // Persist all (cascade will handle children — courses + report cards)
        session.persist(s1);
        session.persist(s2);
        session.persist(s3);

        tx.commit();
        session.close();

        // ─── Fetch and display Student with ID 1
        Session getSession = HibernateUtil.getSessionFactory().openSession();

        Student stud = getSession.get(Student.class, 1L);
        System.out.println("Student Details : " + stud.getName());
        stud.getCourses().forEach(c -> System.out.println("Courses enrolled for : " + c.getTitle()));

        getSession.close();
        HibernateUtil.close();

        /* For bidirectional access:
        Course c = session.get(Course.class, 1L);
        System.out.println("Course: " + c.getTitle() + " belongs to Student: " + c.getStudent().getName());
        */
    }
}
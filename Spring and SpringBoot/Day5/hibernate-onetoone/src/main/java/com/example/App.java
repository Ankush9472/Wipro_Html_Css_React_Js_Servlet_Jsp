package com.example;

import com.example.model.ReportCard;
import com.example.model.Student;
import com.example.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

public class App {

    public static void main(String[] args) {

        try (Session session = HibernateUtil.getSessionFactory().openSession()) {

            Transaction tx = session.beginTransaction();

            //  CREATE: Save Student + ReportCard together

            // Create ReportCard
            ReportCard reportCard = new ReportCard(97);

            // Create Student and link ReportCard
            Student student = new Student("Ankush");
            student.setReportcard(reportCard);

            // Save Student — Hibernate will auto-save ReportCard too (cascade!)
            session.persist(student);

            tx.commit();

            System.out.println("Student and ReportCard saved successfully!");

            // READ: Fetch and display the saved student

            Student fetched = session.get(Student.class, student.getId());
            System.out.println("\nFetched: " + fetched);
        }

        HibernateUtil.close();
    }
}
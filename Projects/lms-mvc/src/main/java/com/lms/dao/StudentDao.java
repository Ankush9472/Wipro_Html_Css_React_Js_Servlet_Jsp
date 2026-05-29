package com.lms.dao;

import com.lms.config.HibernateUtil;
import com.lms.entity.Course;
import com.lms.entity.Student;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Repository → tells Spring this is a DAO.
 */
@Repository
public class StudentDao {

    /**
     * Saves a new student to the database.
     */
    public void save(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.persist(student);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }

    /**
     * Fetches all students from the database.
     */
    public List<Student> findAll() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.createQuery("FROM Student", Student.class).list();
        }
    }

    /**
     * Finds a single student by ID.
     */
    public Student findById(Long id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            return session.get(Student.class, id);
        }
    }

    /**
     * Enrolls a student in multiple courses using the addCourse() helper
     * method that synchronizes both sides of the relationship.
     */
    public void enrollStudent(Long studentId, List<Long> courseIds) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();

            Student student = session.get(Student.class, studentId);
            if (student != null) {
                for (Long courseId : courseIds) {
                    Course course = session.get(Course.class, courseId);
                    if (course != null) {
                        student.addCourse(course);  // bidirectional sync
                    }
                }
                session.merge(student);
            }

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) transaction.rollback();
            e.printStackTrace();
        }
    }
}
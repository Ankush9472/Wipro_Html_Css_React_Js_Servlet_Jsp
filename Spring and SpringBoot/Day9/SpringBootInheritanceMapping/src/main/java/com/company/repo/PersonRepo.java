package com.company.repo;

import com.company.entity.base.Person;
import com.company.entity.base.Student;
import com.company.entity.base.Teacher;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface PersonRepo extends JpaRepository<Person, Long> {

    // JPQL
    @Query("select s from Student s")
    List<Student> findAllStudents();

    @Query("select t from Teacher t")
    List<Teacher> findAllTeachers();

    // Eagerly fetches the emails collection to avoid N+1 queries / LazyInitializationException
    @EntityGraph(attributePaths = {"emails"})
    @Query("select s from Student s")
    List<Student> findAllStudentsWithEmails();
}
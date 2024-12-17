package org.gestionbibliotheque.dao;

import org.gestionbibliotheque.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentDAO extends JpaRepository<Student,Long> {
    Student getStudentById(Long id);
}

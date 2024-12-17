package org.gestionbibliotheque;


import org.gestionbibliotheque.dao.StudentDAO;
import org.gestionbibliotheque.model.Student;
import org.gestionbibliotheque.service.StudentService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {
    @InjectMocks
    private StudentService studentService;
    @Mock
    private StudentDAO studentDAO;


    @Test
    void testAddStudent() {
        studentService.addStudent(Student.builder()
                        .id(1L)
                        .email("alice@example.com")
                        .name("Alice")
                .build());
        assertEquals(1, studentDAO.findAll().size());
        assertEquals("Alice", studentDAO.getStudentById(1L).getName());
    }

    @Test
    void testUpdateStudent() {
        studentService.addStudent(Student.builder()
                .id(1L)
                .email("alice@example.com")
                .name("Alice")
                .build());
        studentService.updateStudent(Student.builder()
                .id(1L)
                .email("alice.smith@example.com")
                .name("Alice Smith")
                .build());
        assertEquals("Alice Smith", studentDAO.getStudentById(1L).getName());
    }

    @Test
    void testDeleteStudent() {
        studentService.addStudent(Student.builder()
                .id(1L)
                .email("alice@example.com")
                .name("Alice")
                .build());
        studentService.deleteStudent(1L);
        assertNotNull(studentDAO.getStudentById(1L));
    }

    @Test
    void testGetAllStudents() {
        studentService.addStudent(Student.builder()
                .id(1L)
                .email("alice@example.com")
                .name("Alice")
                .build());
        studentService.addStudent(Student.builder()
                .id(2L)
                .email("bob@example.com")
                .name("Bob")
                .build());
        assertEquals(2, studentDAO.findAll().size());
    }
}

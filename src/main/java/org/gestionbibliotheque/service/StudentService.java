package org.gestionbibliotheque.service;

import lombok.RequiredArgsConstructor;
import org.gestionbibliotheque.dao.StudentDAO;
import org.gestionbibliotheque.model.Student;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StudentService {

    private final StudentDAO studentDAO;

    public void addStudent(Student student){
        studentDAO.save(student);
    }

    public List<Student> displayStudents(){
        return studentDAO.findAll();
    }

    public Student findStudentById(Long id){
        return studentDAO.getStudentById(id);
    }

    public void deleteStudent(Long id) {
        studentDAO.deleteById(id);
    }

    public void updateStudent(Student student) {
        Student student1 = studentDAO.getStudentById(student.getId());
        student1.setEmail(student.getEmail());
        student1.setName(student.getName());
        studentDAO.save(student1);
    }
}

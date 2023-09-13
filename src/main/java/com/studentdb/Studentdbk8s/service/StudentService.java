package com.studentdb.Studentdbk8s.service;

import com.studentdb.Studentdbk8s.domain.Student;
import com.studentdb.Studentdbk8s.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class StudentService {

    @Autowired
    StudentRepository studentRepository;

    public Student addStudent(Student student) {
        studentRepository.save(student);
        return student;
    }

    public void deleteStudent(Student student) {
        studentRepository.delete(student);
    }

    public Iterable<Student> getStudents(){
        return studentRepository.findAll();
    }
}
package com.jhooq.Jhooqk8s.service;

import com.jhooq.Jhooqk8s.domain.Student;
import com.jhooq.Jhooqk8s.repository.StudentRepository;
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
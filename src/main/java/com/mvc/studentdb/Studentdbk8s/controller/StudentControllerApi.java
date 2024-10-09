package com.mvc.studentdb.Studentdbk8s.controller;

import com.mvc.studentdb.Studentdbk8s.domain.Student;
import com.mvc.studentdb.Studentdbk8s.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
//@RequestMapping("api/v1")
public class StudentControllerApi {

    @Autowired
    private StudentService studentService;

    @GetMapping("/student")
    ResponseEntity<List<Student>> getAllStudents() {
        return new ResponseEntity<List<Student>>((List<Student>) studentService.getStudents(), HttpStatus.OK);
    }

    @GetMapping("/hello")
    public String hello() {
        return "Jenkins Pipeline Test: Docker -> DockerHub -> AKS -> Semi Automated";
    }

}

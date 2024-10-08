package com.mvc.studentdb.Studentdbk8s.controller;

import com.mvc.studentdb.Studentdbk8s.domain.Student;
import com.mvc.studentdb.Studentdbk8s.service.StudentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


@Controller
@RequestMapping("/")
class StudentController {
    final static DateFormat df = new SimpleDateFormat("yyyy-MM-dd");

    @Autowired
    StudentService studentService;

    @RequestMapping(method = RequestMethod.GET)
    String home() {
        return "student";
    }

    @RequestMapping(value = "student", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView addStudent(@RequestParam Integer rollNo,
                            @RequestParam String name,
                            @RequestParam String dateOfBirth ) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student");
        try {
            Student student = new Student();
            student.setRollNo(rollNo);
            student.setName(name);
            student.setDateOfBirth(df.parse(dateOfBirth));
            student = studentService.addStudent(student);
            modelAndView.addObject("message", "Student added with name: " + student.getName());
        }
        catch (Exception ex){
            modelAndView.addObject("message", "Failed to add student: " + ex.getMessage());
        }
        modelAndView.addObject("students", studentService.getStudents());
        return modelAndView;
    }

    @RequestMapping(value = "student/delete/{rollNo}", method = RequestMethod.POST, consumes = MediaType.ALL_VALUE)
    @ResponseStatus(value = HttpStatus.OK)
    ModelAndView deleteStudent(@PathVariable Integer rollNo) throws Exception {

        ModelAndView modelAndView = new ModelAndView("student");
        try {
            Student student = new Student();
            student.setRollNo(rollNo);

            studentService.deleteStudent(student);
            modelAndView.addObject("message", "Student deleted with rollNo: " + student.getRollNo());
        }
        catch (Exception ex){
            modelAndView.addObject("message", "Failed to delete student: " + ex.getMessage());
        }
        modelAndView.addObject("students", studentService.getStudents());
        return modelAndView;
    }
}
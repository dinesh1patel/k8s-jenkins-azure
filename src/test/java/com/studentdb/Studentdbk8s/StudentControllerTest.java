package com.studentdb.Studentdbk8s;

import com.studentdb.Studentdbk8s.domain.Student;
import com.studentdb.Studentdbk8s.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;


@ExtendWith(SpringExtension.class)
@WebMvcTest
public class StudentControllerTest {
    //subject under test StudentController

    //dependency to mock StudentService, i.e. controller depends on service

    @Autowired
    MockMvc mockMvc;

    @MockBean
    private StudentService studentService;

    @Test
    void getAllStudents() throws Exception {
        List<Student> studentList = new ArrayList<Student>();

        Student student1 = new Student();
        student1.setName("student1");
        student1.setRollNo(1);
        student1.setDateOfBirth(new Date());
        studentList.add(student1);

        Student student2 = new Student();
        student2.setName("student1");
        student2.setRollNo(1);
        student2.setDateOfBirth(new Date());
        studentList.add(student2);
        when(studentService.getStudents()).thenReturn(studentList);

        mockMvc.perform(MockMvcRequestBuilders.get("/student")
                .contentType(MediaType.APPLICATION_JSON)
        ).andExpect(jsonPath("$", hasSize(2))).andDo(print());
    }

}

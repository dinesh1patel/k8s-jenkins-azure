package com.studentdb.Studentdbk8s;

import com.studentdb.Studentdbk8s.domain.Student;
import com.studentdb.Studentdbk8s.repository.StudentRepository;
import com.studentdb.Studentdbk8s.service.StudentService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
public class StudentServiceTest {

    //subject under test StudentService

    //dependency to mock StudentRepository, i.e. StudentService depends on repository

    @MockBean(name="studentRepository")
    private StudentRepository studentRepository;

    @InjectMocks
    private StudentService studentService;

    @Test
    void testGetAllUserById(){

        // arrange
        Student student = new Student();
        student.setName("student1");
        student.setRollNo(1);
        student.setDateOfBirth(new Date());

        Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(student));

        // act
        Optional<Student> result = studentService.getStudentsById(1);

        // assert
        assertEquals(result.get().getDateOfBirth(), student.getDateOfBirth());
        assertEquals(result.get().getRollNo(), student.getRollNo());
        assertEquals(result.get().getName(), student.getName());
    }

    @Test
    public void testGetAllUsers() {
        // arrange
        List<Student> users = Arrays.asList(
                new Student(1, "test1", new Date()),
                new Student(2, "test2", new Date())
        );
        Mockito.when(studentRepository.findAll()).thenReturn(users);

        // act
        List<Student> result = studentService.getStudentsList();

        // assert
        assertEquals(result.get(0).getName(), users.get(0).getName());
    }
}

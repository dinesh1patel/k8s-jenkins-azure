package com.studentdb.Studentdbk8s.repository;

import com.studentdb.Studentdbk8s.domain.Student;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
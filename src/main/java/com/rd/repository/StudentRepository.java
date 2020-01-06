package com.rd.repository;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.rd.model.Student;

public interface StudentRepository extends CrudRepository<Student, Long> ,JpaSpecificationExecutor<Student> {


}

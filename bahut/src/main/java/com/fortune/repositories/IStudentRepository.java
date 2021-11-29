package com.fortune.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fortune.models.Student;


@Repository
public interface IStudentRepository extends JpaRepository<Student, String> {
	
     List<Student> findByActiveTrueOrderByFirstName();

}

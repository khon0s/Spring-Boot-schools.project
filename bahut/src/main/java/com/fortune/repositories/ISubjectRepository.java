package com.fortune.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fortune.models.Subject;

@Repository
public interface ISubjectRepository extends JpaRepository<Subject, String> {

	List<Subject> findByActiveTrueOrderByName();

}

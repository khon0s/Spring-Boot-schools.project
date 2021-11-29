package com.fortune.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fortune.models.Professor;

@Repository
public interface IProfessorRepository extends JpaRepository<Professor, String> {

	List<Professor> findByActiveTrueOrderByFirstName();
}

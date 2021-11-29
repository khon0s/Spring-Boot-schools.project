package com.fortune.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fortune.domains.EnquiryDom;
import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;
import com.fortune.services.IProfessorService;
import com.fortune.validator.IProfessorValidator;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/professor")
public class ProfessorController {

	@Autowired
	IProfessorService professorServiceImpl;

	@Autowired
	IProfessorValidator professorValidatorImpl;

	@PostMapping(value = "/new")
	public ResponseEntity<?> registerProfessor(@RequestBody ProfessorDom professorDom) {

		if (professorDom == null) {
			return new ResponseEntity<>("Registro no es válido", HttpStatus.OK);
		};
		
		String responseValidation = professorValidatorImpl.professorValidationNew(professorDom);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		};

		ProfessorPresenter professorPresenter = professorServiceImpl.register(professorDom);

		return new ResponseEntity<>(professorPresenter, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> registerProfessor(@PathVariable(name = "id") String id) {

		if (id.isBlank() || id == null) {

			return new ResponseEntity<>("Invalid id", HttpStatus.OK);
		}

		ProfessorPresenter professorPresenter = professorServiceImpl.getProfessorById(id);

		if (professorPresenter == null) {

			return new ResponseEntity<>("Not available", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(professorPresenter, HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {

		List<ProfessorPresenter> setProfessor = professorServiceImpl.getAllProfessors();

		if (setProfessor == null) {
			return new ResponseEntity<>("Invalid request", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(setProfessor, HttpStatus.OK);
	}

	// CRITERIA QUERY
	@PostMapping(value = "/some")
	public ResponseEntity<?> getSome(@RequestBody(required = false) EnquiryDom enquiryDom) {

		Optional<EnquiryDom> enquiryOpt = Optional.ofNullable(enquiryDom);

		if (!enquiryOpt.isPresent()) {
			return new ResponseEntity<>("missing body", HttpStatus.OK);
		}
		;

		EnquiryDom enquiryDomAfer;

		try {
			enquiryDomAfer = enquiryOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return new ResponseEntity<>("missing body opt", HttpStatus.OK);
		}

		if (enquiryDom.getDateEnd() == null && enquiryDom.getDateInit() == null && enquiryDom.getFirstName() == null
				&& enquiryDom.getLastName() == null) {
			return new ResponseEntity<>("missing data", HttpStatus.OK);
		}
		;

		Set<ProfessorPresenter> setProfessors = new HashSet<ProfessorPresenter>();

		setProfessors = professorServiceImpl.setFourInput(enquiryDomAfer);

		if (setProfessors == null) {
			return new ResponseEntity<>("Not found in DDBB", HttpStatus.OK);
		}

		return new ResponseEntity<>(setProfessors, HttpStatus.OK);
	}

	@DeleteMapping(value = "/fall/{id}")
	public ResponseEntity<?> deleteMyStudent(@PathVariable(name = "id") String id) {

		if (id == null || id.isBlank()) {
			return new ResponseEntity<>("Invalid input", HttpStatus.OK);
		}
		;

		String res = professorServiceImpl.deleteProfessors(id);

		if (res == null) {
			return new ResponseEntity<>("Identity not found", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") String id,
			@RequestBody ProfessorPresenter professorPresenter) {

		if (id.isBlank() || id == null) {
			return new ResponseEntity<>("Wrong id", HttpStatus.OK);
		};

		String responseValidation = professorValidatorImpl.professorValidationPresenter(professorPresenter);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		}
		;

		String res = professorServiceImpl.updateProfessor(id, professorPresenter);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}

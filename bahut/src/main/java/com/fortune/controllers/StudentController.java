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
import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;
import com.fortune.exceptions.StudentServiceException;
import com.fortune.services.IStudentService;
import com.fortune.validator.IStudentValidator;

@CrossOrigin("*")
@RestController
@RequestMapping(value = "/v1/student")
public class StudentController {

	@Autowired
	private IStudentService studentServiceImpl;

	@Autowired
	private IStudentValidator studentValidatorImpl;

	@PostMapping(value = "/new")
	public ResponseEntity<?> registerStudent(@RequestBody StudentDom studentDom) throws StudentServiceException {

		String responseValidation = studentValidatorImpl.studentValidationNew(studentDom);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		};

		StudentPresenter studentPresenter = studentServiceImpl.register(studentDom);

		return new ResponseEntity<>(studentPresenter, HttpStatus.OK);
	}

	@GetMapping(value = "/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable(name = "id") String id) {

		if (id.isBlank() || id == null) {
			return new ResponseEntity<>("Id incorrecto", HttpStatus.OK);
		}
		;

		StudentPresenter studentPresenter = studentServiceImpl.getStudentById(id);

		if (studentPresenter == null) {
			return new ResponseEntity<>("Invalid request", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(studentPresenter, HttpStatus.OK);
	}

	@GetMapping(value = "/all")
	public ResponseEntity<?> getAll() {

		List<StudentPresenter> setStudents = studentServiceImpl.getAllStudents();

		if (setStudents == null) {
			return new ResponseEntity<>("Invalid request", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(setStudents, HttpStatus.OK);
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

		Set<StudentPresenter> setStudents = new HashSet<StudentPresenter>();

		setStudents = studentServiceImpl.setFourInput(enquiryDomAfer);

		if (setStudents == null) {
			return new ResponseEntity<>("Not found in DDBB", HttpStatus.OK);
		}

		return new ResponseEntity<>(setStudents, HttpStatus.OK);
	}

	@DeleteMapping(value = "/fall/{id}")
	public ResponseEntity<?> deleteMyStudent(@PathVariable(name = "id") String id) {

		if (id == null || id.isBlank()) {
			return new ResponseEntity<>("Invalid input", HttpStatus.OK);
		}
		;

		String res = studentServiceImpl.deleteStu(id);

		if (res == null) {
			return new ResponseEntity<>("Identity not found", HttpStatus.OK);
		}
		;

		return new ResponseEntity<>(res, HttpStatus.OK);

	}

	@PutMapping(value = "/update/{id}")
	public ResponseEntity<?> updateUser(@PathVariable(name = "id") String id,
			@RequestBody StudentPresenter studentPresenter) {

		if (id.isBlank() || id == null) {
			return new ResponseEntity<>("Wrong id", HttpStatus.OK);
		};
		
		String responseValidation = studentValidatorImpl.studentValidationPresenter(studentPresenter);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		};

		String res = studentServiceImpl.updateStudent(id, studentPresenter);

		return new ResponseEntity<>(res, HttpStatus.OK);
	}

}

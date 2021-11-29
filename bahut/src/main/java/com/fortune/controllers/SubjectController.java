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

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;
import com.fortune.services.ISubjectService;
import com.fortune.validator.ISubjectValidator;


@CrossOrigin("*")
@RestController
@RequestMapping( value = "/v1/subject" )
public class SubjectController {
	
	@Autowired
	ISubjectService subjectServiceImpl;
	
	@Autowired
	ISubjectValidator subjectValidatorImpl;
	
	@PostMapping(value = "/new")
	public ResponseEntity<?> registerSubject(@RequestBody SubjectDom subjectDom) {
		
		if( subjectDom.getName() == null ) {
			return new ResponseEntity<>("Nombre no válido", HttpStatus.OK);
		};
		
		String responseValidation = subjectValidatorImpl.subjectValidationNew(subjectDom);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		};
	
		SubjectPresenter subjectPresenter = subjectServiceImpl.register(subjectDom);
		
		if( subjectPresenter == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(subjectPresenter , HttpStatus.OK );
		
	}
	
	@GetMapping( value = "/{id}" )
	public ResponseEntity<?> getSubjectById(@PathVariable(name = "id") String id ) {
		
		if( id.isBlank() || id == null ) {
			return new ResponseEntity<>("Invalid id" , HttpStatus.OK );
		}
		
		SubjectPresenter subjectPresenter = subjectServiceImpl.getSubjectById(id);
		
		if( subjectPresenter == null ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		}
		
		return new ResponseEntity<>(subjectPresenter , HttpStatus.OK );
	}

	@PutMapping("/enroll/{subjectId}/{studentId}")
	public ResponseEntity<?> asignaEstudianteAClase( 
			@PathVariable(name = "subjectId") String subjectId,
			@PathVariable(name = "studentId") String studentId
			){
		
		SubjectPresenter subjectPresenter = subjectServiceImpl.enrollStudentToClass(subjectId, studentId);
		
		if( subjectPresenter == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(subjectPresenter , HttpStatus.OK );
	}
	
	@PutMapping("/enroll/fall/{subjectId}/{studentId}")
	public ResponseEntity<?> desAsignarEstudianteAClase( 
			@PathVariable(name = "subjectId") String subjectId,
			@PathVariable(name = "studentId") String studentId
			){
		
		String res = subjectServiceImpl.desenrollStudentToClass(subjectId, studentId);
		
		if( res == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(res, HttpStatus.OK );
	}
	
	@PutMapping("/assign/{subjectId}/{proId}")
	public ResponseEntity<?> asignaProfesorAClase( 
			@PathVariable(name = "subjectId") String subjectId,
			@PathVariable(name = "proId") String proId
			){
		
		SubjectPresenter subjectPresenter = subjectServiceImpl.assignProfesorToClass(subjectId, proId);
		
		if( subjectPresenter == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(subjectPresenter , HttpStatus.OK );
	}
	
	@PutMapping("/assign/fall/{subjectId}/{proId}")
	public ResponseEntity<?> desasignarProfesorAClase( 
			@PathVariable(name = "subjectId") String subjectId,
			@PathVariable(name = "proId") String proId
			){
		
		String res = subjectServiceImpl.unassignProfesorToClass(subjectId, proId);
		
		if( res == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(res , HttpStatus.OK );
	}
	
	@PutMapping("/assign/coordinator/{subjectId}/{proId}")
	public ResponseEntity<?> asignarCoordinadorAClase( 
			@PathVariable(name = "subjectId") String subjectId,
			@PathVariable(name = "proId") String proId
			){
		
		String res = subjectServiceImpl.assignCoordinatorToClass(subjectId, proId);
		
		if( res == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(res , HttpStatus.OK );
	}
	
	@PutMapping("/unassign/coordinator/fall/{subjectId}")
	public ResponseEntity<?> desasignarCoordinadorAClase( 
			@PathVariable(name = "subjectId") String subjectId
			){
		
		String res = subjectServiceImpl.replaceCoordinator(subjectId);
		
		if( res == null  ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		};
		
		return new ResponseEntity<>(res , HttpStatus.OK );
	}
	
	
	@DeleteMapping( value = "/fall/{id}" )
	public ResponseEntity<?> deleteOneSubject(@PathVariable(name = "id") String id ) {
		
		if( id.isBlank() || id == null ) {
			return new ResponseEntity<>("Invalid id" , HttpStatus.OK );
		}
		
		String res = subjectServiceImpl.deleteSubWithId(id);
		
		if( res == null ) {
			return new ResponseEntity<>("Invalid request" , HttpStatus.OK );
		}
		
		return new ResponseEntity<>(res , HttpStatus.OK );
	}
	
	
	@GetMapping( value = "/all" )
	public ResponseEntity<?> getAll(){
		
		List<SubjectPresenter> setSubjects = subjectServiceImpl.getAllSubjects();

		if ( setSubjects == null) {
			return new ResponseEntity<>("Invalid request", HttpStatus.OK);
		};

		return new ResponseEntity<>(setSubjects, HttpStatus.OK);
	}
	 
	@PutMapping( value = "/update/{id}" )
	public ResponseEntity<?> updateSubject(@PathVariable(name = "id") String id,
			@RequestBody SubjectPresenter subjectPresenter) {
		
		if( id.isBlank() || id == null  ) {
			return new ResponseEntity<>("Wrong id", HttpStatus.OK);
		};
		
		String responseValidation = subjectValidatorImpl.subjectValidationPresenter(subjectPresenter);

		if (responseValidation != "hakunamatata") {
			return new ResponseEntity<>(responseValidation, HttpStatus.OK);
		};
          
	    String res =  subjectServiceImpl.updateSubjects(id, subjectPresenter );

		return new ResponseEntity<>(res, HttpStatus.OK);
	}
	
	// CRITERIA QUERY
		@PostMapping(value = "/some")
		public ResponseEntity<?> getSome(@RequestBody(required=false) SubjectDom enquiryDom) {
			
			Optional<SubjectDom> subOpt = Optional.ofNullable(enquiryDom) ;
			
			if( !subOpt.isPresent()  ) {
				return new ResponseEntity<>("missing body", HttpStatus.OK);
			};
			
			SubjectDom subDomAfter ;
			
			try {
				subDomAfter = subOpt.orElseThrow();
			}catch(NoSuchElementException nsee) {
				return new ResponseEntity<>("missing body opt", HttpStatus.OK);
			}
			
			if( enquiryDom.getName() == null && 
					enquiryDom.getTypeDep() == null 
			) {
				return new ResponseEntity<>("missing data for null", HttpStatus.OK);
			};
			
			Set<SubjectPresenter> setSubjects = new HashSet<SubjectPresenter>();

			setSubjects = subjectServiceImpl.setTwoInput(subDomAfter);
			
			if( setSubjects == null ) {
				return new ResponseEntity<>("Not found in DDBB", HttpStatus.OK);
			}

			return new ResponseEntity<>(setSubjects, HttpStatus.OK);
		}
	
	
}

package com.fortune.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortune.domains.EnquiryDom;
import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;
import com.fortune.exceptions.StudentServiceException;
import com.fortune.models.Student;
import com.fortune.repositories.IStudentRepository;
import com.fortune.repositories.ISubjectRepository;
import com.fortune.services.IStudentService;
import com.fortune.utils.IStudentConverter;

@Service(value = "studentServiceImpl")
public class StudentServiceImpl implements IStudentService {

	@Autowired
	private IStudentConverter studentConverterImpl;
	@Autowired
	private IStudentRepository studentRepository;
	@PersistenceContext
	private EntityManager entityManager;
	@Autowired 
	private ISubjectRepository subjectRepository;

	@Override
	public StudentPresenter register(StudentDom studentDom) throws StudentServiceException {

		if (studentDom == null)
			throw new StudentServiceException("No se indica estudiante a crear.");

		Student studentModel = studentConverterImpl.convert(studentDom);

		studentModel = studentRepository.save(studentModel);

		StudentPresenter studentPresenter = studentConverterImpl.convert(studentModel);

		return studentPresenter;
	}

	@Override
	public StudentPresenter getStudentById(String id) {

		Optional<Student> studentOpt = studentRepository.findById(id);

		Student studentModel;

		try {
			studentModel = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		StudentPresenter studentPresenter = studentConverterImpl.convert(studentModel);

		return studentPresenter;
	}

	@Override
	public List<StudentPresenter> getAllStudents() {

		Optional<List<Student>> listStuOpt = Optional.ofNullable(studentRepository.findByActiveTrueOrderByFirstName());

		List<Student> listStudents;

		try {
			listStudents = listStuOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		List<StudentPresenter> listStuPresenter = listStudents.stream().map(e -> studentConverterImpl.convertSimple(e))
				.collect(Collectors.toList());

		return listStuPresenter;
	}

	

	@Override
	public String updateStudent(String id, StudentPresenter studentPre) {

		Optional<Student> stuOpt = studentRepository.findById(id);

		Student studentModel;

		try {
			studentModel = stuOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		if (studentPre.getFirstName() != null) {
			studentModel.setFirstName(studentPre.getFirstName());
		}
		;

		if (studentPre.getLastName() != null) {
			studentModel.setLastName(studentPre.getLastName());
		}
		;

		if (studentPre.getDni() != null) {
			studentModel.setDni(studentPre.getDni());
		}
		;

		if (studentPre.getBirthDate() != null) {
			studentModel.setBirthDate(studentPre.getBirthDate());
		}
		;

		studentModel.setAvrScore(studentPre.getAvrScore());

		studentModel = studentRepository.save(studentModel);

		return "updated";
	}

	@Override
	public Set<StudentPresenter> setFourInput(EnquiryDom enuiryDom) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Student> cq = cb.createQuery(Student.class);

		Root<Student> student = cq.from(Student.class);
	
		List<Predicate> predicates = new ArrayList<Predicate>();
		
		
		if( enuiryDom.getFirstName() != null) {
			predicates.add( cb.equal(student.get("firstName"), enuiryDom.getFirstName()) );
		};
		
		if( enuiryDom.getLastName() != null) {
		predicates.add( cb.like(student.<String>get("lastName"), "%"+ enuiryDom.getLastName()+"%"));
		}
		
		if( enuiryDom.getDateInit() != null) {
		predicates.add( cb.greaterThan(student.get("birthDate"), enuiryDom.getDateInit() ));
		}

		if( enuiryDom.getDateEnd() != null) {
			predicates.add( cb.lessThan(student.get("birthDate"), enuiryDom.getDateEnd() ));
		}
		
        
		cq.select( student )
		.where( predicates.toArray(new Predicate[]{}));
		
		TypedQuery<Student> query = entityManager.createQuery(cq);

	    Optional<List<Student>> setOpt = Optional.ofNullable(query.getResultList());
	    
	    List<Student> setStudentModel ;
	    
	    try {
	    	setStudentModel = setOpt.orElseThrow();
	    }catch(NoSuchElementException nsee){
	    	return null ;
	    }

		Set<StudentPresenter> setStudentPresenter = setStudentModel.stream()
				.map( e -> studentConverterImpl.convertSimple(e) )
				.collect(Collectors.toSet());
		 
		return setStudentPresenter ;
	}

	@Override
	@Transactional
	public String deleteStu(String id) {
		
		if (id == null || id.isBlank()) {
			return null;
		};
		
		    Student student = studentRepository.findById(id).orElseThrow();
		    
		    student.getSubjectList().forEach(u -> u.getStudentListSubject().remove(student));
		    
		    subjectRepository.saveAll(student.getSubjectList());
		    
		    studentRepository.delete(student);
		    
			return "deleted";
	}

}

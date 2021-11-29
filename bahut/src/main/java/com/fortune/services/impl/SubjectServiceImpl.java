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

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;
import com.fortune.models.Professor;
import com.fortune.models.Student;
import com.fortune.models.Subject;
import com.fortune.repositories.IProfessorRepository;
import com.fortune.repositories.IStudentRepository;
import com.fortune.repositories.ISubjectRepository;
import com.fortune.services.ISubjectService;
import com.fortune.utils.IStudentConverter;
import com.fortune.utils.ISubjectConverter;

@Service(value = "subjectServiceImpl")
public class SubjectServiceImpl implements ISubjectService {

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	public ISubjectRepository subjectRepository;

	@Autowired
	private IStudentRepository studentRepository;

	@Autowired
	IProfessorRepository professorRepository;

	@Autowired
	public ISubjectConverter subjectConverterImpl;

	@Autowired
	private IStudentConverter studentConverterImpl;

	@Override
	public SubjectPresenter register(SubjectDom subjectDom) {

		Subject subject = subjectConverterImpl.convert(subjectDom);

		subject = subjectRepository.save(subject);

		SubjectPresenter subjectPresenter = subjectConverterImpl.convert(subject);

		return subjectPresenter;
	}

	@Override
	public SubjectPresenter getSubjectById(String id) {

		Optional<Subject> subjectOpt = subjectRepository.findById(id);

		Subject subject;

		try {
			subject = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		SubjectPresenter subjectPresenter = subjectConverterImpl.convert(subject);

		return subjectPresenter;
	}

	@Override
	public SubjectPresenter enrollStudentToClass(String subjectId, String studentId) {

		Optional<Subject> subOpt = Optional.ofNullable(subjectRepository.findById(subjectId).get());

		Subject subjectModel;

		try {
			subjectModel = subOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Optional<Student> studentOpt = Optional.ofNullable(studentRepository.findById(studentId).get());

		Student studentModel;

		try {
			studentModel = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}
		;

		subjectModel.addStudent(studentModel);

		subjectModel = subjectRepository.save(subjectModel);

		SubjectPresenter subjectPresenter = subjectConverterImpl.convert(subjectModel);

		return subjectPresenter;
	}

	@Override
	public SubjectPresenter assignProfesorToClass(String subId, String proId) {

		Optional<Subject> subOpt = Optional.ofNullable(subjectRepository.findById(subId).get());

		Subject subjectModel;

		try {
			subjectModel = subOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Optional<Professor> professorOpt = Optional.ofNullable(professorRepository.findById(proId).get());

		Professor professorModel;

		try {
			professorModel = professorOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}
		;

		subjectModel.addTeacher(professorModel);

		subjectModel = subjectRepository.save(subjectModel);

		SubjectPresenter subjectPresenterWithPro = subjectConverterImpl.convert(subjectModel);

		return subjectPresenterWithPro;
	}

	@Override
	public String deleteSubWithId(String id) {

		Optional<Subject> subjectOpt = subjectRepository.findById(id);

		Subject subject;

		try {
			subject = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		subjectRepository.deleteById(subject.getId());

		return "deleted";
	}

	@Override
	public List<SubjectPresenter> getAllSubjects() {

		Optional<List<Subject>> listSubOpt = Optional.ofNullable(subjectRepository.findByActiveTrueOrderByName());

		List<Subject> listSubject;

		try {
			listSubject = listSubOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		List<SubjectPresenter> listSubPresenter = listSubject.stream().map(e -> subjectConverterImpl.convertSimple(e))
				.collect(Collectors.toList());

		return listSubPresenter;
	}

	@Override
	public String updateSubjects(String id, SubjectPresenter subjectPresenter) {

		Optional<Subject> subOpt = subjectRepository.findById(id);

		Subject subjectModel;

		try {
			subjectModel = subOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		if (subjectPresenter.getName() != null) {
			subjectModel.setName(subjectPresenter.getName());
		}
		;

		if (subjectPresenter.getTypeDep() != null) {
			subjectModel.setTypeDep(subjectPresenter.getTypeDep());
		}
		;

		if (subjectPresenter.getStudentList() != null) {

			Set<Student> setStu = subjectPresenter.getStudentList().stream()
					.map(e -> studentConverterImpl.convertSimple(e)).collect(Collectors.toSet());

			subjectModel.setStudentListSubject(setStu);
		}
		;

		subjectModel = subjectRepository.save(subjectModel);

		return "updated";
	}

	@Override
	public Set<SubjectPresenter> setTwoInput(SubjectDom subDomAfter) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Subject> cq = cb.createQuery(Subject.class);

		Root<Subject> subject = cq.from(Subject.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (subDomAfter.getName() != null) {
			predicates.add(cb.like(subject.<String>get("name"), "%" + subDomAfter.getName() + "%"));
		}
		;

		if (subDomAfter.getTypeDep() != null) {
			predicates.add(cb.equal(subject.get("typeDep"), subDomAfter.getTypeDep()));
		}

		cq.select(subject).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Subject> query = entityManager.createQuery(cq);

		Optional<List<Subject>> setOpt = Optional.ofNullable(query.getResultList());

		List<Subject> setSubjectModel;

		try {
			setSubjectModel = setOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Set<SubjectPresenter> setSubjectPresenter = setSubjectModel.stream()
				.map(e -> subjectConverterImpl.convertSimple(e)).collect(Collectors.toSet());

		return setSubjectPresenter;
	}

	@Override
	public String desenrollStudentToClass(String subjectId, String studentId) {

		Optional<Student> studentOpt = studentRepository.findById(studentId);
		Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);

		Student studentModel;
		Subject subjectModel;

		try {
			studentModel = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		try {
			subjectModel = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Set<Student> setStuModel = subjectModel.getStudentListSubject();

		setStuModel.remove(studentModel);

		subjectModel.setStudentListSubject(setStuModel);

		subjectModel = subjectRepository.save(subjectModel);

		return "removed";
	}

	@Override
	public String unassignProfesorToClass(String subjectId, String proId) {

		Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
		Optional<Professor> studentOpt = professorRepository.findById(proId);

		Subject subjectModel;
		Professor professorModel;

		try {
			professorModel = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		try {
			subjectModel = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Set<Professor> setProModel = subjectModel.getProfessorListSubject();

		setProModel.remove(professorModel);

		subjectModel.setProfessorListSubject(setProModel);
		;

		subjectModel = subjectRepository.save(subjectModel);

		return "removed";
	}

	@Override
	public String assignCoordinatorToClass(String subjectId, String proId) {

		Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
		Optional<Professor> studentOpt = professorRepository.findById(proId);

		Subject subjectModel;
		Professor professorModel;

		try {
			professorModel = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		try {
			subjectModel = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		subjectModel.setCoordinator(professorModel);

		subjectRepository.save(subjectModel);

		return "set as coordinator";
	}

	@Override
	public String replaceCoordinator(String subjectId) {
		
		Optional<Subject> subjectOpt = subjectRepository.findById(subjectId);
		
		Subject subjectModel;

		try {
			subjectModel = subjectOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}
		
		Optional<Professor> studentOpt = professorRepository.findById(
				subjectModel.getCoordinator().getId()		
				);
		Professor professor;
		
		try {
			professor = studentOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}
		
		professor.getHeadOfList().forEach(u -> u.setCoordinator(null));
		
		subjectRepository.saveAll(professor.getHeadOfList());
		
		return "unset as coordinator";
	}



}

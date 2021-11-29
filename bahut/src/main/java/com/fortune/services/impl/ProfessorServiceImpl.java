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

import com.fortune.domains.EnquiryDom;
import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;
import com.fortune.models.Professor;
import com.fortune.repositories.IProfessorRepository;
import com.fortune.repositories.ISubjectRepository;
import com.fortune.services.IProfessorService;
import com.fortune.utils.IProfessorConverter;

@Service(value = "professorServiceImpl")
public class ProfessorServiceImpl implements IProfessorService {

	@Autowired
	IProfessorRepository professorRepositoryImpl;

	@Autowired
	IProfessorConverter professorConverterImpl;

	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ISubjectRepository subjectRepository;

	@Override
	public ProfessorPresenter register(ProfessorDom ProfessorDom) {

		Professor professorModel = professorConverterImpl.convert(ProfessorDom);

		professorModel = professorRepositoryImpl.save(professorModel);

		ProfessorPresenter professorPresenter = professorConverterImpl.convert(professorModel);

		return professorPresenter;
	}

	@Override
	public ProfessorPresenter getProfessorById(String id) {

		Optional<Professor> professorOpt = professorRepositoryImpl.findById(id);

		Professor professorModel;

		try {
			professorModel = professorOpt.orElseThrow();

		} catch (NoSuchElementException nsee) {
			return null;
		}

		ProfessorPresenter professorPresenter = professorConverterImpl.convert(professorModel);

		return professorPresenter;
	}

	@Override
	public List<ProfessorPresenter> getAllProfessors() {

		Optional<List<Professor>> listProOpt = Optional
				.ofNullable(professorRepositoryImpl.findByActiveTrueOrderByFirstName());

		List<Professor> listProfessor;

		try {
			listProfessor = listProOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		List<ProfessorPresenter> listProPresenter = listProfessor.stream()
				.map(e -> professorConverterImpl.convertSimple(e)).collect(Collectors.toList());

		return listProPresenter;

	}

	@Override
	public Set<ProfessorPresenter> setFourInput(EnquiryDom enuiryDom) {

		CriteriaBuilder cb = entityManager.getCriteriaBuilder();

		CriteriaQuery<Professor> cq = cb.createQuery(Professor.class);

		Root<Professor> professor = cq.from(Professor.class);

		List<Predicate> predicates = new ArrayList<Predicate>();

		if (enuiryDom.getFirstName() != null) {
			predicates.add(cb.equal(professor.get("firstName"), enuiryDom.getFirstName()));
		}
		;

		if (enuiryDom.getLastName() != null) {
			predicates.add(cb.like(professor.<String>get("lastName"), "%" + enuiryDom.getLastName() + "%"));
		}

		if (enuiryDom.getDateInit() != null) {
			predicates.add(cb.greaterThan(professor.get("birthDate"), enuiryDom.getDateInit()));
		}

		if (enuiryDom.getDateEnd() != null) {
			predicates.add(cb.lessThan(professor.get("birthDate"), enuiryDom.getDateEnd()));
		}

		cq.select(professor).where(predicates.toArray(new Predicate[] {}));

		TypedQuery<Professor> query = entityManager.createQuery(cq);

		Optional<List<Professor>> setOpt = Optional.ofNullable(query.getResultList());

		List<Professor> setProfessorModel;

		try {
			setProfessorModel = setOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		Set<ProfessorPresenter> setProfessorPresenter = setProfessorModel.stream()
				.map(e -> professorConverterImpl.convertSimple(e)).collect(Collectors.toSet());

		return setProfessorPresenter;

	}

	@Override
	public String deleteProfessors(String id) {

		if (id == null || id.isBlank()) {
			return null;
		}
		;

		Professor professor = professorRepositoryImpl.findById(id).orElseThrow();

		professor.getSubjectList().forEach(u -> u.getProfessorListSubject().remove(professor));

		professor.getHeadOfList().forEach(e -> e.setCoordinator(null) );

		subjectRepository.saveAll(professor.getSubjectList());
		subjectRepository.saveAll(professor.getHeadOfList() );
 
		professorRepositoryImpl.delete(professor);

		return "deleted";
	}

	@Override
	public String updateProfessor(String id, ProfessorPresenter professorPresenter) {

		Optional<Professor> professorOpt = professorRepositoryImpl.findById(id);

		Professor professorModel;

		try {
			professorModel = professorOpt.orElseThrow();
		} catch (NoSuchElementException nsee) {
			return null;
		}

		if (professorPresenter.getFirstName() != null) {
			professorModel.setFirstName(professorPresenter.getFirstName());
		}
		;

		if (professorPresenter.getLastName() != null) {
			professorModel.setLastName(professorPresenter.getLastName());
		}
		;

		if (professorPresenter.getDni() != null) {
			professorModel.setDni(professorPresenter.getDni());
		}
		;

		if (professorPresenter.getBirthDate() != null) {
			professorModel.setBirthDate(professorPresenter.getBirthDate());
		}
		;
		if (professorPresenter.isHeadOfDepartment()) {
			professorModel.setHeadOfDepartment(professorPresenter.isHeadOfDepartment());
		}
		;

		professorModel = professorRepositoryImpl.save(professorModel);

		return "updated";
	}

}

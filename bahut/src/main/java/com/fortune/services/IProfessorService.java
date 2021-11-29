package com.fortune.services;

import java.util.List;
import java.util.Set;

import com.fortune.domains.EnquiryDom;
import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;

public interface IProfessorService {

	public ProfessorPresenter register(ProfessorDom ProfessorDom);
	
	public ProfessorPresenter getProfessorById(String id);
	
	public List<ProfessorPresenter> getAllProfessors();
	
	public Set<ProfessorPresenter> setFourInput( EnquiryDom enuiryDom );
	
	public String deleteProfessors(String id);
	
	public String updateProfessor(String id, ProfessorPresenter professorPresenter);
}

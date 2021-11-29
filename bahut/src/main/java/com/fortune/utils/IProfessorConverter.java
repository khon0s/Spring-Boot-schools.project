package com.fortune.utils;

import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;
import com.fortune.models.Professor;

public interface IProfessorConverter {

	public Professor convert(ProfessorDom professorDom);
	
	public ProfessorPresenter convert(Professor professorModel);
	
	public ProfessorPresenter convertSimple(Professor professorModel);
}

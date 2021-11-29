package com.fortune.validator;

import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;

public interface IProfessorValidator {

	public String professorValidationNew(ProfessorDom proDom);

	public String professorValidationPresenter(ProfessorPresenter professorPresenter);
}

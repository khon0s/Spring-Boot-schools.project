package com.fortune.validator;

import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;

public interface IStudentValidator {

	public String studentValidationNew(StudentDom studentDom);
	
	public String studentValidationPresenter(StudentPresenter studentPresenter);
}

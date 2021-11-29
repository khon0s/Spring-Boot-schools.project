package com.fortune.validator;

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;

public interface ISubjectValidator {

	public String subjectValidationNew(SubjectDom subDom);
	
	public String subjectValidationPresenter(SubjectPresenter subPresenter);
}

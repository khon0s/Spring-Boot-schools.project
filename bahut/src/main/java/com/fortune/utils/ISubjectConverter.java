package com.fortune.utils;

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;
import com.fortune.models.Subject;

public interface ISubjectConverter {

	public Subject convert(SubjectDom subjectDom);
	
	public SubjectPresenter convert(Subject subject);
	
	public SubjectPresenter convertSimple(Subject subject);
	
}

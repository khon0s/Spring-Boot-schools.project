package com.fortune.utils;

import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;
import com.fortune.models.Student;

public interface IStudentConverter {

	public Student convert( StudentDom studentDom );
	
	public StudentPresenter convert( Student student );
	
	public StudentPresenter convertSimple( Student student );
	
	public Student convertSimple( StudentPresenter studentPresenter );

}

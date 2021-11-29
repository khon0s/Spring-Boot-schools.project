package com.fortune.services;

import java.util.List;
import java.util.Set;

import com.fortune.domains.EnquiryDom;
import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;
import com.fortune.exceptions.StudentServiceException;

public interface IStudentService {

	public StudentPresenter register(StudentDom studentDom) throws StudentServiceException;
	
	public StudentPresenter getStudentById(String id);
	
	public List<StudentPresenter> getAllStudents();
	
	public String deleteStu(String id);
	
	public String updateStudent(String id, StudentPresenter studentPre);
	
	public Set<StudentPresenter> setFourInput( EnquiryDom enuiryDom );
}

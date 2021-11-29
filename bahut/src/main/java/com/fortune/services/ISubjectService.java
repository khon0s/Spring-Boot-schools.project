package com.fortune.services;

import java.util.List;
import java.util.Set;

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;

public interface ISubjectService {

	public SubjectPresenter register(SubjectDom subjectDom);
	
	public SubjectPresenter getSubjectById(String id);
	
	public SubjectPresenter enrollStudentToClass( String subId, String stuId );
	
	public SubjectPresenter assignProfesorToClass( String subId, String proId );

	public String deleteSubWithId(String id);

	public List<SubjectPresenter> getAllSubjects();

	public String updateSubjects(String id, SubjectPresenter subjectPresenter);

	public Set<SubjectPresenter> setTwoInput(SubjectDom subDomAfter);

	public String desenrollStudentToClass(String subjectId, String studentId);

	public String unassignProfesorToClass(String subjectId, String proId);

	public String assignCoordinatorToClass(String subjectId, String proId);

	public String replaceCoordinator(String subjectId);
}
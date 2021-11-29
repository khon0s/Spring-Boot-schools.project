package com.fortune.domains;

import java.io.Serializable;
import java.util.Set;

import com.fortune.base.PersonDom;


public class ProfessorPresenter extends PersonDom implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<SubjectPresenter> subjectList;
	private boolean headOfDepartment = false;
	private Set<SubjectPresenter> headOfList;
	
	
	public Set<SubjectPresenter> getSubjectList() {
		return subjectList;
	}
	public void setSubjectList(Set<SubjectPresenter> subjectList) {
		this.subjectList = subjectList;
	}
	public boolean isHeadOfDepartment() {
		return headOfDepartment;
	}
	public void setHeadOfDepartment(boolean headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}
	public Set<SubjectPresenter> getHeadOfList() {
		return headOfList;
	}
	public void setHeadOfList(Set<SubjectPresenter> headOfList) {
		this.headOfList = headOfList;
	}



}

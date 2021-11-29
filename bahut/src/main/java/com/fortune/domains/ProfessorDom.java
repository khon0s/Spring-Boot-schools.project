package com.fortune.domains;

import java.util.List;
import java.util.Set;

import com.fortune.base.PersonDom;


public class ProfessorDom extends PersonDom {

	private Set<SubjectDom> subjectList ;
	private boolean headOfDepartment = false;
	private List<SubjectDom> headOfList;



	public Set<SubjectDom> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(Set<SubjectDom> subjectList) {
		this.subjectList = subjectList;
	}

	public boolean isHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(boolean headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public List<SubjectDom> getHeadOfList() {
		return headOfList;
	}

	public void setHeadOfList(List<SubjectDom> headOfList) {
		this.headOfList = headOfList;
	}

}

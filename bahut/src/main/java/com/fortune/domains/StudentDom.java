package com.fortune.domains;

import java.util.Set;

import com.fortune.base.PersonDom;


public class StudentDom extends PersonDom {

	private Set<SubjectDom> subjectList;
	private float avrScore;

	
	

	public Set<SubjectDom> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(Set<SubjectDom> subjectList) {
		this.subjectList = subjectList;
	}

	public float getAvrScore() {
		return avrScore;
	}

	public void setAvrScore(float avrScore) {
		this.avrScore = avrScore;
	}

}

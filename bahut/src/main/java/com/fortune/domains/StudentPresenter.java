package com.fortune.domains;

import java.io.Serializable;
import java.util.Set;

import com.fortune.base.PersonDom;


public class StudentPresenter extends PersonDom implements Serializable {
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Set<SubjectPresenter> subjectList;
	private float avrScore;
	
	

	public Set<SubjectPresenter> getSubjectList() {
		return subjectList;
	}

	public void setSubjectList(Set<SubjectPresenter> subjectList) {
		this.subjectList = subjectList;
	}

	public float getAvrScore() {
		return avrScore;
	}

	public void setAvrScore(float avrScore) {
		this.avrScore = avrScore;
	}

}

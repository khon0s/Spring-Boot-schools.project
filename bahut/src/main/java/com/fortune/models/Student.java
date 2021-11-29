package com.fortune.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fortune.base.Person;

@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
@Entity
public class Student extends Person {

	@ManyToMany( mappedBy = "enrolledStudents",
			cascade ={CascadeType.MERGE,  CascadeType.REFRESH }
	)
	private Set<Subject> subjects = new HashSet<>();

	
	@Column
	private float avrScore;

	public Set<Subject> getSubjectList() {
		return subjects;
	}

	public void setSubjectList(Set<Subject> subjectList) {
		this.subjects = subjectList;
	}

	public float getAvrScore() {
		return avrScore;
	}

	public void setAvrScore(float avrScore) {
		this.avrScore = avrScore;
	}
	
	

}

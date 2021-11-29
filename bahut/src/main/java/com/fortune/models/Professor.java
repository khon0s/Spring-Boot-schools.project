package com.fortune.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import com.fortune.base.Person;


 
@Entity
public class Professor extends Person {

	@ManyToMany( mappedBy = "professorsLecturing",
			fetch = FetchType.LAZY,
					cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
	)
	private Set<Subject> subjects  =new HashSet<>();  
	
	@Column
	private boolean headOfDepartment = false;
	
	@OneToMany(mappedBy = "coordinator",
			cascade ={CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}
			)
	private Set<Subject> headOfList = new HashSet<>();   

	

	public Set<Subject> getSubjectList() {
		return subjects;
	}

	public void setSubjectList(Set<Subject> subjectList) {
		this.subjects = subjectList;
	}

	public boolean isHeadOfDepartment() {
		return headOfDepartment;
	}

	public void setHeadOfDepartment(boolean headOfDepartment) {
		this.headOfDepartment = headOfDepartment;
	}

	public Set<Subject> getHeadOfList() {
		return headOfList;
	}

	public void setHeadOfList(Set<Subject> headOfList) {
		this.headOfList = headOfList;
	}

}

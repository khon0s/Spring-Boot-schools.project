package com.fortune.domains;

import java.util.Set;

import com.fortune.base.Base;
import com.fortune.base.DepartmentType;


public class SubjectDom extends Base {

	private String name ;
	private DepartmentType typeDep;
	private Set<ProfessorDom> professorList;
	private Set<StudentDom> studentList;
	private ProfessorDom coordinator;

	
	public SubjectDom(){
		
	}


	/**
	 * @param name
	 * @param typeDep
	 * @param professorList
	 * @param studentList
	 * @param coordinator
	 */
	public SubjectDom(String name, DepartmentType typeDep, Set<ProfessorDom> professorList,
			Set<StudentDom> studentList, ProfessorDom coordinator) {
		super();
		this.name = name;
		this.typeDep = typeDep;
		this.professorList = professorList;
		this.studentList = studentList;
		this.coordinator = coordinator;
	}


	public String getName() {
		return name;
	}


	public void setName(String name) {
		this.name = name;
	}


	public DepartmentType getTypeDep() {
		return typeDep;
	}


	public void setTypeDep(DepartmentType typeDep) {
		this.typeDep = typeDep;
	}


	public Set<ProfessorDom> getProfessorList() {
		return professorList;
	}


	public void setProfessorList(Set<ProfessorDom> professorList) {
		this.professorList = professorList;
	}


	public Set<StudentDom> getStudentList() {
		return studentList;
	}


	public void setStudentList(Set<StudentDom> studentList) {
		this.studentList = studentList;
	}


	public ProfessorDom getCoordinator() {
		return coordinator;
	}


	public void setCoordinator(ProfessorDom coordinator) {
		this.coordinator = coordinator;
	}


	@Override
	public String toString() {
		return "typeDep";
	}


	
	
	

}

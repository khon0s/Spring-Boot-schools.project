package com.fortune.domains;

import java.io.Serializable;
import java.util.Set;

import com.fortune.base.BaseDom;
import com.fortune.base.DepartmentType;


public class SubjectPresenter extends BaseDom implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String name ;
	private DepartmentType typeDep;
	private Set<ProfessorPresenter> professorList;
	private Set<StudentPresenter> studentList;
	private ProfessorPresenter coordinator;
	
	public SubjectPresenter() {
		
	}

	/**
	 * @param name
	 * @param typeDep
	 * @param professorList
	 * @param studentList
	 * @param coordinator
	 */
	public SubjectPresenter(String name, DepartmentType typeDep, Set<ProfessorPresenter> professorList,
			Set<StudentPresenter> studentList, ProfessorPresenter coordinator) {
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

	public Set<ProfessorPresenter> getProfessorList() {
		return professorList;
	}

	public void setProfessorList(Set<ProfessorPresenter> professorList) {
		this.professorList = professorList;
	}

	public Set<StudentPresenter> getStudentList() {
		return studentList;
	}

	public void setStudentList(Set<StudentPresenter> studentList) {
		this.studentList = studentList;
	}

	public ProfessorPresenter getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(ProfessorPresenter coordinator) {
		this.coordinator = coordinator;
	}

	@Override
	public String toString() {
		return "typeDep";
	}

	
	
}

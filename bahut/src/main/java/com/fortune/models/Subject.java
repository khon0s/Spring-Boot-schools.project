package com.fortune.models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import com.fortune.base.Base;
import com.fortune.base.DepartmentType;

@JsonIdentityInfo(
		  generator = ObjectIdGenerators.PropertyGenerator.class, 
		  property = "id")
@Entity
public class Subject extends Base {
	
	@Column
	private String name ;
	
	@Enumerated(EnumType.STRING)
	private DepartmentType typeDep;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "professorsLecturing",
			joinColumns = @JoinColumn( name = "subject_id" ),
			inverseJoinColumns = @JoinColumn( name = "professor_id" )
			)
	private Set<Professor> professorsLecturing = new HashSet<>();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(
			name = "student_enrolled",
			joinColumns = @JoinColumn( name = "subject_id" ),
			inverseJoinColumns = @JoinColumn( name = "student_id" )
			)
	private Set<Student> enrolledStudents = new HashSet<>();
	
	
	@ManyToOne
	@JoinColumn(name = "coordinator_id")
	private Professor coordinator;



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

	public Set<Professor> getProfessorListSubject() {
		return professorsLecturing;
	}

	public void setProfessorListSubject(Set<Professor> professorListSubject) {
		this.professorsLecturing = professorListSubject;
	}

	public Set<Student> getStudentListSubject() {
		return enrolledStudents;
	}

	public void setStudentListSubject(Set<Student> studentListSubject) {
		this.enrolledStudents = studentListSubject;
	}

	public Professor getCoordinator() {
		return coordinator;
	}

	public void setCoordinator(Professor coordinator) {
		this.coordinator = coordinator;
	}

	public void addStudent( Student student ) {
		enrolledStudents.add(student);
	}
	
	public void addTeacher( Professor professor ) {
		professorsLecturing.add(professor);
	}
	
}

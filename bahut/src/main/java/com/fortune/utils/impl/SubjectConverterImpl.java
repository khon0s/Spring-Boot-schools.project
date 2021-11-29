package com.fortune.utils.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortune.domains.ProfessorPresenter;
import com.fortune.domains.StudentPresenter;
import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;
import com.fortune.models.Professor;
import com.fortune.models.Student;
import com.fortune.models.Subject;
import com.fortune.utils.IProfessorConverter;
import com.fortune.utils.IStudentConverter;
import com.fortune.utils.ISubjectConverter;

@Service( value = "subjectConverterImpl")
public class SubjectConverterImpl implements ISubjectConverter {
	
	@Autowired
	IStudentConverter studentConverterImpl ;
	
	@Autowired
	IProfessorConverter professorConverterImpl ;

	@Override
	public Subject convert(SubjectDom subjectDom) {
		
		Subject subjectModel = new Subject();
		
		Set<Student> listStudent = new HashSet<>();
		
		if( subjectDom.getStudentList() != null && !subjectDom.getStudentList().isEmpty()) {
			
			 listStudent = subjectDom.getStudentList().stream()
					.map( e -> studentConverterImpl.convert(e) )
					.collect(Collectors.toSet());
	    };
	    
				subjectModel.setStudentListSubject(listStudent);
		
		Set<Professor> listProfessor = new HashSet<>();
		
        if( subjectDom.getProfessorList() != null ) {
			
			 listProfessor = subjectDom.getProfessorList().stream()
					.map( e -> professorConverterImpl.convert(e) )
					.collect(Collectors.toSet());
			
			subjectModel.setProfessorListSubject( listProfessor ); 
		};
		
		if( subjectDom.getCoordinator() != null ) {
			
			Professor coordinatorModel =
					professorConverterImpl.convert( subjectDom.getCoordinator());
			
			subjectModel.setCoordinator(coordinatorModel);
		};
		
		subjectModel.setId( subjectDom.getId() );
		subjectModel.setName( subjectDom.getName() );
		subjectModel.setTypeDep( subjectDom.getTypeDep() );
		
		return subjectModel;
	}
	
	

	@Override
	public SubjectPresenter convert(Subject subject) {
		
		
		SubjectPresenter subjectPresenter = new SubjectPresenter();
		
		Set<StudentPresenter> listStudent = new HashSet<>();
		
        if( subject.getStudentListSubject() != null && !subject.getStudentListSubject().isEmpty()) {
			
        	listStudent = subject.getStudentListSubject().stream()
					.map( e -> studentConverterImpl.convertSimple(e) )
					.collect(Collectors.toSet());
			
		};
		
		Set<ProfessorPresenter> listPrfessor = new HashSet<>();
		
		 if( subject.getProfessorListSubject() != null && !subject.getProfessorListSubject().isEmpty() ) {
				
			 listPrfessor = subject.getProfessorListSubject().stream()
						.map( e -> professorConverterImpl.convertSimple(e)  )
						.collect(Collectors.toSet());
				
			};
			
			ProfessorPresenter coordPresenter = new ProfessorPresenter();
			
		 if( subject.getCoordinator() != null ) {	
			 
		 coordPresenter = professorConverterImpl.convertSimple(subject.getCoordinator());
			 
		 };
		 
		 subjectPresenter.setCoordinator(coordPresenter);
		 subjectPresenter.setStudentList(listStudent);
		 subjectPresenter.setProfessorList(listPrfessor);
		 
		 subjectPresenter.setCreateAt( subject.getCreateAt() );
		 subjectPresenter.setId( subject.getId() );
		 subjectPresenter.setName( subject.getName() );
		 subjectPresenter.setTypeDep( subject.getTypeDep() );
		 subjectPresenter.setUpdatedAt( subject.getUpdatedAt() );
		
		
		return subjectPresenter;
		
	}



	@Override
	public SubjectPresenter convertSimple(Subject subject) {
		
		SubjectPresenter subjectPresenter = new SubjectPresenter();
		
		 subjectPresenter.setCreateAt( subject.getCreateAt() );
		 subjectPresenter.setId( subject.getId() );
		 subjectPresenter.setName( subject.getName() );
		 subjectPresenter.setTypeDep( subject.getTypeDep() );
		 subjectPresenter.setUpdatedAt( subject.getUpdatedAt() );
		 
		 ProfessorPresenter coord = professorConverterImpl.convertSimple(subject.getCoordinator());
		 
		 subjectPresenter.setCoordinator(coord);
		
		return subjectPresenter;
	}








}

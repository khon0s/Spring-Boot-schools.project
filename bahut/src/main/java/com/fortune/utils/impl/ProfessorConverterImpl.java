package com.fortune.utils.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;
import com.fortune.domains.SubjectPresenter;
import com.fortune.models.Professor;
import com.fortune.models.Subject;
import com.fortune.utils.IProfessorConverter;
import com.fortune.utils.ISubjectConverter;

@Service(value = "professorConverterImpl")
public class ProfessorConverterImpl implements IProfessorConverter {
	
	@Autowired 
	ISubjectConverter subjectConverterImpl ;

	@Override
	public Professor convert(ProfessorDom professorDom) {
		
		if( professorDom == null ) return null ;

		Professor professorModel = new Professor();
		
		Set<Subject> setSubject = new HashSet<>();

		if(  professorDom.getSubjectList() != null  ) {
			
			 setSubject = professorDom.getSubjectList().stream()
					.map( e -> subjectConverterImpl.convert(e) )
					.collect(Collectors.toSet());
	    };
	    
	    professorModel.setSubjectList(setSubject); 
	    
	    Set<Subject> setHeadOf = new HashSet<>();
	    
        if(  professorDom.getHeadOfList() != null  ) {
			
		 setHeadOf = professorDom.getSubjectList().stream()
					.map( e -> subjectConverterImpl.convert(e) )
					.collect(Collectors.toSet());
			
			professorModel.setHeadOfList(setHeadOf); ;
	    };
	    
	    professorModel.setBirthDate(professorDom.getBirthDate());
	    professorModel.setCreateAt(professorDom.getCreateAt());
	    professorModel.setDni(professorDom.getDni());
	    professorModel.setFirstName(professorDom.getFirstName());
	    professorModel.setId(professorDom.getId());
	    professorModel.setLastName(professorDom.getLastName());
	    professorModel.setUpdatedAt(professorDom.getUpdatedAt());
	    professorModel.setHeadOfDepartment(professorDom.isHeadOfDepartment());
		return professorModel;

	}

	@Override
	public ProfessorPresenter convert(Professor professorModel) {
		
		if( professorModel == null ) return null ;
		
		ProfessorPresenter professorPresenter = new ProfessorPresenter();
		
		Set<SubjectPresenter> setSubject = new HashSet<>();
		
        if(  professorModel.getSubjectList() != null && !professorModel.getSubjectList().isEmpty()) {
			
		 setSubject = professorModel.getSubjectList().stream()
					.map( e -> subjectConverterImpl.convertSimple(e) )
					.collect(Collectors.toSet());
	    };
	    
	    Set<SubjectPresenter> setHeadOf = new HashSet<>();
	    
         if(  professorModel.getHeadOfList() != null && !professorModel.getHeadOfList().isEmpty() ) {
			
			 setHeadOf = professorModel.getHeadOfList().stream()
					.map( e -> subjectConverterImpl.convertSimple(e) )
					.collect(Collectors.toSet());
			
	    };
	    
	    professorPresenter.setSubjectList(setSubject);
	    professorPresenter.setHeadOfList(setHeadOf);
	    
	    professorPresenter.setBirthDate(professorModel.getBirthDate());
	    professorPresenter.setCreateAt(professorModel.getCreateAt());
	    professorPresenter.setDni(professorModel.getDni());
	    professorPresenter.setFirstName(professorModel.getFirstName());
	    professorPresenter.setId(professorModel.getId());
	    professorPresenter.setLastName(professorModel.getLastName());
	    professorPresenter.setUpdatedAt(professorModel.getUpdatedAt());
	    professorPresenter.setHeadOfDepartment(professorModel.isHeadOfDepartment());
		
		return professorPresenter;
	}

	@Override
	public ProfessorPresenter convertSimple(Professor professorModel) {
		
		if( professorModel == null ) return null ;
		
		ProfessorPresenter professorPresenter = new ProfessorPresenter();
		
		    professorPresenter.setBirthDate(professorModel.getBirthDate());
		    professorPresenter.setCreateAt(professorModel.getCreateAt());
		    professorPresenter.setDni(professorModel.getDni());
		    professorPresenter.setFirstName(professorModel.getFirstName());
		    professorPresenter.setId(professorModel.getId());
		    professorPresenter.setLastName(professorModel.getLastName());
		    professorPresenter.setUpdatedAt(professorModel.getUpdatedAt());
		    professorPresenter.setHeadOfDepartment(professorModel.isHeadOfDepartment());
			
			return professorPresenter;
	}

}

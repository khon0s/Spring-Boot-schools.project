package com.fortune.validator;

import java.util.List;

import org.springframework.stereotype.Service;

import com.fortune.domains.SubjectDom;
import com.fortune.domains.SubjectPresenter;

@Service(value = "subjectValidatorImpl")
public class SubjectValidatorImpl implements ISubjectValidator{
	
	
	List<String> listDep = List.of("HISTORY", "MATHEMATICS", "ART",
			"HUMANITIES", "SCIENCE", "LANGUAGES");

	@Override
	public String subjectValidationNew(SubjectDom subDom) {
		
		
		if (subDom.getName() == null) {
			return "data null";
		};

		String name = subDom.getName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		};
		
		if (subDom.getName().length() < 4)
			return "name length doesn't comply";
		if (subDom.getName().length() > 50)
			return "name length doesn't comply";
		
		
		if(listDep.stream().anyMatch( e -> e.equals(subDom.toString() ))) {
			return "Invalid department";
		};
		
		return "hakunamatata";
	}

	@Override
	public String subjectValidationPresenter(SubjectPresenter subPresenter) {
	
		
		if (subPresenter.getName() == null) {
			return "data null";
		};

		String name = subPresenter.getName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		};
		
		if (subPresenter.getName().length() < 4)
			return "name length doesn't comply";
		if (subPresenter.getName().length() > 50)
			return "name length doesn't comply";
		
		
		if(listDep.stream().anyMatch( e -> e.equals(subPresenter.toString() ))) {
			return "Invalid department";
		};
		
		return "hakunamatata";
	}

}

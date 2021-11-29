package com.fortune.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fortune.domains.ProfessorDom;
import com.fortune.domains.ProfessorPresenter;

@Service( value = "professorValidatorImpl" )
public class ProfessorValidatorImpl implements IProfessorValidator {

	@Override
	public String professorValidationNew(ProfessorDom proDom) {
		
		if (proDom.getFirstName() == null) {
			return "data null";
		};

		String name = proDom.getFirstName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		}
		;

		if (proDom.getFirstName() == null) {
			return "data null";
		}
		;

		String lastName = proDom.getLastName();

		boolean validLastName = lastName.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (validLastName == false) {
			return "lastname doesn't comply";
		}
		;

		String dni = proDom.getDni();
		String patternDni = "[0-9]{8}[A-Z a-z]";
		if (dni.matches(patternDni) == false)
			return "DNI doesn't comply";

		if (proDom.getFirstName().length() < 2)
			return "name length doesn't comply";
		if (proDom.getFirstName().length() > 30)
			return "name length doesn't comply";

		if (proDom.getLastName().length() < 2)
			return "name length doesn't comply";
		if (proDom.getLastName().length() > 30)
			return "name length doesn't comply";


		Date dateInput = proDom.getBirthDate() ;
		
		String pattern = "yyyy-MM-dd HH:mm";

		DateFormat df = new SimpleDateFormat(pattern);

		String InputAsString = df.format(dateInput);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(InputAsString, formatter);

		LocalDateTime dateIcod = java.time.LocalDateTime.now();

		if (dateTime.isAfter(dateIcod)) {
			return "Date after current time";
		};

		return "hakunamatata";
	}

	@Override
	public String professorValidationPresenter(ProfessorPresenter professorPresenter) {
		
		
		if (professorPresenter.getFirstName() == null) {
			return "data null";
		};

		String name = professorPresenter.getFirstName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		}
		;

		if (professorPresenter.getFirstName() == null) {
			return "data null";
		}
		;

		String lastName = professorPresenter.getLastName();

		boolean validLastName = lastName.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (validLastName == false) {
			return "lastname doesn't comply";
		}
		;

		String dni = professorPresenter.getDni();
		String patternDni = "[0-9]{8}[A-Z a-z]";
		if (dni.matches(patternDni) == false)
			return "DNI doesn't comply";

		if (professorPresenter.getFirstName().length() < 2)
			return "name length doesn't comply";
		if (professorPresenter.getFirstName().length() > 30)
			return "name length doesn't comply";

		if (professorPresenter.getLastName().length() < 2)
			return "name length doesn't comply";
		if (professorPresenter.getLastName().length() > 30)
			return "name length doesn't comply";

		Date dateInput = professorPresenter.getBirthDate() ;
		
		String pattern = "yyyy-MM-dd HH:mm";

		DateFormat df = new SimpleDateFormat(pattern);

		String InputAsString = df.format(dateInput);

		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
		LocalDateTime dateTime = LocalDateTime.parse(InputAsString, formatter);

		LocalDateTime dateIcod = java.time.LocalDateTime.now();

		if (dateTime.isAfter(dateIcod)) {
			return "Date after current time";
		};
		
		return "hakunamatata";
	}
	
	

}

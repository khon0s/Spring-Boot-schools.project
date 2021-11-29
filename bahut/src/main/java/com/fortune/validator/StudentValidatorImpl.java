package com.fortune.validator;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import org.springframework.stereotype.Service;

import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;

@Service(value = "studentValidatorImpl")
public class StudentValidatorImpl implements IStudentValidator {

	@Override
	public String studentValidationNew(StudentDom studentDom) {

		if (studentDom.getFirstName() == null) {
			return "data null";
		};

		String name = studentDom.getFirstName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		}
		;

		if (studentDom.getFirstName() == null) {
			return "data null";
		}
		;

		String lastName = studentDom.getLastName();

		boolean validLastName = lastName.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (validLastName == false) {
			return "lastname doesn't comply";
		}
		;

		String dni = studentDom.getDni();
		String patternDni = "[0-9]{8}[A-Z a-z]";
		if (dni.matches(patternDni) == false)
			return "DNI doesn't comply";

		if (studentDom.getFirstName().length() < 2)
			return "name length doesn't comply";
		if (studentDom.getFirstName().length() > 30)
			return "name length doesn't comply";

		if (studentDom.getLastName().length() < 2)
			return "lastname length doesn't comply";
		if (studentDom.getLastName().length() > 30)
			return "lastname length doesn't comply";

		if (studentDom.getAvrScore() < 0)
			return "score doesn't comply -";
		if (studentDom.getAvrScore() > 10)
			return "score doesn't comply +";

		Date dateInput = studentDom.getBirthDate() ;
		
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
	public String studentValidationPresenter(StudentPresenter studentPresenter) {
		
		if (studentPresenter.getFirstName() == null) {
			return "data null";
		};

		String name = studentPresenter.getFirstName();

		boolean valid = name.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (valid == false) {
			return "name doesn't comply";
		}
		;

		if (studentPresenter.getFirstName() == null) {
			return "data null";
		}
		;

		String lastName = studentPresenter.getLastName();

		boolean validLastName = lastName.matches("^[a-zA-ZäöüÄÖÜáéíóúñN-]*$");

		if (validLastName == false) {
			return "lastname doesn't comply";
		}
		;

		String dni = studentPresenter.getDni();
		String patternDni = "[0-9]{8}[A-Z a-z]";
		if (dni.matches(patternDni) == false)
			return "DNI doesn't comply";

		if (studentPresenter.getFirstName().length() < 2)
			return "name length doesn't comply";
		if (studentPresenter.getFirstName().length() > 30)
			return "name length doesn't comply";

		if (studentPresenter.getLastName().length() < 2)
			return "name length doesn't comply";
		if (studentPresenter.getLastName().length() > 30)
			return "name length doesn't comply";

		if (studentPresenter.getAvrScore() < 0)
			return "score doesn't comply -";
		if (studentPresenter.getAvrScore() > 10)
			return "score doesn't comply +";

		Date dateInput = studentPresenter.getBirthDate() ;
		
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

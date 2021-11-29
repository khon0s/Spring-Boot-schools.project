package com.fortune.utils.impl;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fortune.domains.StudentDom;
import com.fortune.domains.StudentPresenter;
import com.fortune.domains.SubjectPresenter;
import com.fortune.models.Student;
import com.fortune.models.Subject;
import com.fortune.utils.IStudentConverter;
import com.fortune.utils.ISubjectConverter;

@Service(value = "studentConverterImpl")
public class StudentConverterImpl implements IStudentConverter {

	@Autowired
	ISubjectConverter subjectConverterImpl;

	@Override
	public Student convert(StudentDom studentDom) {

		Student studentModel = new Student();

		Set<Subject> setSubjectModel = new HashSet<>();

		if (studentDom.getSubjectList() != null && !studentDom.getSubjectList().isEmpty()) {

			setSubjectModel = studentDom.getSubjectList().stream().map(e -> subjectConverterImpl.convert(e))
					.collect(Collectors.toSet());
		}
		;

		studentModel.setSubjectList(setSubjectModel);
		studentModel.setAvrScore(studentDom.getAvrScore());
		studentModel.setBirthDate(studentDom.getBirthDate());
		studentModel.setDni(studentDom.getDni());
		studentModel.setFirstName(studentDom.getFirstName());
		studentModel.setId(studentDom.getId());
		studentModel.setLastName(studentDom.getLastName());

		return studentModel;
	}

	@Override
	public StudentPresenter convert(Student student) {

		StudentPresenter studentPresenter = new StudentPresenter();

		Set<SubjectPresenter> listSubjectPresenter = new HashSet<>();

		if (student.getSubjectList() != null && !student.getSubjectList().isEmpty()) {

			listSubjectPresenter = student.getSubjectList().stream().map(e -> subjectConverterImpl.convertSimple(e))
					.collect(Collectors.toSet());
		}
		;

		studentPresenter.setSubjectList(listSubjectPresenter);

		studentPresenter.setFirstName(student.getFirstName());
		studentPresenter.setLastName(student.getLastName());
		studentPresenter.setDni(student.getDni());
		studentPresenter.setAvrScore(student.getAvrScore());
		studentPresenter.setBirthDate(student.getBirthDate());
		studentPresenter.setCreateAt(student.getCreateAt());
		studentPresenter.setUpdatedAt(student.getUpdatedAt());
		studentPresenter.setId(student.getId());

		return studentPresenter;
	}

	@Override
	public StudentPresenter convertSimple(Student student) {

		StudentPresenter studentPresenter = new StudentPresenter();

		studentPresenter.setFirstName(student.getFirstName());
		studentPresenter.setLastName(student.getLastName());
		studentPresenter.setDni(student.getDni());
		studentPresenter.setAvrScore(student.getAvrScore());
		studentPresenter.setBirthDate(student.getBirthDate());
		studentPresenter.setCreateAt(student.getCreateAt());
		studentPresenter.setUpdatedAt(student.getUpdatedAt());
		studentPresenter.setId(student.getId());

		return studentPresenter;
	}

	@Override
	public Student convertSimple(StudentPresenter studentPresenter) {

		Student studentModel = new Student();

		if (studentPresenter.getFirstName() != null) {
			studentModel.setFirstName(studentPresenter.getFirstName());

		}
		;

		if (studentPresenter.getLastName() != null) {
			studentModel.setLastName(studentPresenter.getLastName());

		}
		;

		if (studentPresenter.getDni() != null) {
			studentModel.setDni(studentPresenter.getDni());

		}
		;

		studentModel.setAvrScore(studentPresenter.getAvrScore());

		if (studentPresenter.getBirthDate() != null) {
			studentModel.setBirthDate(studentPresenter.getBirthDate());

		}
		;

		if (studentPresenter.getCreateAt() != null) {
			studentModel.setCreateAt(studentPresenter.getCreateAt());

		}
		;

		if (studentPresenter.getUpdatedAt() != null) {
			studentModel.setUpdatedAt(studentPresenter.getUpdatedAt());

		}
		;

		if (studentPresenter.getId() != null) {
			studentModel.setId(studentPresenter.getId());

		}
		;

		return studentModel;
	}

}

package com.example.project_fresher.service;

import com.example.project_fresher.Dto.SubjectDTO;
import com.example.project_fresher.entity.Subject;

import java.util.List;

public interface ISubjectService {
	Subject addSubject(SubjectDTO subjectDTO);

	SubjectDTO details(String subjectName);

	void deleteSubjectById(Long id);

	void upDateSubject(SubjectDTO subjectDTO);
	List<SubjectDTO> list();
	SubjectDTO findSubjectByName(String name);
}

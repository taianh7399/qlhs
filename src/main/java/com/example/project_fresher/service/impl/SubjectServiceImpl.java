package com.example.project_fresher.service.impl;

import com.example.project_fresher.Dto.SubjectDTO;
import com.example.project_fresher.entity.Subject;
import com.example.project_fresher.repository.SubjectRepository;
import com.example.project_fresher.service.ISubjectService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SubjectServiceImpl implements ISubjectService {
	private final SubjectRepository subjectRepository;
	private final ObjectMapper objectMapper=new ObjectMapper();

	@Override public Subject addSubject(SubjectDTO subjectDTO) {
		if(!subjectRepository.existsByNameSubject(subjectDTO.getNameSubject())){
			Subject subject=objectMapper.convertValue(subjectDTO,Subject.class);
			subjectRepository.save(subject);
			return subject;
		}
		return null;
	}

	@Override public SubjectDTO details(String subjectName) {
		Subject subject=subjectRepository.findSubjectByNameSubject(subjectName);
		if(subject!=null){
			SubjectDTO subjectDTO=objectMapper.convertValue(subject,SubjectDTO.class);
			return subjectDTO;
		}
		return null;
	}

	@Override public void deleteSubjectById(Long id) {
		if(subjectRepository.existsById(id)){
			Subject subject=subjectRepository.getById(id);
			subject.setStatus(0);
			subjectRepository.save(subject);
			System.out.println("delete success");
		}
		else {
			System.out.println("not found subject");
		}
	}

	@Override public void upDateSubject(SubjectDTO subjectDTO) {

	}

	@Override public List<SubjectDTO> list() {
		List<SubjectDTO> dtos=new ArrayList<>();
		List<Subject> subjects=subjectRepository.findAll();
		for(Subject subject:subjects){
			SubjectDTO subjectDTO=new SubjectDTO();
			subjectDTO.setNameSubject(subject.getNameSubject());
			subjectDTO.setId(subject.getId());
			subjectDTO.setStatus(subject.getStatus());
			dtos.add(subjectDTO);
		}
		return dtos;
	}

	@Override public SubjectDTO findSubjectByName(String name) {
		Subject subject=subjectRepository.findSubjectByNameSubject(name);
		if(subject!=null){
			SubjectDTO subjectDTO=objectMapper.convertValue(subject,SubjectDTO.class);
			return subjectDTO;
		}
		return null;
	}
}

package com.example.project_fresher.service;

import com.example.project_fresher.Dto.ClassDto;

import com.example.project_fresher.entity.Clazz;
import com.example.project_fresher.request.ClassRequest;


public interface IClassService {
	Clazz addClass(ClassRequest classRequest);

	ClassDto details(Clazz clazz);

	void deleteById(Long idClass);

	void updateClass(ClassDto classDto);
	ClassDto list();
}

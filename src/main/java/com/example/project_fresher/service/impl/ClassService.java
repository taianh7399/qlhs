package com.example.project_fresher.service.impl;

import com.example.project_fresher.Dto.ClassDto;

import com.example.project_fresher.entity.Clazz;
import com.example.project_fresher.repository.ClassRepository;
import com.example.project_fresher.repository.StudentRepository;
import com.example.project_fresher.request.ClassRequest;
import com.example.project_fresher.service.IClassService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor

public class ClassService implements IClassService {
	private final ClassRepository classRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final StudentRepository studentRepository;
	private final SinhvienServiceIMPL sinhvienServiceIMPL;

	@Override public Clazz addClass(ClassRequest classRequest) {
		if (!classRepository.existsById(classRequest.getId())) {
			Clazz clazz = objectMapper.convertValue(classRequest, Clazz.class);
			return clazz;
		}
		return null;
	}

	@Override
	public ClassDto details(Clazz clazz) {
		if (classRepository.existsById(clazz.getId())) {
			ClassDto classRespone =
				objectMapper.convertValue(classRepository.getClazzById(clazz.getId()), ClassDto.class);
//		classRespone.setStudentResponses(sinhvienServiceIMPL.listByClass(clazz.getId()));
			return classRespone;
		}
		return null;
	}

	@Override
	public void deleteById(Long idClass) {
		if (classRepository.existsById(idClass)) {
			Clazz clazz = classRepository.findClazzById(idClass);
			clazz.setStatus(1);
			classRepository.save(clazz);
			System.out.println("sửa thành công");
		} else {
			System.out.println("không tìm thấy class");
		}
	}

	@Override public void updateClass(ClassDto classDto) {
		Clazz clazz = classRepository.findClazzById(classDto.getId());
		if (clazz != null) {
			clazz = objectMapper.convertValue(classDto, Clazz.class);
			classRepository.save(clazz);
			System.out.println("update thành công");
		} else {
			System.out.println("không tìm thấy class tương ứng ");
		}
	}

	@Override public ClassDto list() {
		Clazz clazz= (Clazz) classRepository.findAll();
		ClassDto classDto=new ClassDto();
		classDto.setNameClass(clazz.getNameClass());
		classDto.setStatus(clazz.getStatus());
		classDto.setId(clazz.getId());
		classDto.setTeacher(clazz.getTeacher());

		return classDto ;
	}
}

package com.example.project_fresher.controller;

import com.example.project_fresher.Dto.ClassDto;

import com.example.project_fresher.entity.Clazz;
import com.example.project_fresher.repository.ClassRepository;
import com.example.project_fresher.request.ClassRequest;
import com.example.project_fresher.service.impl.ClassService;
import lombok.AllArgsConstructor;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/class")
@AllArgsConstructor
public class ClassController {
		private  final ClassService classService;
		private  final ClassRepository classRepository;


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public ClassDto list(){
		return classService.list();
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public Clazz addClass(@RequestBody ClassRequest classRequest) {
		return classService.addClass(classRequest);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/detail")
	public ClassDto details(@RequestBody Clazz classDto) {
		return classService.details(classDto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete")
	public void delete(@RequestParam Long idClass) {
		classService.deleteById(idClass);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public void updateClass(@RequestBody ClassDto classDto) {
		classService.updateClass(classDto);
		System.out.println("sửa thành công ");
	}
}

//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping("/findByNames")
//	public StudentRespone findSVByName(@RequestParam String name) {
//		return sinhVienService.findSVByName(name);
//	}


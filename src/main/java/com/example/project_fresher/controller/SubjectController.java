package com.example.project_fresher.controller;

import com.example.project_fresher.Dto.StudentDTO;
import com.example.project_fresher.Dto.SubjectDTO;
import com.example.project_fresher.entity.Student;
import com.example.project_fresher.entity.Subject;
import com.example.project_fresher.repository.SubjectRepository;
import com.example.project_fresher.request.StudentRequest;
import com.example.project_fresher.service.ISubjectService;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/subject")
@AllArgsConstructor
public class SubjectController {
	private final ISubjectService service;
	private final SubjectRepository subjectRepository;
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public List<SubjectDTO> list(){
		return service.list();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public Subject addSubject(@RequestBody SubjectDTO subjectDTO) {
		return service.addSubject(subjectDTO);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/details")
	public SubjectDTO details(@RequestParam String subjectName) {
		return service.details(subjectName);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete")
	public void delete(@RequestParam Long id) {
		service.deleteSubjectById(id);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public void updateSubject(@RequestBody SubjectDTO subjectDTO) {
		service.upDateSubject(subjectDTO);
		System.out.println("sửa thành công ");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/findByNames")
	public SubjectDTO findSubjectByName(@RequestParam String name) {
		return service.findSubjectByName(name);
	}


}

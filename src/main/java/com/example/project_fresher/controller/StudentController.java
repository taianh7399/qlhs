package com.example.project_fresher.controller;


import com.example.project_fresher.Dto.*;
import com.example.project_fresher.entity.Student;
import com.example.project_fresher.repository.StudentRepository;
import com.example.project_fresher.repository.UserRepository;
import com.example.project_fresher.request.StudentRequest;
import com.example.project_fresher.service.impl.SinhvienServiceIMPL;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/student")
@AllArgsConstructor
public class StudentController {

	private final SinhvienServiceIMPL sinhVienService;
	StudentRepository studentRepository;
	UserRepository userRepository;


	//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@GetMapping("/list")
//	public Page<StudentDTO> list(
//		@RequestParam(name = "page", required = false, defaultValue = "0") Integer page,
//		@RequestParam(name = "size", required = false, defaultValue = "5") Integer size,
//		@RequestParam(name = "sort", required = false, defaultValue = "ASC") String sort) {
//		Sort sort1 = null;
//		if (sort.equals("ASC")) {
//			sort1 = Sort.by("id").ascending();
//		}
//		if (sort.equals("DESC")) {
//			sort1 = Sort.by("id").descending();
//		}
//		Pageable pageable = PageRequest.of(page, size, sort1);
//		Page<StudentDTO> studentRespones = studentRepository.findStudents(pageable);
//		return studentRespones;
//	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/listByClass")
	public List<StudentDTO> list2(@RequestBody StudentRequest studentRequest) {
		return sinhVienService.listByClass(studentRequest);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/detailsClass")
	public List<ClassDetailDTO> detailsClass(@RequestBody StudentRequest studentRequest) {
		return sinhVienService.listByClazz(studentRequest);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/add")
	public Student addUser(@RequestBody StudentRequest studentRequest) {
		return sinhVienService.addSinhVien(studentRequest);
	}

	@PreAuthorize("hasRole('ROLE_USER')")
	@GetMapping("/details")
	public StudentDTO details() {
		return sinhVienService.details();
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/{maSV}")
	public ResponseEntity delete(@PathVariable  Long maSV) {
		if(sinhVienService.deleteSVByMaSV(maSV)){
			return ResponseEntity.ok("xóa thành công");
		}else{
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("xóa thất bại");
		}
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public void upDateSV(@RequestBody StudentRequest sinhVienDto) {
		sinhVienService.upDateSV(sinhVienDto);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/find")
	public StudentDTO findSVByNameOrStudentCode(@RequestParam String name, Long studentCode) {
		return sinhVienService.findSVByNameorStudentCode(name,studentCode);
	}


}

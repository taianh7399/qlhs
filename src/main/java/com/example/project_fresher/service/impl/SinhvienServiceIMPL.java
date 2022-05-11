package com.example.project_fresher.service.impl;


import com.example.project_fresher.Dto.ClassDetailDTO;
import com.example.project_fresher.jwt.JwtTokenProvider;
import com.example.project_fresher.repository.ClassRepository;
import com.example.project_fresher.request.StudentRequest;
import com.example.project_fresher.Dto.StudentDTO;
import com.example.project_fresher.entity.Student;
import com.example.project_fresher.repository.StudentRepository;
import com.example.project_fresher.repository.UserRepository;
import com.example.project_fresher.service.ISinhVienService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;


import javax.security.auth.login.Configuration;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class SinhvienServiceIMPL implements ISinhVienService {
	private final UserRepository userRepository;
	private final StudentRepository studentRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final ClassRepository classRepository;


	@Override public List<StudentDTO> listByClass(StudentRequest studentRequest) {
		List<Student> students = studentRepository.finallByClass(studentRequest.getClazz());
		List<StudentDTO> studentDTO = new ArrayList<>();

		for (Student student : students) {
			if(student.getStatus()==1){
			StudentDTO studentDTO1 = new StudentDTO();
			studentDTO1.setStudentCode(student.getStudentCode());
			studentDTO1.setAddress(student.getAddress());
			studentDTO1.setEmail(student.getEmail());
			studentDTO1.setFullName(student.getFullName());
			studentDTO1.setPhoneNumber(student.getPhoneNumber());
			studentDTO1.setGender(student.getGender());
			studentDTO.add(studentDTO1);
		}
		}
		return studentDTO;
	}

	@Override public List<ClassDetailDTO> listByClazz(StudentRequest studentRequest) {
		List<Student> students = studentRepository.findAllByClazz(studentRequest.getClazz());
		List<ClassDetailDTO> studentDTO = new ArrayList<>();

		for (Student student : students) {
			ClassDetailDTO studentDTO1 = new ClassDetailDTO();
			studentDTO1.setStudentCode(student.getStudentCode());
			studentDTO1.setAddress(student.getAddress());
			studentDTO1.setEmail(student.getEmail());
			studentDTO1.setFullName(student.getFullName());
			studentDTO1.setPhoneNumber(student.getPhoneNumber());
			studentDTO1.setGender(student.getGender());
			studentDTO.add(studentDTO1);
			studentDTO1.setUserName(student.getUser().getUsername());
			studentDTO1.setIdClass(student.getClazz().getId());
			studentDTO1.setNameTeacher(student.getClazz().getTeacher().getFullName());
			studentDTO1.setNameClass(student.getClazz().getNameClass());

		}
		return studentDTO;
	}

	@Override
	public Student addSinhVien(StudentRequest studentRequest) {
		if (!studentRepository.existsByStudentCode(studentRequest.getStudentCode()) &&
			!studentRepository.existsByEmail(studentRequest.getEmail())) {
			Student sinhVien = objectMapper.convertValue(studentRequest, Student.class);
			sinhVien.setClazz(classRepository.getClazzById(studentRequest.getClazz().getId()));
			sinhVien.setStatus(1);
			System.out.println("thêm thành công ");
			return objectMapper.convertValue(studentRepository.save(sinhVien), Student.class);

		} else {
			System.out.println("tồn tại sv");
			return null;
		}
	}

	@Override
	public StudentDTO details() {

		return objectMapper.convertValue(
			studentRepository.findStudentByUserName(SecurityContextHolder.getContext().getAuthentication().getName()),
			StudentDTO.class);

	}

	@Override public void upDateSV(StudentRequest studentRequest) {
		Student sinhVien =
			studentRepository.findUserByStudentCode(studentRequest.getStudentCode());
		if (sinhVien != null) {
			if (studentRequest.getAddress() == null) {
				sinhVien.setAddress(sinhVien.getAddress());
			} else {
				sinhVien.setAddress(studentRequest.getAddress());
			}
			if (studentRequest.getEmail() == null) {
				sinhVien.setAddress(sinhVien.getEmail());
			} else {
				sinhVien.setEmail(studentRequest.getEmail());
			}
			if (studentRequest.getFullName() == null) {
				sinhVien.setFullName(sinhVien.getFullName());
			} else {
				sinhVien.setEmail(studentRequest.getFullName());
			}
			if (studentRequest.getPhoneNumber() == null) {
				sinhVien.setPhoneNumber(sinhVien.getPhoneNumber());
			} else {
				sinhVien.setEmail(studentRequest.getEmail());
			}
			sinhVien.setStudentCode(studentRequest.getStudentCode());
			sinhVien.setUser(userRepository.getUserById(studentRequest.getUser().getId()));
			studentRepository.save(sinhVien);
		}
	}

	public Boolean deleteSVByMaSV(Long maSV) {
		Student sinhVien = studentRepository.getStudentByStudentCode(maSV);
		if (sinhVien != null) {
			sinhVien.setStatus(0);
			studentRepository.save(sinhVien);
			return true;
		} else {
			return false;
		}

	}

	public StudentDTO findSVByNameorStudentCode(String name, Long studentCode) {
		return objectMapper.convertValue(studentRepository.findStudentByFullNameOrStudentCode(name, studentCode),
			StudentDTO.class);
	}


}
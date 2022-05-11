package com.example.project_fresher.service;


import com.example.project_fresher.Dto.ClassDetailDTO;
import com.example.project_fresher.request.StudentRequest;
import com.example.project_fresher.Dto.StudentDTO;
import com.example.project_fresher.entity.Student;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface ISinhVienService {
	@Query("select e from Student e")
	List<StudentDTO> listByClass(StudentRequest studentRequest);
	@Query("select e from Student  e inner  join  Clazz c on e.clazz=c.id where e.clazz=?1")
	List<ClassDetailDTO> listByClazz(StudentRequest studentRequest);
	Student addSinhVien(StudentRequest studentRequest);
	StudentDTO details();
	void upDateSV(StudentRequest studentRequest);
	
}

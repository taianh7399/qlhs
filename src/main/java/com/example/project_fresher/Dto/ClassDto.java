package com.example.project_fresher.Dto;

import com.example.project_fresher.entity.Teacher;
import lombok.Data;



@Data
public class ClassDto {
	private Long id;
	private String nameClass;
	private Teacher teacher;
	private int status;

}

package com.example.project_fresher.request;

import com.example.project_fresher.entity.Teacher;
import lombok.Data;

@Data
public class ClassRequest {
	private Long id;
	private String nameClass;
	private Teacher teacher;
	private int status;
}

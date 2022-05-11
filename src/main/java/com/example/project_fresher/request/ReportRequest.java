package com.example.project_fresher.request;

import com.example.project_fresher.entity.Student;
import com.example.project_fresher.entity.Subject;
import lombok.Data;

@Data
public class ReportRequest {
	private Long id;
	private Subject subject;
	private Student student;
	private Long point;
}

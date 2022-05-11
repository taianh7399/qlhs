package com.example.project_fresher.Dto;
import lombok.Data;

@Data
public class ReportDTO {
	private Long id;
	private String nameSubject;
	private String nameStudent;
	private Long studentCode;
	private Long point;
}

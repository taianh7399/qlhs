package com.example.project_fresher.Dto;



import com.example.project_fresher.entity.User;
import lombok.Data;

@Data
public class ClassDetailDTO {
	private Long studentCode;
	private String fullName;
	private String address;
	private String email;
	private String phoneNumber;
	private String gender;
	private String userName;
	private Long idClass;
	private String nameClass;
	private String nameTeacher;
}

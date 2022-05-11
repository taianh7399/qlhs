package com.example.project_fresher.request;

import com.example.project_fresher.entity.Clazz;
import com.example.project_fresher.entity.Subject;
import com.example.project_fresher.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class StudentRequest {
	private Long studentCode;
	private String fullName;
	private String address;
	private String email;
	private String phoneNumber;
	private String gender;
	private int status;
	private User user;
	private List<Subject> subjectList;
	private Clazz clazz;
}

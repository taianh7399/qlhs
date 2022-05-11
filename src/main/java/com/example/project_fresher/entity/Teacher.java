package com.example.project_fresher.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "teacher")
public class Teacher {
	@Id
	private Long teacherCode;
	private String fullName;
	private String address;
	private String email;
	private String phoneNumber;
	private String gender;
	private int status;
	@OneToOne
	@JoinColumn(name = "userId")
	private User user;

}

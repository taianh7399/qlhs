package com.example.project_fresher.entity;

import lombok.Data;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "student")
@Data
public class Student {
	@Id
	private Long studentCode;
	private String fullName;
	private String address;
	private String email;
	private String phoneNumber;
	private String gender;
	private int status;
	@OneToOne()
	@JoinColumn(name = "userId")
	private User user;
	@ManyToMany
	@JoinColumn(name = "studentCode")
	private List<Subject> subjectList;
	@ManyToOne
	@JoinColumn(name = "idClass")
	private Clazz clazz;
}

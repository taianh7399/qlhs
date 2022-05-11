package com.example.project_fresher.entity;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "class")
public class Clazz {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameClass;
	@OneToOne
	@JoinColumn(name = "teacherCode")
	private Teacher teacher;
	private int status;
}

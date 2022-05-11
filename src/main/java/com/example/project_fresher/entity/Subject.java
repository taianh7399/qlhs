package com.example.project_fresher.entity;

import lombok.Data;

import javax.persistence.*;


@Data
@Entity
@Table(name = "subject")
public class Subject {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private String nameSubject;
	private int status;


}

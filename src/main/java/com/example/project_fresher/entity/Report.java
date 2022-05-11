package com.example.project_fresher.entity;

import lombok.Data;

import javax.persistence.*;


@Entity
@Table(name = "report")
@Data
public class Report {
	@Id

	private Long id;
	@ManyToOne
	@JoinColumn(name = "id_subject")
	private Subject subject;
	@ManyToOne
	@JoinColumn(name = "studentCode")
	private Student student;
	private Long point;
}

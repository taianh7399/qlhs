package com.example.project_fresher.Dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

@Data
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
public class StudentDTO {
	private Long studentCode;
	private String fullName;
	private String address;
	private String email;
	private String phoneNumber;
	private String gender;
	private String username;

}

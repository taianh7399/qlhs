package com.example.project_fresher.Dto;

import com.example.project_fresher.entity.Role;
import lombok.Data;

@Data
public class UserDto {
	private Long id;
	private String username;
	private Role roles;
}

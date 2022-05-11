package com.example.project_fresher.service;

import com.example.project_fresher.Dto.UserDto;
import com.example.project_fresher.request.UserRequest;
import com.example.project_fresher.entity.User;


import java.util.List;


public interface IUserService {
	List<UserDto> list();
	UserDto details(Long userId);
	User update(UserRequest userRequest);
	UserDto findUserByUsername(String userName);
}

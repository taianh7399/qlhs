package com.example.project_fresher.service.impl;

import com.example.project_fresher.Dto.UserDto;
import com.example.project_fresher.request.UserRequest;
import com.example.project_fresher.entity.User;
import com.example.project_fresher.jwt.CustomerUserDetails;
import com.example.project_fresher.repository.UserRepository;
import com.example.project_fresher.service.IUserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor

public class UserService implements UserDetailsService, IUserService {

	 UserRepository userRepository;
	private final ObjectMapper objectMapper=new ObjectMapper();



	@Override
	public UserDetails loadUserByUsername(String username) {
		User user = userRepository.findUserByUsername(username);
		if (user == null) {
			throw new UsernameNotFoundException(username);
		}
		return new CustomerUserDetails(user);
	}


	@Override public List<UserDto> list() {
		List<User> user=userRepository.findAll();
		List<UserDto> userDtos=new ArrayList<>();

		for (User user1:user){
			UserDto userDto=new UserDto();
			userDto.setUsername(user1.getUsername());
			userDto.setId(user1.getId());
			userDto.setRoles(user1.getRoles());
			userDtos.add(userDto);

		}
		return userDtos;

	}

	@Override
	public UserDto details(Long userId) {
		User user = userRepository.findUserById(userId);
		UserDto userDto=new UserDto();
		userDto.setUsername(user.getUsername());
		userDto.setId(user.getId());
		userDto.setRoles(user.getRoles());
		return userDto;
	}

	@Override
	public User update(UserRequest userRequest) {
		User user = userRepository.findUserByUsername(userRequest.getUsername());
		if (user != null) {
			user.setUsername(userRequest.getUsername());
			user.setPassword(new BCryptPasswordEncoder().encode(userRequest.getPassword()));
			if(userRequest.getRoles()!=user.getRoles()){
				user.setRoles(userRequest.getRoles());
			}else {
				user.setRoles(user.getRoles());
			}

			userRepository.save(user);
		}
		return user;
	}

	@Override
	public UserDto findUserByUsername(String name) {
		UserDto userDto = objectMapper.convertValue(userRepository.findUserByUsername(name), UserDto.class);
		return userDto;
	}
}

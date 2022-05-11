package com.example.project_fresher.controller;

import com.example.project_fresher.Dto.JWTRespone;
import com.example.project_fresher.Dto.UserDto;
import com.example.project_fresher.jwt.CustomerUserDetails;
import com.example.project_fresher.jwt.JwtTokenProvider;
import com.example.project_fresher.repository.RoleRepository;
import com.example.project_fresher.request.LoginRequest;
import com.example.project_fresher.request.UserRequest;
import com.example.project_fresher.entity.User;
import com.example.project_fresher.repository.UserRepository;
import com.example.project_fresher.service.impl.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@RestController
@RequestMapping("/api/user")
@AllArgsConstructor
public class UserController {
	private final UserService userService;
	UserRepository userRepository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	PasswordEncoder encoder;
	private RoleRepository roleRepository;

	AuthenticationManager authenticationManager;
	private JwtTokenProvider tokenProvider;

	@PostMapping("/login")
	public JWTRespone authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
			new UsernamePasswordAuthenticationToken(
				loginRequest.getUsername(),
				loginRequest.getPassword()
			)
		);
		// Nếu không xảy ra exception tức là thông tin hợp lệ
		// Set thông tin authentication vào Security Context
		SecurityContextHolder.getContext().setAuthentication(authentication);

//		 Trả về jwt cho người dùng.
		String jwt = tokenProvider.generateToken((CustomerUserDetails) authentication.getPrincipal());
		return new JWTRespone(jwt);
	}

	//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PostMapping("/register")
	public ResponseEntity<?> user(@RequestBody UserRequest userRequest) {
		if (userRepository.existsByUsername(userRequest.getUsername())) {
			return ResponseEntity.ok("username tồn tại");
		} else if (userRequest.getRoles().getRoleName().equals("ROLE_USER") ||
			userRequest.getRoles().getRoleName().equals("ROLE_ADMIN")) {
			User user = objectMapper.convertValue(userRequest, User.class);
			user.setRoles(roleRepository.getRoleByRoleName(userRequest.getRoles().getRoleName()));
			user.setPassword(encoder.encode(userRequest.getPassword()));
			userRepository.save(user);
		}
		return ResponseEntity.ok("success");
	}


	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public List<UserDto> list() {
		return userService.list();
	}


	@PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_USER')")
		@GetMapping("/details")
	public UserDto details(@RequestParam Long userId) {
		return userService.details(userId);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@DeleteMapping("/delete")
	public void delete(@RequestParam Long userID) {
		userRepository.deleteUserById(userID);
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/update")
	public void upDateSV(@RequestBody UserRequest userRequest) {
		userService.update(userRequest);
		System.out.println("sửa thành công ");
	}

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/find")
	public UserDto findUserByName(@RequestParam String name) {
		return userService.findUserByUsername(name);
	}
}

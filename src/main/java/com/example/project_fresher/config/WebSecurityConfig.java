package com.example.project_fresher.config;

import com.example.project_fresher.jwt.JWTAuthenticationFilter;
import com.example.project_fresher.service.impl.UserService;

import lombok.AllArgsConstructor;
import org.springframework.context.annotation.Bean;


import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@AllArgsConstructor
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {

	UserService userService;

	private final JWTAuthenticationFilter jwtAuthenticationFilter;

	@Bean(BeanIds.AUTHENTICATION_MANAGER)
	@Override
	public AuthenticationManager authenticationManagerBean() throws Exception {

		return super.authenticationManagerBean();
	}

	@Bean
	public PasswordEncoder passwordEncoder() {

		return new BCryptPasswordEncoder();
	}
//	@Bean
//	public ObjectMapper objectMapper(){
//		ObjectMapper objectMapper=new ObjectMapper();
//		objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
//		objectMapper.configure(MapperFeature.DEFAULT_VIEW_INCLUSION, true);
//		return objectMapper;
//	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth)
		throws Exception {
		auth.userDetailsService(userService) // Cung cáp userservice cho spring security
			.passwordEncoder(passwordEncoder()); // cung cấp password encoder
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.cors().and()
			.authorizeRequests()
			.antMatchers(HttpMethod.POST,"/api/user/login").permitAll()
			.antMatchers(HttpMethod.POST,"api/user/login").permitAll()
			.antMatchers(HttpMethod.POST, "/api/user/register").permitAll() // Cho phép tất cả mọi người truy cập vào địa chỉ này
			.anyRequest().authenticated(); // Tất cả các request khác đều cần phải xác thực mới được truy cập
		http
			.csrf()
			.disable();
		// Thêm một lớp Filter kiểm tra jwt
		http.addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);
	}
}


package com.example.project_fresher.jwt;

import com.example.project_fresher.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Collections;

@Data
@AllArgsConstructor
public class CustomerUserDetails implements UserDetails {
	User user;
	private static final long serialVersionUID = 1L;

	@Override public Collection<? extends GrantedAuthority> getAuthorities() {
		return Collections.singleton(new SimpleGrantedAuthority(user.getRoles().getRoleName()));
	}

	@Override public String getPassword() {
		return user.getPassword();
	}

	@Override public String getUsername() {
		return user.getUsername();
	}

	@Override public boolean isAccountNonExpired() {
		return true;
	}

	@Override public boolean isAccountNonLocked() {
		return true;
	}

	@Override public boolean isCredentialsNonExpired() {
		return true;
	}

	@Override public boolean isEnabled() {
		return true;
	}

	public User getUser() {
		return user;
	}
}

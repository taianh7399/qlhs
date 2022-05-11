package com.example.project_fresher.repository;

import com.example.project_fresher.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
	Role getRoleByRoleName(String roleName);
}

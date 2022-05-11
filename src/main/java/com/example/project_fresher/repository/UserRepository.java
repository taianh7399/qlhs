package com.example.project_fresher.repository;


import com.example.project_fresher.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
	User findUserByUsername(String username);

	User findUserById(Long id);

	boolean existsByUsername(String username);

	User getUserById(Long id);

	void deleteUserById(Long id);


}

package com.example.project_fresher.repository;

import com.example.project_fresher.entity.Clazz;
import com.example.project_fresher.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

	Student findUserByStudentCode(Long studentCode);

	Student getStudentByStudentCode(Long studentCode);

	Student findStudentByFullNameOrStudentCode(String fullName, Long studentCode);

	@Query("select s from Student s join User u on s.user=u.id where u.username=?1")
	Student findStudentByUserName(String userName);

	boolean existsByStudentCode(Long studentCode);

	boolean existsByEmail(String email);

	@Query("select  e from Student  e inner join Clazz c on e.clazz=c.id WHERE e.clazz=?1 ")
	List<Student> findAllByClazz(Clazz clazz);

	@Query("select  e from Student  e WHERE e.clazz=?1 ")
	List<Student> finallByClass(Clazz clazz);
}

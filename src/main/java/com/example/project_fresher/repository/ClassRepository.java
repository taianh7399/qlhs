package com.example.project_fresher.repository;

import com.example.project_fresher.entity.Clazz;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClassRepository extends JpaRepository<Clazz, Long> {
	Clazz getClazzById(Long id);

	Clazz findClazzById(Long idClass);

	boolean existsById(Long idClass);


}

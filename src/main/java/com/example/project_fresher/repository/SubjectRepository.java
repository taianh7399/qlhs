package com.example.project_fresher.repository;


import com.example.project_fresher.Dto.SubjectDTO;
import com.example.project_fresher.entity.Subject;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, Long> {

	boolean existsByNameSubject(String nameSubject);

	Subject findSubjectByNameSubject(String name);
}

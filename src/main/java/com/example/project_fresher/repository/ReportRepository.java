package com.example.project_fresher.repository;

import com.example.project_fresher.entity.Report;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface ReportRepository extends JpaRepository<Report, Long> {
	boolean existsById(Long id);

	@Query(
		"select e from Report e inner join Student s on e.student=s.studentCode inner join Subject c on e.subject=c.id")
	List<Report> findAll();

}

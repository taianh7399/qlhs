package com.example.project_fresher.service.impl;

import com.example.project_fresher.Dto.ReportDTO;
import com.example.project_fresher.entity.Report;
import com.example.project_fresher.entity.Student;
import com.example.project_fresher.entity.Subject;
import com.example.project_fresher.repository.ReportRepository;
import com.example.project_fresher.repository.StudentRepository;
import com.example.project_fresher.repository.SubjectRepository;
import com.example.project_fresher.request.ReportRequest;
import com.example.project_fresher.service.IReportService;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ReportService implements IReportService {
	private final ReportRepository repository;
	private final ObjectMapper objectMapper = new ObjectMapper();
	private final StudentRepository studentRepository;
	private final SubjectRepository subjectRepository;

	@Override
	public List<ReportDTO> list() {
		List<Report> reports = repository.findAll();
		List<ReportDTO> reportDTOS = new ArrayList<>();
		for (Report report : reports) {
			ReportDTO reportDTO = new ReportDTO();
			reportDTO.setId(report.getId());
			reportDTO.setNameStudent(report.getStudent().getFullName());
			reportDTO.setNameSubject(report.getSubject().getNameSubject());
			reportDTO.setPoint(report.getPoint());
			reportDTO.setStudentCode(report.getStudent().getStudentCode());
			reportDTOS.add(reportDTO);
		}
		return reportDTOS;
	}

	public Report add(ReportRequest request) {
		if (!repository.existsById(request.getId())) {
			if (studentRepository.existsByStudentCode(request.getStudent().getStudentCode()) &&
				subjectRepository.existsByNameSubject(request.getSubject().getNameSubject())) {
				Report report=new Report();
				Student student=studentRepository.findUserByStudentCode(request.getStudent().getStudentCode());
				Subject subject=subjectRepository.findSubjectByNameSubject(request.getSubject().getNameSubject());
				report.setPoint(request.getPoint());
				report.setId(request.getId());
				report.setStudent(student);
				report.setSubject(subject);
				repository.save(report);
				return report;
			}
		}
		return null;
	}
}

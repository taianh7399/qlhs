package com.example.project_fresher.controller;

import com.example.project_fresher.Dto.ReportDTO;
import com.example.project_fresher.entity.Report;
import com.example.project_fresher.request.ReportRequest;
import com.example.project_fresher.service.impl.ReportService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/report")
@AllArgsConstructor
public class ReportController {
	private final ReportService reportService;

	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@GetMapping("/list")
	public List<ReportDTO> list() {
		return reportService.list();
	}
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@PutMapping("/add")
	public Report add(@RequestBody ReportRequest request){
		return reportService.add(request);
	}
}

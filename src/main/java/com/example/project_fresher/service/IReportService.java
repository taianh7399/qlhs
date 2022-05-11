package com.example.project_fresher.service;

import com.example.project_fresher.Dto.ReportDTO;
import com.example.project_fresher.entity.Report;
import com.example.project_fresher.request.ReportRequest;

import java.util.List;

public interface IReportService {
	List<ReportDTO> list();
	Report add(ReportRequest request);
}

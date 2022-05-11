package com.example.project_fresher.controller;

import com.example.project_fresher.entity.BaseExportExcelModel;
import com.example.project_fresher.entity.Report;
import com.example.project_fresher.entity.ReportExcel;
import com.example.project_fresher.repository.ReportRepository;
import com.example.project_fresher.service.ExportExcelFileService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/export")
@AllArgsConstructor
public class ExportExCel {
	ReportRepository repository;
	private  final ExportExcelFileService exportExcelFileService;
	@GetMapping
	public void exportExcel(HttpServletResponse http) throws IOException {
		List<BaseExportExcelModel> list = new ArrayList<>();
		List<ReportExcel> list1=new ArrayList<>();
		List<Report> list2 = repository.findAll();
		for (Report report:list2){
			ReportExcel reportExcel=new ReportExcel();
			reportExcel.setId(report.getId());
			reportExcel.setFullName(report.getStudent().getFullName());
			reportExcel.setNameSubject(report.getSubject().getNameSubject());
			reportExcel.setPoint(report.getPoint());
			list1.add(reportExcel);
		}
		list.addAll(list1);

		exportExcelFileService.exportFile("test","dang",list ,ReportExcel.class);

	}


}

package com.example.project_fresher.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportExcel extends BaseExportExcelModel {
	private Long id;
	private String fullName;
	private String nameSubject;
	private Long point;

	@Override public List<MetadataExcelModel> getListMetadata() {
		return Arrays.asList(
			new MetadataExcelModel(1,"id",Long.class,"Mã học sinh"),
			new MetadataExcelModel(2,"fullName",String.class,"tên học sinh"),
			new MetadataExcelModel(3,"nameSubject",String.class,"tên môn học"),
			new MetadataExcelModel(4,"point",Long.class,"Điểm")
		);
	}
}

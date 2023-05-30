package com.example.demo.report.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Report_MS_Yearmapping")
public class ReportYearMapping {

	
	@Id
	
	 @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "reportMsYearMappingSeq")
    @SequenceGenerator(name = "reportMsYearMappingSeq", sequenceName = "report_ms_year_mapping_seq", allocationSize = 1)
	@Column(name = "id")
	private int id;
	@Column(name="map_id")
	private Integer mapId;
	@Column(name="report_id")
	private Integer reportId;
	@Column(name="report_year")
	private String reportYear;
	@Column(name="year_id")
	private String yearId;
	
	
	
}

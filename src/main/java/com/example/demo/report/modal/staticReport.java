package com.example.demo.report.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "PGI_MS_STATICREPORT_1")
//@Table(name = "static_report")
public class staticReport {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String report_code ;
	private String report_name;
	private String tag;
	private String ministryreport;
	private String statereport;
	private String districtreport;
	private String blockreport;
	private String schoolreport;
	private String civilianreport;
    @Column(name="ordernumber")
	private int ordernumber;
    private String reportdomain;
    private String reportTag;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getReport_code() {
		return report_code;
	}
	public void setReport_code(String report_code) {
		this.report_code = report_code;
	}
	public String getReport_name() {
		return report_name;
	}
	public void setReport_name(String report_name) {
		this.report_name = report_name;
	}
	public String getTag() {
		return tag;
	}
	public void setTag(String tag) {
		this.tag = tag;
	}
	public String getMinistryreport() {
		return ministryreport;
	}
	public void setMinistryreport(String ministryreport) {
		this.ministryreport = ministryreport;
	}
	public String getStatereport() {
		return statereport;
	}
	public void setStatereport(String statereport) {
		this.statereport = statereport;
	}
	public String getDistrictreport() {
		return districtreport;
	}
	public void setDistrictreport(String districtreport) {
		this.districtreport = districtreport;
	}
	public String getBlockreport() {
		return blockreport;
	}
	public void setBlockreport(String blockreport) {
		this.blockreport = blockreport;
	}
	public String getSchoolreport() {
		return schoolreport;
	}
	public void setSchoolreport(String schoolreport) {
		this.schoolreport = schoolreport;
	}
	public String getCivilianreport() {
		return civilianreport;
	}
	public void setCivilianreport(String civilianreport) {
		this.civilianreport = civilianreport;
	}
	public int getOrdernumber() {
		return ordernumber;
	}
	public void setOrdernumber(int ordernumber) {
		this.ordernumber = ordernumber;
	}
	public String getReportdomain() {
		return reportdomain;
	}
	public void setReportdomain(String reportdomain) {
		this.reportdomain = reportdomain;
	}
	public String getReportTag() {
		return reportTag;
	}
	public void setReportTag(String reportTag) {
		this.reportTag = reportTag;
	}
    
    
}

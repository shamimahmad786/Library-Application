package com.example.demo.dataentry.modal;

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
@Table(name = "Dashboard_CM_SchooleDataEntry")
public class SchooleDataEntry {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String schoolId;
	private String school_country;
	private String school_state;
	private String school_district;
	private String school_region;
	private String school_schoolName;
	private String school_operation;
	private String school_sanction_year;
	private int school_Enroll;
	private String school_category;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getSchoolId() {
		return schoolId;
	}
	public void setSchoolId(String schoolId) {
		this.schoolId = schoolId;
	}
	public String getSchool_country() {
		return school_country;
	}
	public void setSchool_country(String school_country) {
		this.school_country = school_country;
	}
	public String getSchool_state() {
		return school_state;
	}
	public void setSchool_state(String school_state) {
		this.school_state = school_state;
	}
	public String getSchool_district() {
		return school_district;
	}
	public void setSchool_district(String school_district) {
		this.school_district = school_district;
	}
	public String getSchool_region() {
		return school_region;
	}
	public void setSchool_region(String school_region) {
		this.school_region = school_region;
	}
	public String getSchool_schoolName() {
		return school_schoolName;
	}
	public void setSchool_schoolName(String school_schoolName) {
		this.school_schoolName = school_schoolName;
	}
	public String getSchool_operation() {
		return school_operation;
	}
	public void setSchool_operation(String school_operation) {
		this.school_operation = school_operation;
	}
	public String getSchool_sanction_year() {
		return school_sanction_year;
	}
	public void setSchool_sanction_year(String school_sanction_year) {
		this.school_sanction_year = school_sanction_year;
	}
	public int getSchool_Enroll() {
		return school_Enroll;
	}
	public void setSchool_Enroll(int school_Enroll) {
		this.school_Enroll = school_Enroll;
	}
	public String getSchool_category() {
		return school_category;
	}
	public void setSchool_category(String school_category) {
		this.school_category = school_category;
	}
	
	
	
	
}

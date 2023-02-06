package com.example.demo.dataentry.modal;

import java.util.Date;

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
@Table(name = "School_CM_Academicresults")
public class AcademicResults {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	String schoolId;
	Date academicYear;
	int tenthAppeared;
	int tenthPassed;
	int eleventhAppeared;
	int eleventhPassed;
	int tweelthAppeared;
	int tweelthPassed;
	String academicsYear;
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
	public Date getAcademicYear() {
		return academicYear;
	}
	public void setAcademicYear(Date academicYear) {
		this.academicYear = academicYear;
	}
	public int getTenthAppeared() {
		return tenthAppeared;
	}
	public void setTenthAppeared(int tenthAppeared) {
		this.tenthAppeared = tenthAppeared;
	}
	public int getTenthPassed() {
		return tenthPassed;
	}
	public void setTenthPassed(int tenthPassed) {
		this.tenthPassed = tenthPassed;
	}
	public int getEleventhAppeared() {
		return eleventhAppeared;
	}
	public void setEleventhAppeared(int eleventhAppeared) {
		this.eleventhAppeared = eleventhAppeared;
	}
	public int getEleventhPassed() {
		return eleventhPassed;
	}
	public void setEleventhPassed(int eleventhPassed) {
		this.eleventhPassed = eleventhPassed;
	}
	public int getTweelthAppeared() {
		return tweelthAppeared;
	}
	public void setTweelthAppeared(int tweelthAppeared) {
		this.tweelthAppeared = tweelthAppeared;
	}
	public int getTweelthPassed() {
		return tweelthPassed;
	}
	public void setTweelthPassed(int tweelthPassed) {
		this.tweelthPassed = tweelthPassed;
	}
	public String getAcademicsYear() {
		return academicsYear;
	}
	public void setAcademicsYear(String academicsYear) {
		this.academicsYear = academicsYear;
	}
	
	
	
}

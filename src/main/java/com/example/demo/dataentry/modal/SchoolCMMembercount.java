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
@Table(name = "School_CM_Membercount")
public class SchoolCMMembercount {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	private String schoolId;
	private Date academicYear;
	private int generalBoys;
	private int generalGirls;
	private int scBoys;
	private int scGirls;
	private int stBoys;
	private int stGirls;
	private int obcBoys;
	private int obcGirls;
	private int  diffAbleBoys;
	private int diffAbleGirls;
	private String academicYearTo;
	private String academicYearFrom;
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
	public int getGeneralBoys() {
		return generalBoys;
	}
	public void setGeneralBoys(int generalBoys) {
		this.generalBoys = generalBoys;
	}
	public int getGeneralGirls() {
		return generalGirls;
	}
	public void setGeneralGirls(int generalGirls) {
		this.generalGirls = generalGirls;
	}
	public int getScBoys() {
		return scBoys;
	}
	public void setScBoys(int scBoys) {
		this.scBoys = scBoys;
	}
	public int getScGirls() {
		return scGirls;
	}
	public void setScGirls(int scGirls) {
		this.scGirls = scGirls;
	}
	public int getStBoys() {
		return stBoys;
	}
	public void setStBoys(int stBoys) {
		this.stBoys = stBoys;
	}
	public int getStGirls() {
		return stGirls;
	}
	public void setStGirls(int stGirls) {
		this.stGirls = stGirls;
	}
	public int getObcBoys() {
		return obcBoys;
	}
	public void setObcBoys(int obcBoys) {
		this.obcBoys = obcBoys;
	}
	public int getObcGirls() {
		return obcGirls;
	}
	public void setObcGirls(int obcGirls) {
		this.obcGirls = obcGirls;
	}
	public int getDiffAbleBoys() {
		return diffAbleBoys;
	}
	public void setDiffAbleBoys(int diffAbleBoys) {
		this.diffAbleBoys = diffAbleBoys;
	}
	public int getDiffAbleGirls() {
		return diffAbleGirls;
	}
	public void setDiffAbleGirls(int diffAbleGirls) {
		this.diffAbleGirls = diffAbleGirls;
	}
	public String getAcademicYearTo() {
		return academicYearTo;
	}
	public void setAcademicYearTo(String academicYearTo) {
		this.academicYearTo = academicYearTo;
	}
	public String getAcademicYearFrom() {
		return academicYearFrom;
	}
	public void setAcademicYearFrom(String academicYearFrom) {
		this.academicYearFrom = academicYearFrom;
	}
	
	
	
	
	
	
}

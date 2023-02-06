package com.example.demo.pgi.modal;

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
@Table(name = "Pgi_Ex_LearningOutcome")
public class LearningOutcome {
@Id
@GeneratedValue(strategy = GenerationType.AUTO)
@Column(name = "id")
private int id;
private String sessionYear;
private String stateName;
private String stateCode;
private String districtName;
private String districtCode;
private String distReportCard;
private String distReportCardpercent;
private String distReportCardpoint;
private String avgLangScore3;
private String avgLangpercetScore3;
private String avgLangpointsScore3;
private String avgMathScore3;
private String avgMathpercentScore3;
private String avgMathpointsScore3;
private String avgLangScore5;
private String avgLangpercentScore5;
private String avgLangpointsScore5;
private String avgMathScore5;
private String avgMathpercentScore5;
private String avgMathpointsScore5;
private String avgLangScore8;
private String avgLangpercentScore8;
private String avgLangpointsScore8;
private String avgMathScore8;
private String avgMathpercentScore8;
private String avgMathpointsScore8;
public int getId() {
	return id;
}
public void setId(int id) {
	this.id = id;
}
public String getSessionYear() {
	return sessionYear;
}
public void setSessionYear(String sessionYear) {
	this.sessionYear = sessionYear;
}
public String getStateName() {
	return stateName;
}
public void setStateName(String stateName) {
	this.stateName = stateName;
}
public String getStateCode() {
	return stateCode;
}
public void setStateCode(String stateCode) {
	this.stateCode = stateCode;
}
public String getDistrictName() {
	return districtName;
}
public void setDistrictName(String districtName) {
	this.districtName = districtName;
}
public String getDistrictCode() {
	return districtCode;
}
public void setDistrictCode(String districtCode) {
	this.districtCode = districtCode;
}
public String getDistReportCard() {
	return distReportCard;
}
public void setDistReportCard(String distReportCard) {
	this.distReportCard = distReportCard;
}
public String getDistReportCardpercent() {
	return distReportCardpercent;
}
public void setDistReportCardpercent(String distReportCardpercent) {
	this.distReportCardpercent = distReportCardpercent;
}
public String getDistReportCardpoint() {
	return distReportCardpoint;
}
public void setDistReportCardpoint(String distReportCardpoint) {
	this.distReportCardpoint = distReportCardpoint;
}
public String getAvgLangScore3() {
	return avgLangScore3;
}
public void setAvgLangScore3(String avgLangScore3) {
	this.avgLangScore3 = avgLangScore3;
}
public String getAvgLangpercetScore3() {
	return avgLangpercetScore3;
}
public void setAvgLangpercetScore3(String avgLangpercetScore3) {
	this.avgLangpercetScore3 = avgLangpercetScore3;
}
public String getAvgLangpointsScore3() {
	return avgLangpointsScore3;
}
public void setAvgLangpointsScore3(String avgLangpointsScore3) {
	this.avgLangpointsScore3 = avgLangpointsScore3;
}
public String getAvgMathScore3() {
	return avgMathScore3;
}
public void setAvgMathScore3(String avgMathScore3) {
	this.avgMathScore3 = avgMathScore3;
}
public String getAvgMathpercentScore3() {
	return avgMathpercentScore3;
}
public void setAvgMathpercentScore3(String avgMathpercentScore3) {
	this.avgMathpercentScore3 = avgMathpercentScore3;
}
public String getAvgMathpointsScore3() {
	return avgMathpointsScore3;
}
public void setAvgMathpointsScore3(String avgMathpointsScore3) {
	this.avgMathpointsScore3 = avgMathpointsScore3;
}
public String getAvgLangScore5() {
	return avgLangScore5;
}
public void setAvgLangScore5(String avgLangScore5) {
	this.avgLangScore5 = avgLangScore5;
}
public String getAvgLangpercentScore5() {
	return avgLangpercentScore5;
}
public void setAvgLangpercentScore5(String avgLangpercentScore5) {
	this.avgLangpercentScore5 = avgLangpercentScore5;
}
public String getAvgLangpointsScore5() {
	return avgLangpointsScore5;
}
public void setAvgLangpointsScore5(String avgLangpointsScore5) {
	this.avgLangpointsScore5 = avgLangpointsScore5;
}
public String getAvgMathScore5() {
	return avgMathScore5;
}
public void setAvgMathScore5(String avgMathScore5) {
	this.avgMathScore5 = avgMathScore5;
}
public String getAvgMathpercentScore5() {
	return avgMathpercentScore5;
}
public void setAvgMathpercentScore5(String avgMathpercentScore5) {
	this.avgMathpercentScore5 = avgMathpercentScore5;
}
public String getAvgMathpointsScore5() {
	return avgMathpointsScore5;
}
public void setAvgMathpointsScore5(String avgMathpointsScore5) {
	this.avgMathpointsScore5 = avgMathpointsScore5;
}
public String getAvgLangScore8() {
	return avgLangScore8;
}
public void setAvgLangScore8(String avgLangScore8) {
	this.avgLangScore8 = avgLangScore8;
}
public String getAvgLangpercentScore8() {
	return avgLangpercentScore8;
}
public void setAvgLangpercentScore8(String avgLangpercentScore8) {
	this.avgLangpercentScore8 = avgLangpercentScore8;
}
public String getAvgLangpointsScore8() {
	return avgLangpointsScore8;
}
public void setAvgLangpointsScore8(String avgLangpointsScore8) {
	this.avgLangpointsScore8 = avgLangpointsScore8;
}
public String getAvgMathScore8() {
	return avgMathScore8;
}
public void setAvgMathScore8(String avgMathScore8) {
	this.avgMathScore8 = avgMathScore8;
}
public String getAvgMathpercentScore8() {
	return avgMathpercentScore8;
}
public void setAvgMathpercentScore8(String avgMathpercentScore8) {
	this.avgMathpercentScore8 = avgMathpercentScore8;
}
public String getAvgMathpointsScore8() {
	return avgMathpointsScore8;
}
public void setAvgMathpointsScore8(String avgMathpointsScore8) {
	this.avgMathpointsScore8 = avgMathpointsScore8;
}



}

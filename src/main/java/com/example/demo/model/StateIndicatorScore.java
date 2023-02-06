package com.example.demo.model;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "indicator_score")
@NamedQueries({
	@NamedQuery(name = "StateIndicatorScore.getAllData", query = "from StateIndicatorScore") })
public class StateIndicatorScore

{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "data_id")
	private long dataId;
	@Column(name = "ind_id")
	private int indId;
	@Column(name = "state_id")
	private int stateId;
	@Column(name = "domain_id")
	private int domainId;
	@Column(name = "cycle_id")
	private int cycleId;
	@Column(name = "year")
	private String year;
	@Column(name = "score")
	private int score;
	@Column(name = "raw_value")
	private Double calcRawValue;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "status")
	private String status;
	@Column(name = "modified_time")
	private Date modifiedTime;
	@Transient
	private IndicatorMaster indData;
	@Transient
	private int quarter;
	@Transient
	private DomainScore domainScore;
	public long getDataId() {
		return dataId;
	}
	public void setDataId(long dataId) {
		this.dataId = dataId;
	}
	public int getIndId() {
		return indId;
	}
	public void setIndId(int indId) {
		this.indId = indId;
	}
	public int getStateId() {
		return stateId;
	}
	public void setStateId(int stateId) {
		this.stateId = stateId;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public int getCycleId() {
		return cycleId;
	}
	public void setCycleId(int cycleId) {
		this.cycleId = cycleId;
	}
	public String getYear() {
		return year;
	}
	public void setYear(String year) {
		this.year = year;
	}
	public int getScore() {
		return score;
	}
	public void setScore(int score) {
		this.score = score;
	}
	public Double getCalcRawValue() {
		return calcRawValue;
	}
	public void setCalcRawValue(Double calcRawValue) {
		this.calcRawValue = calcRawValue;
	}
	public String getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}
	public String getModifiedBy() {
		return modifiedBy;
	}
	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}
	public Date getCreatedTime() {
		return createdTime;
	}
	public void setCreatedTime(Date createdTime) {
		this.createdTime = createdTime;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public IndicatorMaster getIndData() {
		return indData;
	}
	public void setIndData(IndicatorMaster indData) {
		this.indData = indData;
	}
	public int getQuarter() {
		return quarter;
	}
	public void setQuarter(int quarter) {
		this.quarter = quarter;
	}
	public DomainScore getDomainScore() {
		return domainScore;
	}
	public void setDomainScore(DomainScore domainScore) {
		this.domainScore = domainScore;
	}
	
	
	

}

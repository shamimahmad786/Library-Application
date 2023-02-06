package com.example.demo.model;

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
@Table(name = "indicator_master")
public class IndicatorMaster

{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "ind_id")
	private long indId;
	@Column(name = "domain_id")
	private int domainId;
	@Column(name = "serial")
	private String serial;
	@Column(name = "created_by")
	private String createdBy;
	@Column(name = "modified_by")
	private String modifiedBy;
	@Column(name = "created_time")
	private Date createdTime;
	@Column(name = "status")
	private String status;
	@Column(name = "weight")
	private int weight;
	@Column(name = "source")
	private String source;
	@Column(name = "description")
	private String description;
	@Column(name = "modified_time")
	private Date modifiedTime;
	@Column(name = "name")
	private String name;
	@Column(name = "benchmark")
	private int benchmark;
	public long getIndId() {
		return indId;
	}
	public void setIndId(long indId) {
		this.indId = indId;
	}
	public int getDomainId() {
		return domainId;
	}
	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}
	public String getSerial() {
		return serial;
	}
	public void setSerial(String serial) {
		this.serial = serial;
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
	public int getWeight() {
		return weight;
	}
	public void setWeight(int weight) {
		this.weight = weight;
	}
	public String getSource() {
		return source;
	}
	public void setSource(String source) {
		this.source = source;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getModifiedTime() {
		return modifiedTime;
	}
	public void setModifiedTime(Date modifiedTime) {
		this.modifiedTime = modifiedTime;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getBenchmark() {
		return benchmark;
	}
	public void setBenchmark(int benchmark) {
		this.benchmark = benchmark;
	}
	
	

}

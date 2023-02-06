package com.example.demo.model;

import java.sql.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity
@Table(name = "Report_Ru_Audit")
@Component
public class ReportAudit {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")	
public Long id;
public String ipAddress;
public String activity;
//public Date createdOn;
public Long getId() {
	return id;
}
public void setId(Long id) {
	this.id = id;
}
public String getIpAddress() {
	return ipAddress;
}
public void setIpAddress(String ipAddress) {
	this.ipAddress = ipAddress;
}
public String getActivity() {
	return activity;
}
public void setActivity(String activity) {
	this.activity = activity;
}



}

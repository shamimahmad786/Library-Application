package com.example.demo.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@NamedQueries({
		@NamedQuery(name = "User.getUserList", query = "Select s from User s where s.status = :search"), })
@Table(name = "pgi_user_mst")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "record_id")
	private Long id;

	@Column(name = "user_id", length = 100, unique = true)
	private String username;

	@Column(name = "password", length = 300)
	private String password;

	@Column(name = "first_name", length = 100)
	private String firstname;

	@Column(name = "last_name", length = 100)
	private String lastname;

	@Column(name = "mobile_no", length = 11)
	private String mobileNo;

	@Column(name = "email", length = 100, unique = true)
	private String email;

	@Column(name = "realpwd", length = 300)
	private String realPassword;

	@Column(name = "role_id", length = 100 )
	private String roleId;

	@Column(name = "designation", length = 100)
	private String designation;

	@Column(name = "param_name", length = 100)
	private String paramName;

	@Column(name = "param_value", length = 100)
	private String paramValue;

	@Column(name = "created_time")
	private Date creationTime;

	@Column(name = "modified_time")
	private Date modificationTime;

	@Column(name = "status", length = 2)
	private String status;

	@Column(name = "created_by", length = 100)
	private String createdby;

	@Column(name = "modified_by", length = 100)
	private String modifiedBy;
	
	@Column(name = "schema_name")
	private String schema_name;
	
	@Column(name = "stateId")
	private String stateId;
	
	@Column(name = "stateName")
	private String stateName;
	
	@Column(name = "districtName")
	private String districtName;
	@Column(name = "blockName")
	private String blockName;
	
	@Column(name = "groupId")
	private String groupId;
	
	
	

	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

	public User(Long id, String username, String password, String firstname, String lastname, String mobileNo,
			String email, String realPassword, String roleId, String designation, String paramName, String paramValue,
			Date creationTime, Date modificationTime, String status, String createdby, String modifiedBy,
			String schema_name, String stateId, String stateName, String districtName, String blockName,
			String groupId) {
		super();
		this.id = id;
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.mobileNo = mobileNo;
		this.email = email;
		this.realPassword = realPassword;
		this.roleId = roleId;
		this.designation = designation;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.creationTime = creationTime;
		this.modificationTime = modificationTime;
		this.status = status;
		this.createdby = createdby;
		this.modifiedBy = modifiedBy;
		this.schema_name = schema_name;
		this.stateId = stateId;
		this.stateName = stateName;
		this.districtName = districtName;
		this.blockName = blockName;
		this.groupId = groupId;
	}



	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFirstname() {
		return firstname;
	}

	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getRealPassword() {
		return realPassword;
	}

	public void setRealPassword(String realPassword) {
		this.realPassword = realPassword;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getParamName() {
		return paramName;
	}

	public void setParamName(String paramName) {
		this.paramName = paramName;
	}

	public String getParamValue() {
		return paramValue;
	}

	public void setParamValue(String paramValue) {
		this.paramValue = paramValue;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Date creationTime) {
		this.creationTime = creationTime;
	}

	public Date getModificationTime() {
		return modificationTime;
	}

	public void setModificationTime(Date modificationTime) {
		this.modificationTime = modificationTime;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getCreatedby() {
		return createdby;
	}

	public void setCreatedby(String createdby) {
		this.createdby = createdby;
	}

	public String getModifiedBy() {
		return modifiedBy;
	}

	public void setModifiedBy(String modifiedBy) {
		this.modifiedBy = modifiedBy;
	}

	public String getSchema_name() {
		return schema_name;
	}

	public void setSchema_name(String schema_name) {
		this.schema_name = schema_name;
	}

	public String getStateId() {
		return stateId;
	}

	public void setStateId(String stateId) {
		this.stateId = stateId;
	}

	public String getStateName() {
		return stateName;
	}

	public void setStateName(String stateName) {
		this.stateName = stateName;
	}

	public String getDistrictName() {
		return districtName;
	}

	public void setDistrictName(String districtName) {
		this.districtName = districtName;
	}

	public String getBlockName() {
		return blockName;
	}

	public void setBlockName(String blockName) {
		this.blockName = blockName;
	}

	public String getGroupId() {
		return groupId;
	}

	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	

}

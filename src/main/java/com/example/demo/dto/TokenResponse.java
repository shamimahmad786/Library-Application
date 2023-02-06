package com.example.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
public class TokenResponse {
	
	private String username;
	
	private String token;
	
	private String paramName;

	private String paramValue;
	private String roleId;
	private String schema_name;
	private String stateId;
	private String stateName;
	private String districtName;
	private String groupId;
	
	
	
	public TokenResponse(String username, String token, String paramName, String paramValue, String roleId,
			String schema_name, String stateId, String stateName, String districtName, String groupId) {
		super();
		this.username = username;
		this.token = token;
		this.paramName = paramName;
		this.paramValue = paramValue;
		this.roleId = roleId;
		this.schema_name = schema_name;
		this.stateId = stateId;
		this.stateName = stateName;
		this.districtName = districtName;
		this.groupId = groupId;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getToken() {
		return token;
	}
	public void setToken(String token) {
		this.token = token;
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
	public String getRoleId() {
		return roleId;
	}
	public void setRoleId(String roleId) {
		this.roleId = roleId;
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
	public String getGroupId() {
		return groupId;
	}
	public void setGroupId(String groupId) {
		this.groupId = groupId;
	}
	
	
	

}

package com.example.demo.bean;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;
@Component
public class StaticReportBeans {
	private List<Object> columnName;
	private List<Map<String, Object>> rowValue;
//	private List<Object> rowValue;
	private List<StaticFilterBean> staticFilter;
	private List<Object> columnView;
	private List<Object> columnDataType;
	private Map<String,List<Object>> msData;
	private String status;
	private String errorMessage;
	private String jsonString;
	private List<String> yearList = new ArrayList<String>();
	private List<Map<String,Object>> yearListMap1 = new ArrayList<Map<String,Object>>();
	private List<Map<String,String>> yearListMap = new ArrayList<Map<String,String>>();
	public List<Object> getColumnName() {
		return columnName;
	}
	public void setColumnName(List<Object> columnName) {
		this.columnName = columnName;
	}
	public List<Map<String, Object>> getRowValue() {
		return rowValue;
	}
	public void setRowValue(List<Map<String, Object>> rowValue) {
		this.rowValue = rowValue;
	}
	public List<StaticFilterBean> getStaticFilter() {
		return staticFilter;
	}
	public void setStaticFilter(List<StaticFilterBean> staticFilter) {
		this.staticFilter = staticFilter;
	}
	public List<Object> getColumnView() {
		return columnView;
	}
	public void setColumnView(List<Object> columnView) {
		this.columnView = columnView;
	}
	public List<Object> getColumnDataType() {
		return columnDataType;
	}
	public void setColumnDataType(List<Object> columnDataType) {
		this.columnDataType = columnDataType;
	}
	public Map<String, List<Object>> getMsData() {
		return msData;
	}
	public void setMsData(Map<String, List<Object>> msData) {
		this.msData = msData;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getErrorMessage() {
		return errorMessage;
	}
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}
	public String getJsonString() {
		return jsonString;
	}
	public void setJsonString(String jsonString) {
		this.jsonString = jsonString;
	}
	public List<String> getYearList() {
		return yearList;
	}
	public void setYearList(List<String> yearList) {
		this.yearList = yearList;
	}
	public List<Map<String, Object>> getYearListMap1() {
		return yearListMap1;
	}
	public void setYearListMap1(List<Map<String, Object>> yearListMap1) {
		this.yearListMap1 = yearListMap1;
	}
	public List<Map<String, String>> getYearListMap() {
		return yearListMap;
	}
	public void setYearListMap(List<Map<String, String>> yearListMap) {
		this.yearListMap = yearListMap;
	}
	
	
	
}

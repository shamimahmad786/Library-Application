package com.example.demo.model;

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
public class QueryModel {
	private String className;
	
	private String fieldName;
	
	private String fieldAlias;
	
	private String fieldValue;
	
	private String fieldType;
	
	private String conditionType;
	
	private String filterName;
	
	private String joinTableName;
	
		private TableJoinDetails joinDetails;

		public String getClassName() {
			return className;
		}

		public void setClassName(String className) {
			this.className = className;
		}

		public String getFieldName() {
			return fieldName;
		}

		public void setFieldName(String fieldName) {
			this.fieldName = fieldName;
		}

		public String getFieldAlias() {
			return fieldAlias;
		}

		public void setFieldAlias(String fieldAlias) {
			this.fieldAlias = fieldAlias;
		}

		public String getFieldValue() {
			return fieldValue;
		}

		public void setFieldValue(String fieldValue) {
			this.fieldValue = fieldValue;
		}

		public String getFieldType() {
			return fieldType;
		}

		public void setFieldType(String fieldType) {
			this.fieldType = fieldType;
		}

		public String getConditionType() {
			return conditionType;
		}

		public void setConditionType(String conditionType) {
			this.conditionType = conditionType;
		}

		public String getFilterName() {
			return filterName;
		}

		public void setFilterName(String filterName) {
			this.filterName = filterName;
		}

		public String getJoinTableName() {
			return joinTableName;
		}

		public void setJoinTableName(String joinTableName) {
			this.joinTableName = joinTableName;
		}

		public TableJoinDetails getJoinDetails() {
			return joinDetails;
		}

		public void setJoinDetails(TableJoinDetails joinDetails) {
			this.joinDetails = joinDetails;
		}
		
		
		
		
	
}

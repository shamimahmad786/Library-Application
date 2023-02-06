package com.example.demo.dto;

import java.util.HashMap;
import java.util.List;

import com.example.demo.model.QueryModel;
import com.example.demo.model.StateIndicatorScore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class IndicatorRequestDto {

	private int domainId;

	private int stateId;

	private String param;

	private String year;

	private int indId;

	private StateIndicatorScore stateIndicatorScore;
	
	List<QueryModel> queryModel;

	public int getDomainId() {
		return domainId;
	}

	public void setDomainId(int domainId) {
		this.domainId = domainId;
	}

	public int getStateId() {
		return stateId;
	}

	public void setStateId(int stateId) {
		this.stateId = stateId;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getYear() {
		return year;
	}

	public void setYear(String year) {
		this.year = year;
	}

	public int getIndId() {
		return indId;
	}

	public void setIndId(int indId) {
		this.indId = indId;
	}

	public StateIndicatorScore getStateIndicatorScore() {
		return stateIndicatorScore;
	}

	public void setStateIndicatorScore(StateIndicatorScore stateIndicatorScore) {
		this.stateIndicatorScore = stateIndicatorScore;
	}

	public List<QueryModel> getQueryModel() {
		return queryModel;
	}

	public void setQueryModel(List<QueryModel> queryModel) {
		this.queryModel = queryModel;
	}
	
	
}

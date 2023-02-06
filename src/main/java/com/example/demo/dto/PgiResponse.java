package com.example.demo.dto;

import java.util.ArrayList;
import java.util.List;

import com.example.demo.model.IndicatorMaster;
import com.example.demo.model.StateIndicatorScore;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@AllArgsConstructor
@ToString
public class PgiResponse

{
	private List<IndicatorMaster> indicators = null;
	private List<StateIndicatorScore> scores = new ArrayList<StateIndicatorScore>();
	private String statusCode = null;
	private String statusDesc = null;
	public List<IndicatorMaster> getIndicators() {
		return indicators;
	}
	public void setIndicators(List<IndicatorMaster> indicators) {
		this.indicators = indicators;
	}
	public List<StateIndicatorScore> getScores() {
		return scores;
	}
	public void setScores(List<StateIndicatorScore> scores) {
		this.scores = scores;
	}
	public String getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(String statusCode) {
		this.statusCode = statusCode;
	}
	public String getStatusDesc() {
		return statusDesc;
	}
	public void setStatusDesc(String statusDesc) {
		this.statusDesc = statusDesc;
	}
	
	

}

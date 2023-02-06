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
@Getter
@Setter
@Entity
@Table(name = "state_question")
public class StateQuestion {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "questionid")
	private Long questionId;
	@Column(name = "questiondesc")
	private String quesDesc;
	@Column(name = "domainid")
	private Long domainId;
	@Column(name = "categoryid")
	private Long categoryId;
	@Column(name = "quesseries")
	private String quesSeries;
	@Column(name = "sourceid")
	private Long sourceId;
	@Column(name = "weight")
	private int weight;
	@Column(name = "benchmark")
	private int benchMark;
	@Column(name = "benchmarkdesc")
	private String benchMarkDesc;
	@Column(name = "sortid")
	private int sortId;
}

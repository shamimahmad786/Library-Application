package com.example.demo.model;

import java.util.List;

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
@Table(name = "PGI_RE_PERFORMANCE")
public class PgiPerformance {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private Long id;
	@Column(name = "questionId")
	private Long questionId;
	@Column(name = "questNumber")
	private String questNumber;
	@Column(name = "r1")
	private Double r1;
	@Column(name = "r2")
	private Double r2;
	@Column(name = "r3")
	private Double r3;
	@Column(name = "r4")
	private Double r4;
	@Column(name = "r5")
	private Double r5;
	@Column(name = "calVlaue")
	private Double calVlaue;
	@Column(name = "calPoint")
	private Float calPoint;
	@Column(name = "stateId")
	private Long stateId;
	@Column(name = "UpdatedBy")
	private String UpdatedBy;
	@Column(name = "approver")
	private String approver;
	@Column(name = "finalStatus")
	private String finalStatus;
	@Column(name = "domId")
	private int domId;
	@Column(name = "questType")
	private String questType;
	@Column(name = "r1Type")
	private String r1Type;
	@Column(name = "r2Type")
	private String r2Type;
	@Column(name = "r3Type")
	private String r3Type;
	
	
	
	
}

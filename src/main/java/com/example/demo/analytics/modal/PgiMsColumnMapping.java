package com.example.demo.analytics.modal;

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
@Table(name = "PGI_MS_ColumnMapping")
public class PgiMsColumnMapping {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;
	@Column(name = "columnId")
	private int columnId;
	@Column(name = "masterDetails")
	private String masterDetails;
	@Column(name = "masterValue")
	private String masterValue;
}

package com.example.demo.analytics.modal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "PGI_MS_ColumnList")
public class PgiMsColumn {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "columnId")
	private int columnId;
	@Column(name = "tableId")
	private int tableId;
	@Column(name = "columnName")
	private String columnName;
	@Column(name = "columnView")
	private String columnView;
	@Column(name = "columnDataType")
	private String columnDataType;
	@Column(name = "tableName")
	private String tableName;
	@Column(name = "masterFlag")
	private String masterFlag;
	@Column(name = "tableView")
	private String tableView;
	@Column(name = "columnType")
	private String columnType;
	
}

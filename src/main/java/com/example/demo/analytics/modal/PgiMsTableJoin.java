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
@Table(name = "PGI_MS_TableJoin")
public class PgiMsTableJoin {
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "joinTableId")
	private int joinTableId;
	
	@Column(name = "tableId")
	private int tableId;
	@Column(name = "tableName")
	private String tableName;
	@Column(name = "tableViewName")
	private String tableViewName;
	@Column(name = "tableMappingColumn")
	private String tableMappingColumn;
	@Column(name = "joinedTableId")
	private int joinedTableId;
	@Column(name = "joinTableName")
	private String joinTableName;
	@Column(name = "joinTableView")
	private String joinTableView;
	@Column(name = "joinTableMappingColumn")
	private String joinTableMappingColumn;

}

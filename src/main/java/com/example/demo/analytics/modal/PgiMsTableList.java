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
@Table(name = "PGI_MS_TableList")
public class PgiMsTableList {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "tableId")
	private int tableId;
	@Column(name = "tableName")
	private String tableName;
	@Column(name = "tableView")
	private String tableView;

}

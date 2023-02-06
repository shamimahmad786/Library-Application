package com.example.demo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.analytics.modal.PgiMsTableList;
import com.example.demo.model.BlockMaster;
import com.example.demo.report.modal.ReportName;

public interface PgiMsTableListRepository  extends JpaRepository<PgiMsTableList, Integer> {
	List<PgiMsTableList>  findAll();
	
}

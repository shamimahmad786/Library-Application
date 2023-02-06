package com.example.demo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.analytics.modal.PgiMsColumn;
import com.example.demo.analytics.modal.PgiMsTableList;

public interface PgiMsColumnRepository  extends JpaRepository<PgiMsColumn, Integer> {
	List<PgiMsColumn> findByTableIdOrderByColumnViewAsc(int tableId);
	
}

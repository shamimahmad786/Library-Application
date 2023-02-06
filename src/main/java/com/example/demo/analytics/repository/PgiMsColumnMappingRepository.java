package com.example.demo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.analytics.modal.PgiMsColumn;
import com.example.demo.analytics.modal.PgiMsColumnMapping;

public interface PgiMsColumnMappingRepository extends JpaRepository<PgiMsColumnMapping, Integer>{
List<PgiMsColumnMapping> findByColumnIdOrderByMasterDetailsAsc(int colId);
}

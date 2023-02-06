package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.IndicatorMaster;
import com.example.demo.model.PgiPerformance;

public interface PgiPerformanceRepository extends JpaRepository<PgiPerformance, Long>{
	List<PgiPerformance> findByStateId(Long stateId);
}

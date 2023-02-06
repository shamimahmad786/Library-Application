package com.example.demo.analytics.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.analytics.modal.PgiMsTableJoin;
import com.example.demo.analytics.modal.analyticsOperator;

public interface analyticsOperatorRepository extends JpaRepository<analyticsOperator, Integer> {
	List<analyticsOperator>  findAll();
}

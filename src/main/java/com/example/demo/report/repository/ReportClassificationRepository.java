package com.example.demo.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.report.modal.ReportClassification;
import com.example.demo.report.modal.ReportDomain;

public interface ReportClassificationRepository  extends JpaRepository<ReportClassification, Integer> {
	List<ReportClassification> findAllByCategory(String category);
}

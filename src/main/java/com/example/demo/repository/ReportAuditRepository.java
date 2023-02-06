package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.PgiPerformance;
import com.example.demo.model.ReportAudit;

public interface ReportAuditRepository extends JpaRepository<ReportAudit, Long>{

}

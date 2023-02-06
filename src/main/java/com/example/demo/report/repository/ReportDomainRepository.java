package com.example.demo.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.report.modal.ReportDomain;
import com.example.demo.report.modal.ReportName;

public interface ReportDomainRepository  extends JpaRepository<ReportDomain, Integer> {

}

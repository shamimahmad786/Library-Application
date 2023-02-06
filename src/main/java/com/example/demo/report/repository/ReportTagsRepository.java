package com.example.demo.report.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.report.modal.ReportTags;
import com.example.demo.report.modal.staticReport;

public interface ReportTagsRepository  extends JpaRepository<ReportTags, Integer>{

}

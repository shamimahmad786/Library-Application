package com.example.demo.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.report.modal.ReportYearMapping;
import com.example.demo.report.modal.staticReport;

public interface ReportYearMappingRepository extends JpaRepository<ReportYearMapping, Integer> {
List<ReportYearMapping> findByMapIdOrderByYearIdDesc(Integer mapId);
//findAllByOrderBySchoolreportAsc
//findByTeacherIdOrderByWorkStartDateDesc
}

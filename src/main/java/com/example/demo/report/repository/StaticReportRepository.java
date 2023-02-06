package com.example.demo.report.repository;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.report.modal.ReportName;
import com.example.demo.report.modal.staticReport;

@Repository
public interface StaticReportRepository  extends JpaRepository<staticReport, Integer>{
	List<staticReport>  findAllByOrderByIdAsc();
//	List<staticReport>  findAllByOrderByMinistryreportAsc(String msReport);
	List<staticReport> findAllByStatereport(String stReport);
//	List<staticReport> findAllByOrderByDistrictreportAsc(String dsReport);
//	List<staticReport> findAllByOrderByBlockreportAsc(String bkReport);
//	List<staticReport> findAllByOrderBySchoolreportAsc(String slReport);
	List<staticReport> findAllByCivilianreport(String cvReport);
	List<staticReport> findAllByOrderByOrdernumberAsc();
	List<staticReport> findByIdIn(List<Integer> id);
	
//	List<staticReport> findByIdInOrdernumberAsc(List<Integer> id);
}

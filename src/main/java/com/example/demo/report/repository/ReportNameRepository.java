package com.example.demo.report.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;
import com.example.demo.report.modal.ReportName;

//import com.example.demo.model.BlockMaster;
@Repository
public interface ReportNameRepository extends JpaRepository<ReportName, Integer>{
	List<ReportName>  findAllByOrderByIdAsc();
}

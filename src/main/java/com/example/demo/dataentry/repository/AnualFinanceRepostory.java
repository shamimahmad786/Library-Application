package com.example.demo.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.AnualFinance;

public interface AnualFinanceRepostory extends JpaRepository<AnualFinance, Integer>{
	List<AnualFinance> findByAutonomusTypeAndFinancialYearTo(String autonomusType, String financialYearTo);
}

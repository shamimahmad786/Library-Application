package com.example.demo.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.dataentry.modal.SchooleDataEntry;

public interface AcademicResultsRepostory extends JpaRepository<AcademicResults, Integer> {
	List<AcademicResults> findBySchoolIdAndAcademicsYear(String schoolId,String academicsYear);
}

package com.example.demo.dataentry.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.SchooleDataEntry;
import com.example.demo.report.modal.ReportName;

public interface DataEntryRepository extends JpaRepository<SchooleDataEntry, Integer> {
List<SchooleDataEntry> findBySchoolId(String schoolId);
}

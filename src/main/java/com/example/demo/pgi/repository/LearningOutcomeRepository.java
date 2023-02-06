package com.example.demo.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.dataentry.modal.AcademicResults;
import com.example.demo.pgi.modal.LearningOutcome;

public interface LearningOutcomeRepository extends JpaRepository<LearningOutcome, Integer> {
List<LearningOutcome>	findByDistrictCode(String distId);
}

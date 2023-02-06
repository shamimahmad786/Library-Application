package com.example.demo.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pgi.modal.DigitalLearning;
import com.example.demo.pgi.modal.LearningOutcome;

public interface DigitalLearningRepository extends JpaRepository<DigitalLearning, Integer> {
	List<DigitalLearning>	findByDistrictCode(String distId);
	}
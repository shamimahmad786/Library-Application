package com.example.demo.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pgi.modal.DigitalLearning;
import com.example.demo.pgi.modal.InfraFacility;

public interface InfraFacilityRepository extends JpaRepository<InfraFacility, Integer> {
	List<InfraFacility>	findByDistrictCode(String distId);
	} 

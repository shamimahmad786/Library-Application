package com.example.demo.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pgi.modal.DigitalLearning;
import com.example.demo.pgi.modal.Equity;
import com.example.demo.pgi.modal.InfraFacility;

public interface EquityRepository extends JpaRepository<Equity, Integer> {
	List<Equity>	findByDistrictCode(String distId);
	} 


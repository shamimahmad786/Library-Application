package com.example.demo.pgi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.pgi.modal.Access;
import com.example.demo.pgi.modal.DigitalLearning;

public interface AccessRepository extends JpaRepository<Access, Integer> {
	List<Access>	findByDistrictCode(String distId);
	} 



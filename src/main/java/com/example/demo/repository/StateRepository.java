package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import com.example.demo.model.StateMaster;

@Repository
public interface StateRepository extends JpaRepository<StateMaster, Long> {
//public interface StateRepository extends JpaRepository<StateMaster, Long>, JpaSpecificationExecutor<StateMaster> {

	public List<StateMaster> findById(long id);
	public StateMaster findByUdiseStateCode(String id);
	public List<StateMaster> findAllByOrderByStateNameAsc();

}

package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BlockMaster;
import com.example.demo.model.DomainMaster;
import com.example.demo.model.TableMaster;

@Repository
public interface TableMasterRepository extends JpaRepository<TableMaster, Long> {

	List<TableMaster> findByTableId(long tableId);
}

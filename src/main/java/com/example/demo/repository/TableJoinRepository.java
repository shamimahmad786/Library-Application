package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.TableFields;
import com.example.demo.model.TableJoinDetails;

@Repository
public interface TableJoinRepository extends JpaRepository<TableJoinDetails, Long> {

	List<TableJoinDetails> findByMasterTableId(int masterTableId);
}

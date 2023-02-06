package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BlockMaster;
import com.example.demo.model.TableFields;
import com.example.demo.model.TableMaster;
import com.example.demo.model.User;

@Repository
public interface TableFieldRepository extends JpaRepository<TableFields, Long> {

	List<TableFields> findByTableId(long tableId);
}

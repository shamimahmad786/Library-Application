package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.BlockMaster;

@Repository
public interface BlockRepository extends JpaRepository<BlockMaster, Long> {

//	List<BlockMaster> getBlock(long districtId);
	List<BlockMaster> findByUdiseDistCode(String districtId);
}

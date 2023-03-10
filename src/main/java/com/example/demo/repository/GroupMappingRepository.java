package com.example.demo.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.GroupMapping;
import com.example.demo.model.GroupMaster;

@Repository
public interface GroupMappingRepository  extends JpaRepository<GroupMapping, Long>{
List<GroupMapping> findByGroupId(int id);
List<GroupMapping> findByGroupIdIn(List<Integer> groupId);
@Transactional
long deleteByGroupId(int groupId);
}

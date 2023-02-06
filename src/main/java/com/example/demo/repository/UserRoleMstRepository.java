package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.model.UserRoleMst;

@Repository
public interface UserRoleMstRepository extends JpaRepository<UserRoleMst, Long> {
}

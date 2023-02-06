package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface UserRepository extends JpaRepository<User, Long> {

	List<User> findByEmail(String email);

  @Query("select u from User u where u.username like %:username%")
	//@Query("select u from User u where u.username = :usernam")
	List<User> getByUsername(@Param("username") String username);

	User findByUsername(String username);

	public User findByUsernameAndPassword(String username, String password);

//	public List<User> getUserList(String search);s
		public List<User> findByStateId(String stateId);										 
	public Optional<User> findById(Long id);

}

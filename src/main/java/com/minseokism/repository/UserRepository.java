package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.minseokism.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{
	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.id = :userName")
	boolean existsById(@Param("userName")String id);

	@Query("SELECT CASE WHEN COUNT(c) > 0 THEN true ELSE false END FROM User c WHERE c.email = :userEmail")
	boolean existsByEmail(@Param("userEmail")String email);
	
	User findById(String id);
}

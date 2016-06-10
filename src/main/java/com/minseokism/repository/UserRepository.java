package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minseokism.domain.User;

public interface UserRepository extends JpaRepository<User, Integer>{

}

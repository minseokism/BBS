package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minseokism.domain.Token;

public interface TokenRepository extends JpaRepository<Token, Integer>{

}


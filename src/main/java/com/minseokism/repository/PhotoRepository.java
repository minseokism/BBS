package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minseokism.domain.Photo;

public interface PhotoRepository extends JpaRepository<Photo, Integer>{

}

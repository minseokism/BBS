package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minseokism.domain.Image;

public interface ImageRepository extends JpaRepository<Image, Integer>{

}

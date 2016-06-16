package com.minseokism.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.minseokism.domain.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer>{

}

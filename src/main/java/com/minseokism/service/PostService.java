package com.minseokism.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.minseokism.domain.Post;
import com.minseokism.repository.PostRepository;

@Service
public class PostService{
	@Autowired
	PostRepository postRepository;
	
	public List<Post> findAll() {
		return postRepository.findAll();
	}
	
	public Post findOne(Integer id) {
		return postRepository.findOne(id);
	}
	
	public Post create(Post post) {
		return postRepository.save(post);
	}
	
	public Post update(Post post) {
		return postRepository.save(post);
	}
	
	public void delete(Integer id) {
		postRepository.delete(id);
	}

	public boolean write(Post post) {
		
		if (postRepository.save(post) != null) return true;
		return false;
	}

}
